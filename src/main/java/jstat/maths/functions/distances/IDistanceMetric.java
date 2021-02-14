package jstat.maths.functions.distances;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
  * Interface for deriving simple distance metrics
  *
 */
public interface IDistanceMetric {

    /**
     * Returns the distance between the two points
     * @param p1 the first  point
     * @param p2 the second point
     */
    double calculate(final INDArray p1, final INDArray p2);

    /**
     * Initialize the min distance
     */
    double minValue();

    /**
     * Initialize the maximum distance
     */
    double maxValue();

    /**
     * Compare the two results
     */
    double compareMin(double r1, double r2);

    /**
     * Returns
     * -1 if r1 less than r2
     * 0  if r1 == r2
     * 1 if  r1 greater than r2
     */
    int compare(double r1, double r2);
}
