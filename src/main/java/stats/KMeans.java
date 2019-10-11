package stats;

import stats.utils.Centroid;
import stats.utils.Distance;
import stats.utils.Record;

import java.util.List;
import java.util.Map;

/**
 * Implementation of K-Means Clustering Algorithm.
 */
public class KMeans {


    private KMeans() {
        throw new IllegalAccessError("This constructor should not be used");
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
