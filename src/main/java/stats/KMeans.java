package stats;

import stats.utils.Cluster;
import stats.utils.Point;

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

    public KMeans(int k, int maxIterations) {
        this.k = k;
        this.maxIterations = maxIterations;
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
    private List points;
    private List clusters;


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
