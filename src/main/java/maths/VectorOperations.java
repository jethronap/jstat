package maths;


import utils.ListMaths;

/**
 * Common operations on Vector
 */
public class VectorOperations {

    /**
     * Adds the two vectors and returns a vector that contains the result
     */
    public static Vector add(final Vector v1, final Vector v2){

        if(v1.size() != v2.size()){
            throw new IllegalStateException("v1 size not equal to v2 size");
        }

        Vector rslt = new Vector(v1.size(),0.0);

        for(int i=0; i<v1.size(); ++i){
            rslt.set(i, v1.get(i) + v2.get(i));
        }

        return rslt;
    }

    /**
     * Subtracts the two vectors and returns a vector that contains the result
     */
    public static Vector subtract(final Vector v1, final Vector v2){

        if(v1.size() != v2.size()){
            throw new IllegalStateException("v1 size not equal to v2 size");
        }

        Vector rslt = new Vector(v1.size(),0.0);

        for(int i=0; i<v1.size(); ++i){
            rslt.set(i, v1.get(i) - v2.get(i));
        }

        return rslt;
    }


    /**
      * Computes the dot product of the two vectors
     */
    public static double dotProduct(final Vector v1, final Vector v2){

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
    public static double l2Norm(final Vector v1){
        double dotProduct = VectorOperations.dotProduct(v1 ,v1);
        return StrictMath.sqrt(dotProduct);
    }

    /**
     * Computes the L1 norm of the vector
     */
    public static double l1Norm(final Vector v1){
        return ListMaths.absSum(v1.getRawData());
    }

    /**
     * Compute x = f1*v1 + f2*v2
     */
    public static final Vector scaleAndAdd(Vector v1, double f1, Vector v2, double f2){

        if(v1.size() != v2.size()){
            throw new IllegalStateException("v1 size not equal to v2 size");
        }

        Vector rslt = new Vector(v1.size(), 0.0);

        for(int i=0; i<rslt.size(); ++i){

            rslt.set(i, f1*v1.get(i) + f2*v2.get(i));
        }

        return rslt;

    }
}
