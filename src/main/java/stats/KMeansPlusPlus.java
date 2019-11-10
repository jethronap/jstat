package stats;

import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;
import org.apache.commons.math3.ml.distance.EuclideanDistance;

import java.util.ArrayList;
import java.util.List;

public class KMeansPlusPlus {

    public void calculate() {
        List<DoublePoint> points = load(data);
        KMeansPlusPlusClusterer<DoublePoint> clusterer =
                new KMeansPlusPlusClusterer<>(k, maxIterations, euclideanDistance);
        List<CentroidCluster<DoublePoint>> clusters = clusterer.cluster(points);

        /**
        for (CentroidCluster<DoublePoint> cluster: clusters) {
            System.out.println(cluster.getPoints());
        }
        */
    }

    private static List<DoublePoint> load(double[][] data) {
        List<DoublePoint> points = new ArrayList<>();

        for (int i = 0; i < data.length ; i++) {
            points.add(new DoublePoint(data[i]));
        }
        return points;
    }


    private double[][] data;


    private int numberOfPoints;


    /**
     * Number of Clusters
     */
    private int k;


    private int maxIterations;

    private EuclideanDistance euclideanDistance = new EuclideanDistance();
}
