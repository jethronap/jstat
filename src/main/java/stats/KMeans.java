package stats;

import stats.utils.Centroid;
import stats.utils.Distance;
import stats.utils.Record;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * Implementation of K-Means Clustering Algorithm.
 */
public class KMeans {


    private KMeans() {
        throw new IllegalAccessError("This constructor should not be used");
    }


    /**
     * Performs the K-Means clustering algorithm on the given data set.
     *
     * @param records       The given data set.
     * @param k             Number of clusters.
     * @param distance      The distance calculator.
     * @param maxIterations Upper limit for number of iterations.
     * @return K clusters along with their features.
     */
    public static Map<Centroid, List<Record>> fit(List<Record> records, int k, Distance distance, int maxIterations) {

        applyPreconditions(records, k, distance, maxIterations);

        List<Centroid> centroids = randomCentroids(records, k);
        Map<Centroid, List<Record>> clusters = new HashMap<>();
        Map<Centroid, List<Record>> lastState = new HashMap<>();

        // Iterate for a pre defined number of times:
        for (int i = 0; i < maxIterations; i++) {
            boolean isLastIteration = i == maxIterations - 1;

            // In each iteration find the nearest centroid for each record:
            for (Record record : records) {
                Centroid centroid = nearestCentroid(record, centroids, distance);
                assignToCluster(clusters, record, centroid);
            }
            // If assignments remain the same, then we have nothing else to do:
            boolean shouldTerminate = isLastIteration || clusters.equals(lastState);
            lastState = clusters;
            if (shouldTerminate) {
                break;
            }
            // Relocate centroid after each iteration:
            centroids = relocateCentroids(clusters);
            clusters = new HashMap<>();
        }
        return lastState;

    }

    /**
     * Generates k randomly placed centroids. First generate the possible value range
     * for each attribute. Then generate random coordinates in the [min, max] range
     * foe each attribute.
     *
     * @param records The data set.
     * @param k       Number of clusters.
     * @return Collection of randomly generated centroids.
     */
    public static List<Centroid> randomCentroids(List<Record> records, int k) {

        List<Centroid> centroids = new ArrayList<>();
        Map<String, Double> maxValues = new HashMap<>();
        Map<String, Double> minValues = new HashMap<>();

        for (Record record : records) {
            record.getFeatures().forEach((key, value) -> {
                // Compares the value with the current max and choose the bigger value:
                maxValues.compute(key, (k1, max) -> max == null || value > max ? value : max);

                // Compare the value with the current min and choose the smaller value:
                minValues.compute(key, (k2, min) -> min == null || value < min ? value : min);
            });
        }

        Set<String> attributes = records.stream().flatMap(e -> e.getFeatures().keySet().stream()).collect(toSet());
        for (int i = 0; i < k; i++) {
            Map<String, Double> coordinates = new HashMap<>();
            for (String attribute : attributes) {
                double max = maxValues.get(attribute);
                double min = minValues.get(attribute);
                coordinates.put(attribute, random.nextDouble() * (max - min) + min);
            }
            centroids.add(new Centroid(coordinates));
        }
        return centroids;
    }


    /**
     * Finds the nearest centroid given a data set.
     *
     * @param record    The data set
     * @param centroids The list of centroids.
     * @param distance  The distance calculator
     * @return A centroid.
     */
    public static Centroid nearestCentroid(Record record, List<Centroid> centroids, Distance distance) {

        double minDistance = Double.MAX_VALUE;
        Centroid nearest = null;

        for (Centroid centroid : centroids) {
            double currentDistance = distance.calculate(record.getFeatures(), centroid.getCoordinates());

            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                nearest = centroid;
            }
        }
        return nearest;
    }


    /**
     * Assign record to nearest centroid cluster.
     *
     * @param clusters The current cluster configuration.
     * @param record   The feature vector.
     * @param centroid The centroid.
     */
    public static void assignToCluster(Map<Centroid, List<Record>> clusters, Record record, Centroid centroid) {

        clusters.compute(centroid, (key, list) -> {
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(record);
            return list;
        });
    }


    /**
     * The average location of all assigned records.
     *
     * @param centroid The centroid.
     * @param records  The given list of features.
     * @return A Centroid.
     */
    public static Centroid average(Centroid centroid, List<Record> records) {
        if (records == null || records.isEmpty()) {
            return centroid;
        }

        Map<String, Double> average = centroid.getCoordinates();
        records.stream().flatMap(e -> e.getFeatures().keySet().stream()).forEach(k -> average.put(k, 0.0));

        for (Record record : records) {
            record.getFeatures().forEach((k, v) -> average.compute(k, (k1, currentValue) -> v + currentValue));
        }

        average.forEach((k, v) -> average.put(k, v / records.size()));

        return new Centroid(average);
    }


    /**
     * Iterates through all centroids, relocates them and
     * returns the new centroids.
     *
     * @param clusters The current cluster configuration.
     * @return Collection of a new and relocated centroids.
     */
    public static List<Centroid> relocateCentroids(Map<Centroid, List<Record>> clusters) {
        return clusters.entrySet().stream().map(e -> average(e.getKey(), e.getValue())).collect(toList());
    }


    /**
     * Checks necessary for optimal algorithm execution.
     *
     * @param records       The given data set.
     * @param k             The number of clusters.
     * @param distance      The distance calculator.
     * @param maxIterations Maximum number of iterations.
     */
    private static void applyPreconditions(List<Record> records, int k, Distance distance, int maxIterations) {

        if (records == null || records.isEmpty()) {
            throw new IllegalArgumentException("The data set cannot be empty.");
        }
        if (k <= 1) {
            throw new IllegalArgumentException("It doesn't make sense to have less or equal to one cluster.");
        }
        if (distance == null) {
            throw new IllegalArgumentException("The distance calculator is required.");
        }
        if (maxIterations <= 0) {
            throw new IllegalArgumentException("Max iterations should a positive number");
        }
    }


    /**
     * Will be used to generate random numbers.
     */
    private static final Random random = new Random();
}
