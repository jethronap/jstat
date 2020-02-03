package maths.functions;

import maths.Vector;

/**
 * Class that models a function of the form f = a*X^order
 * where X is scalar
 */
public class ScalarMonomial implements IScalarRealFunction {


    /**
     * Constructor
     */
    public ScalarMonomial(int order, double a){
        this.a = a;
        this.order = order;
    }

    /**
     * Evaluate the monomila at the given input
     */
    @Override
    public Double evaluate(Double input){
        return this.a*Math.pow(input, this.order);
    }

    /**
     * Returns the number of coefficients
     */
    @Override
    public int numCoeffs(){return 1;}

    /**
     * Returns the coefficients of the vector function
     */
    @Override
    public Vector getCoeffs(){return new Vector(1, this.a);}

    /**
     * Set the coefficients of the function
     */
    @Override
    public void setCoeff(double coeff){this.a = coeff;}

    /**
     * Set the coefficients of the function
     */
    @Override
    public void setCoeffs(Double[] coeffs){
        this.setCoeff(coeffs[0]);
    }


    /**
     * Set the coefficients of the function
     */
    @Override
    public void setCoeffs(double[] coeffs){
        this.setCoeff(coeffs[0]);
    }

    @Override
    public double getCoeff(int i){
        if( i!=0 ){
            throw new IllegalArgumentException("A Scalar monomial has only one coefficient");
        }

        return this.a;
    }

    /**
     * Returns the gradient with respect to the i-th coeff
     */
    @Override
    public double gradient(double data){
        return this.a*this.order*Math.pow(data, this.order-1);
    }

    /**
     * Compute the gradient with respect to the coefficient at the given point
     */
    public double coeffGradient(double data){
        return Math.pow(data, this.order);
    }

    /**
     * The coefficient of the monomial
     */
    private double a;

    /**
     * The order of the monomial
     */
    private int order;
}
