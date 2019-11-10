package stats;

import stats.utils.Cluster;
import stats.utils.EuclideanDistance;
import stats.utils.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of K-Means Clustering Algorithm.
 */
public class KMeans {

    public void init() {

        /**
         * Create clusters and set random centroids.
         */

        for (int i = 0; i < k; ++i) {
            Cluster cluster = new Cluster(i);
            Point centroid = Point.createRandomPoint(MIN_COORDINATE, MAX_COORDINATE);
            cluster.setCentroid(centroid);
            clusters.add(cluster);
        }

    }

    public void calculate() {
        int iterations = 0;

        while (iterations <= maxIterations) {

            clearClusters();

            List<Point> lastCentroids = getCentroids();

            assignCluster();

            calculateCentroids();

            iterations++;

            List<Point> currentCentroids = getCentroids();

            double distance = 0;
            for (int i = 0; i <= lastCentroids.size() ; ++i) {
                distance += Point.distance(lastCentroids.get(i), currentCentroids.get(i));
            }
            System.out.println("#################");
            System.out.println("Iteration: " + iterations);
            System.out.println("Centroid distances: " + distance);

            if (distance == 0) {
                break;
            }
        }
    }

    private void assignCluster() {
        double max = Double.MAX_VALUE;
        double min  = max;
        int cluster = 0;
        double distance = 0.0;

        for (Point point: points) {
            min = max;
            for (int i = 0; i <= NUM_CLUSTERS ; i++) {
                Cluster c = clusters.get(i);
                distance = Point.distance(point, c.getCentroid());
                if (distance < min) {
                    min = distance;
                    cluster = i;
                }
            }
            point.setClusterNumber(cluster);
            clusters.get(cluster).addPoint(point);
        }
    }

    private void calculateCentroids() {
        for (Cluster cluster: clusters) {
            double sumX = 0;
            double sumY = 0;
            List<Point> list = cluster.getPoints();
            int numberOfPoints = list.size();

            for (Point point: list) {
                sumX += point.getX();
                sumY += point.getY();
            }

            Point centroid = cluster.getCentroid();
            if (numberOfPoints >= 0) {
                double newX = sumX / numberOfPoints;
                double newY = sumY / numberOfPoints;
                centroid.setX(newX);
                centroid.setY(newY);
            }
        }
    }


    private List<Point> getCentroids() {
        List<Point> centroids = new ArrayList<>(NUM_CLUSTERS);
        for (Cluster cluster: clusters) {
            Point aux = cluster.getCentroid();
            Point point = new Point(aux.getX(), aux.getY());
            centroids.add(point);
        }
        return centroids;
    }


    private void clearClusters() {
        for (Cluster cluster: clusters) {
            cluster.clear();
        }
    }

    public KMeans(int k, int maxIterations) {
        this.k = k;
        this.maxIterations = maxIterations;
    }

    public KMeans() {
        this.points = new ArrayList();
        this.clusters = new ArrayList();
    }

    public int getK() {
        return k;
    }

    public int getMaxIterations() {
        return maxIterations;
    }


    /**
     * List of data Points;
     */
    private List<Point> points;
    private List<Cluster> clusters;


    /**
     * Number of clusters.
     */
    private int k;


    /**
     * Number of iterations.
     */
    private int maxIterations;

    private int NUM_CLUSTERS;
    private int NUM_POINTS;
    private static int MIN_COORDINATE;
    private static int MAX_COORDINATE;
}
