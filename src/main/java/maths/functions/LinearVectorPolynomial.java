package maths.functions;

import maths.IVector;
import maths.Vector;
import maths.VectorOperations;

/**
 * class that represents a linear polynomial of the form
 * f = w0 + w1*X1 + w2*X2+...worder-1*Xorder-1
 */
public class LinearVectorPolynomial implements IVectorRealFunction<IVector<Double>> {

    /**
      * Construct a vector polynomial: f = w0 + w1*X1 + w2*X2+...worder-1*Xorder-1
     */
    public LinearVectorPolynomial(int order){

        // we also need the constant coefficient
        this.coeffs = new Vector(order + 1, 0.0);
    }


    public Double evaluate(Vector input){
        return VectorOperations.dotProduct(this.coeffs, input);
    }


    @Override
    public Double evaluate(IVector<Double> input){
        return this.evaluate((Vector) input);
    }

    /**
     * Set the coefficients of the Polynomial
     */
    public final void setCoeffs(Vector coeffs){
        this.coeffs = coeffs;
    }

    /**
     * Set the coefficients of the function
     */
    @Override
    public final void setCoeffs(Double[] coeffs){
        this.coeffs.set(coeffs);
    }

    /**
     * Set the coefficients of the function
     */
    @Override
    public void setCoeffs(double[] coeffs){
        this.coeffs.set(coeffs);
    }

    /**
     * Returns the coefficients of the vector function
     */
    @Override
    public final Vector getCoeffs(){
        return this.coeffs;
    }

    /**
     * Returns the number of coefficients
     */
    @Override
    public final int numCoeffs(){
        return this.coeffs.size();
    }

    /**
     * Returns the gradients with respect to the coefficients at the given data point
     */
    @Override
    public Vector gradidents(IVector<Double> data){
        Vector rslt = new Vector(data);
        rslt.set(0, 1.0);
        return rslt;
    }

    /**
     * Returns the gradient with respect to the i-th coeff
     */
    @Override
    public double gradient(int i, IVector<Double> data){

        if(i==0){
            return 0.0;
        }

        //this is  a linear model with respect to
        //the weights so simply return the value of the feature
        //for the i-th weight
        return this.coeffs.get(i);
    }

    /**
     * Returns the gradient with respect to the i-th coeff
     */
    @Override
    public double coeffGradient(int i, IVector<Double> data){

        if(i==0){
            return 1.0;
        }

        //this is  a linear model with respect to
        //the weights so simply return the value of the feature
        //for the i-th weight
        return data.get(i);

    }

    /**
     * Compute the gradients with respect to the coefficients
     */
    @Override
    public Vector coeffGradients(IVector<Double> data){
        Vector grads = new Vector(this.coeffs.size(), 0.0);

        for (int i = 0; i < grads.size(); i++) {
            grads.set(i, this.coeffGradient(i, data));
        }

        return grads;
    }

    /**
     * Returns the coeff-th coefficient
     */
    @Override
    public double getCoeff(int coeff){
        return this.coeffs.get(coeff);
    }


    /**
     * The coefficients of the vector
     */
    private Vector coeffs;

}
