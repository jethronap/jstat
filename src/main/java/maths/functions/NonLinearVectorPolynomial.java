package maths.functions;


import maths.IVector;
import maths.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * class that represents a possibly non-linear polynomial of the form
 * f = w0 + w1*X1^order_1 + w2*X2^order_2+...worder-1*Xorder-1^order_N-1
 * The constructor of this class accepts an argument that specifies
 * the term and the order this term has
 */

public class NonLinearVectorPolynomial implements IVectorRealFunction<IVector<Double>> {

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
    public Double evaluate(IVector<Double> input){

        if(input.size() != this.terms.size()){
            throw new IllegalArgumentException("Invalid number of coeffs. "+input.size()+" should be "+this.terms.size());
        }

        double rslt = 0.0;
        for (int i = 0; i < input.size(); i++) {
            rslt += this.terms.get(i).evaluate(input.get(i));
        }
        return rslt;
    }

    /**
     * Set the coefficients of the Polynomial
     */
    @Override
    public final void setCoeffs(IVector<Double> coeffs){

        this.setCoeffs(coeffs.toArray());
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
    public final Vector getCoeffs(){

        Vector rslt = new Vector(this.terms.size(), 0.0);

        for (int i = 0; i < rslt.size() ; i++) {

            rslt.set(i, this.terms.get(i).getCoeffs().get(0));
        }

        return rslt;
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
    public Vector gradidents(IVector<Double> data){

        if(data.size() != this.terms.size()){
            throw new IllegalArgumentException("Invalid data size "+data.size()+" should be equal to: "+this.terms.size());
        }

        Vector rslt = new Vector(data.size(), 0.0);

        for (int i = 0; i < rslt.size() ; i++) {

            rslt.set(i, this.terms.get(i).gradient(data.get(i)));
        }

        return rslt;
    }

    /**
     * Compute the gradients with respect to the coefficients
     */
    @Override
    public Vector coeffGradients(IVector<Double> data){

        Vector grads = new Vector(this.terms.size(), 0.0);

        for (int i = 0; i < grads.size(); i++) {
            grads.set(i, this.coeffGradient(i, data));
        }

        return grads;
    }

    /**
     * Returns the gradient with respect to the i-th coeff
     */
    @Override
    public double coeffGradient(int i, IVector<Double> data){

        return this.terms.get(i).coeffGradient(data.get(i));
    }


    /**
     * Returns the gradient with respect to the i-th coeff
     */
    @Override
    public double gradient(int i, IVector<Double> data){

        return this.terms.get(i).gradient(data.get(i));
    }


    /**
     * Returns the coeff-th coefficient
     */
    @Override
    public double getCoeff(int coeff){
        return this.terms.get(coeff).getCoeffs().get(0);
    }



    List<ScalarMonomial> terms;
}
