package jstat.maths.functions;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * class that represents a linear polynomial of the form
 * f = w0 + w1*X1 + w2*X2+...worder-1*Xorder-1
 */
public class LinearVectorPolynomial implements IVectorRealFunction {

    /**
      * Construct a vector polynomial: f = w0 + w1*X1 + w2*X2+...worder-1*Xorder-1
     */
    public LinearVectorPolynomial(int order, boolean withIntercept){

        this.withIntercept = withIntercept;

        if(withIntercept) {

            // we also need the constant coefficient
            this.coeffs = Nd4j.zeros(order + 1);
        }
        else{

            this.coeffs = Nd4j.zeros(order);
        }
    }


    /**
     * Returns true if the intercept is accounted in the model
     * @return
     */
    public boolean hasIntercept(){return this.withIntercept;}

    /**
     * Compute the value of the polynomial
     * @param input
     * @return
     */
    public Double evaluate(INDArray input){

        double result = 0;
        for(int i=0; i<this.coeffs.size(0); ++i){
            result += this.coeffs.getDouble(i)*input.getDouble(i);
        }

        INDArray resultArr = this.coeffs.mmul(input);
        return this.coeffs.mmul(input).getDouble(0);
    }

    /**
     * Set the coefficients of the Polynomial
     */
    public final void setCoeffs(INDArray coeffs){

        if(this.coeffs.size(0) != coeffs.size(0)){
            //this is a mismatch
        }

        this.coeffs = coeffs;
    }

    /**
     * Set the coefficients of the function
     */
    @Override
    public final void setCoeffs(Double[] coeffs){
        /*this.coeffs.set(coeffs);*/
    }

    /**
     * Set the coefficients of the function
     */
    @Override
    public void setCoeffs(double[] coeffs){ }

    /**
     * Returns the coefficients of the vector function
     */
    @Override
    public final INDArray getCoeffs(){
        return this.coeffs;
    }

    /**
     * Returns the number of coefficients
     */
    @Override
    public final int numCoeffs(){
        return (int) this.coeffs.size(0);
    }

    /**
     * Returns the gradients with respect to the coefficients at the given data point
     */
    @Override
    public INDArray gradidents(INDArray data){
        INDArray rslt = data;
        rslt.putScalar(0, 1.0);
        return rslt;
    }

    /**
     * Returns the gradient with respect to the i-th coeff
     */
    @Override
    public double gradient(int i, INDArray data){

        if(i==0){
            return 0.0;
        }

        //this is  a linear model with respect to
        //the weights so simply return the value of the feature
        //for the i-th weight
        return this.coeffs.getDouble(i);
    }

    /**
     * Returns the gradient with respect to the i-th coeff
     */
    @Override
    public double coeffGradient(int i, INDArray data){

        // this depends on whether we use intercept
        // or not
        if(i==0){
            return 1.0;
        }

        //this is  a linear model with respect to
        //the weights so simply return the value of the feature
        //for the i-th weight
        return data.getDouble(i);

    }

    /**
     * Compute the gradients with respect to the coefficients
     */
    @Override
    public INDArray coeffGradients(INDArray data){
        INDArray grads = Nd4j.zeros(this.coeffs.size(0));

        for (int i = 0; i < grads.size(0); i++) {
            grads.putScalar(i, this.coeffGradient(i, data));
        }

        return grads;
    }

    /**
     * Returns the coeff-th coefficient
     */
    @Override
    public double getCoeff(int coeff){
        return this.coeffs.getDouble(coeff);
    }

    /**
     * The coefficients of the vector
     */
    private INDArray coeffs;

    /**
     * Flag indicating that the interception
     * term is used
     */
    private boolean withIntercept;

}
