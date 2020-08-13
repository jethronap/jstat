package jstat.maths;


import jstat.datasets.VectorDouble;
import jstat.datastructs.IVector;
import jstat.utils.ListMaths;

/**
 * Common operations on Vector
 */
public class VectorOperations {

    /**
     * Adds the two vectors and returns a vector that contains the result
     */
    public static VectorDouble add(final VectorDouble v1, final VectorDouble v2){

        if(v1.size() != v2.size()){
            throw new IllegalStateException("v1 size not equal to v2 size");
        }

        VectorDouble rslt = new VectorDouble(v1.size(),0.0);

        for(int i=0; i<v1.size(); ++i){
            rslt.set(i, v1.get(i) + v2.get(i));
        }

        return rslt;
    }

    /**
     * Subtracts the two vectors and returns a vector that contains the result
     */
    public static VectorDouble subtract(final VectorDouble v1, final VectorDouble v2){

        if(v1.size() != v2.size()){
            throw new IllegalStateException("v1 size not equal to v2 size");
        }

        VectorDouble rslt = new VectorDouble(v1.size(),0.0);

        for(int i=0; i<v1.size(); ++i){
            rslt.set(i, v1.get(i) - v2.get(i));
        }

        return rslt;
    }


    /**
      * Computes the dot product of the two vectors
     */
    public static double dotProduct(final VectorDouble v1, final VectorDouble v2){

        if(v1.size() != v2.size()){
            throw new IllegalStateException("v1 size not equal to v2 size");
        }

        double rslt = 0.0;

        for(int i=0; i<v1.size(); ++i){
            rslt += v1.get(i)*v2.get(i);
        }

        return rslt;
    }


    /**
     * Computes the dot product of the two vectors
     */
    public static double dotProduct(final IVector<Double> v1, final IVector<Double> v2){

        if(v1.size() != v2.size()){
            throw new IllegalStateException("v1 size not equal to v2 size");
        }

        double rslt = 0.0;

        for(int i=0; i<v1.size(); ++i){
            rslt += v1.get(i)*v2.get(i);
        }

        return rslt;
    }

    /**
      * Computes the L2 norm of the vector
     */
    public static double l2Norm(final VectorDouble v1){
        double dotProduct = VectorOperations.dotProduct(v1 ,v1);
        return StrictMath.sqrt(dotProduct);
    }

    /**
     * Computes the L1 norm of the vector
     */
    public static double l1Norm(final VectorDouble v1){
        return ListMaths.absSum(v1.getRawData());
    }

    /**
     * Compute x = f1*v1 + f2*v2
     */
    public static final VectorDouble scaleAndAdd(VectorDouble v1, double f1, VectorDouble v2, double f2){

        if(v1.size() != v2.size()){
            throw new IllegalStateException("v1 size not equal to v2 size");
        }

        VectorDouble rslt = new VectorDouble(v1.size(), 0.0);

        for(int i=0; i<rslt.size(); ++i){

            rslt.set(i, f1*v1.get(i) + f2*v2.get(i));
        }

        return rslt;

    }
}
