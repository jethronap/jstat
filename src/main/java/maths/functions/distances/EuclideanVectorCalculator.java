package maths.functions.distances;

import datastructs.IVector;

/**
 * Compute Euclidean distance for vector-like datastructs
 */
public class EuclideanVectorCalculator<T> implements DistanceCalculator<IVector<T>, Double> {


    /**
     * Returns the distance between the two points
     * @param p1 the first  point
     * @param p2 the second point
     */
    @Override
    public Double calculate(final IVector<T> p1, final IVector<T> p2) {

        if(p1 == null || p2 == null){
            throw new NullPointerException("Either p1 or p2 is null");
        }

        if (p1.size() != p2.size()) {
            throw new IllegalStateException("Invalid sizes for IVector. " + p1.size() + " not equal to " + p2.size());
        }

        double rslt = 0.0;

        for (int i = 0; i < p1.size(); i++) {
            rslt += ((Double) p1.get(i) - (Double) p2.get(i)) * ((Double) p1.get(i) - (Double) p2.get(i));
        }

        return Math.sqrt(rslt);
    }

    /**
     * Initialize the min distance
     */
    @Override
    public Double minValue(){return Double.MIN_VALUE; }

    /**
     * Initialize the maximum distance
     */
    @Override
    public Double maxValue(){ return Double.MAX_VALUE; }

    /**
     * Compare the two results
     */
    @Override
    public Double compareMin(Double r1, Double r2){

        if(r1 < r2)
            return r1;

        return r2;
    }


    /**
     * Returns
     * -1 if r1 less than r2
     * 0  if r1 == r2
     * 1 if  r1 greater than r2
     */
    @Override
    public int compare(Double r1, Double r2){

        if(r1 < r2){
            return -1;
        }
        else if(r1 > r2){
            return 1;
        }

        return 0;
    }

}
