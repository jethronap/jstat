package jstat.ml.regression;


import jstat.maths.functions.LinearVectorPolynomial;

/**
 * Linear regression modelling
 */
public class LinearRegressor extends RegressorBase {

    /**
     * Constructor
     */
    public LinearRegressor(int numFeatures){
        super(new LinearVectorPolynomial(numFeatures));
    }

    /**
     * Constructor
     */
    public LinearRegressor(LinearVectorPolynomial hypothesis){
        super(hypothesis);
    }

}
