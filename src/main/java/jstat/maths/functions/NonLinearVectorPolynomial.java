package jstat.maths.functions;


import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.ArrayList;
import java.util.List;

/**
 * class that represents a possibly non-linear polynomial of the form
 * f = w0 + w1*X1^order_1 + w2*X2^order_2+...worder-1*Xorder-1^order_N-1
 * The constructor of this class accepts an argument that specifies
 * the term and the order this term has
 */

public class NonLinearVectorPolynomial implements IVectorRealFunction {

    /**
     * Constructor
     */
    public NonLinearVectorPolynomial(ScalarMonomial...terms){
        this.terms = new ArrayList<>();

        for (ScalarMonomial monomial: terms) {
            this.terms.add(monomial);
        }
    }


    @Override
    public Double evaluate(INDArray input){

        if(input.size(0) != this.terms.size()){
            throw new IllegalArgumentException("Invalid number of coeffs. "+input.size(0)+" should be "+this.terms.size());
        }

        double rslt = 0.0;
        for (int i = 0; i < input.size(0); i++) {
            rslt += this.terms.get(i).evaluate(input.getDouble(i));
        }
        return rslt;
    }


    /**
     * Set the coefficients of the function
     */
    @Override
    public final void setCoeffs(Double[] coeffs){

        if(coeffs.length != this.terms.size()){
            throw new IllegalArgumentException("Invalid number of coeffs. "+coeffs.length+" should be "+this.terms.size());
        }

        for (int i = 0; i < this.terms.size() ; i++) {
            this.terms.get(i).setCoeff(coeffs[i]);
        }
    }

    /**
     * Set the coefficients of the function
     */
    @Override
    public void setCoeffs(double[] coeffs){

        if(coeffs.length != this.terms.size()){
            throw new IllegalArgumentException("Invalid number of coeffs. "+coeffs.length+" should be "+this.terms.size());
        }

        for (int i = 0; i < this.terms.size() ; i++) {
            this.terms.get(i).setCoeff(coeffs[i]);
        }
    }

    /**
     * Returns the coefficients of the vector function
     */
    @Override
    public final INDArray getCoeffs(){

        INDArray result = Nd4j.zeros(this.terms.size());

        for (int i = 0; i < result.size(0) ; i++) {

            result.putScalar(i, this.terms.get(i).getCoeffs().getDouble(0));
        }

        return result;
    }

    /**
     * Returns the number of coefficients
     */
    @Override
    public final int numCoeffs(){
        return this.terms.size();
    }

    /**
     * Returns the gradients with respect to the coefficients at the given data point
     */
    @Override
    public INDArray gradidents(INDArray data){

        if(data.size(0) != this.terms.size()){
            throw new IllegalArgumentException("Invalid data size "+data.size(0)+" should be equal to: "+this.terms.size());
        }

        INDArray rslt = Nd4j.zeros(data.size(0));

        for (int i = 0; i < rslt.size(0) ; i++) {

            rslt.putScalar(i, this.terms.get(i).gradient(data.getDouble(i)));
        }

        return rslt;
    }

    /**
     * Compute the gradients with respect to the coefficients
     */
    @Override
    public INDArray coeffGradients(INDArray data){

        INDArray grads = Nd4j.zeros(this.terms.size());

        for (int i = 0; i < grads.size(0); i++) {
            grads.putScalar(i, this.coeffGradient(i, data));
        }

        return grads;
    }

    /**
     * Returns the gradient with respect to the i-th coeff
     */
    @Override
    public double coeffGradient(int i, INDArray data){

        return this.terms.get(i).coeffGradient(data.getDouble(i));
    }


    /**
     * Returns the gradient with respect to the i-th coeff
     */
    @Override
    public double gradient(int i, INDArray data){
        return this.terms.get(i).gradient(data.getDouble(i));
    }


    /**
     * Returns the coeff-th coefficient
     */
    @Override
    public double getCoeff(int coeff){
        return this.terms.get(coeff).getCoeffs().getDouble(0);
    }


    /**
     * The terms of the polynomial
     */
    private List<ScalarMonomial> terms;
}
