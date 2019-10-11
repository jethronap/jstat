package stats;

import stats.utils.Centroid;
import stats.utils.Distance;
import stats.utils.Record;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

/**
 * Implementation of K-Means Clustering Algorithm.
 */
public class KMeans {


    private KMeans() {
        throw new IllegalAccessError("This constructor should not be used");
    }


    /**
     * Generates k randomly placed centroids. First generate the possible value range
     * for each attribute. Then generate random coordinates in the [min, max] range
     * foe each attribute.
     * @param records The data set.
     * @param k Number of clusters.
     * @return Collection of randomly generated centroids.
     */
    public static List<Centroid> randomCentroids(List<Record> records, int k) {

        List<Centroid> centroids = new ArrayList<>();
        Map<String, Double> maxValues = new HashMap<>();
        Map<String, Double> minValues = new HashMap<>();

        for (Record record : records) {
            record.getFeatures().forEach((key, value) ->{
                // compares the value with the current max and choose the bigger value:
                maxValues.compute(key, (k1, max) -> max == null || value > max ? value : max);

                // compare the value with the current min and choose the smaller value:
                minValues.compute(key, (k2, min) -> min == null || value < min ? value : min);
            });
        }

        Set<String> attributes = records.stream().flatMap(e -> e.getFeatures().keySet().stream()).collect(toSet());
        for (int i = 0; i < k; i++) {
            Map<String, Double> coordinates = new HashMap<>();
            for (String attribute: attributes) {
                double max = maxValues.get(attribute);
                double min = minValues.get(attribute);
            }
            centroids.add(new Centroid(coordinates));
        }
        return centroids;
    }

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
}