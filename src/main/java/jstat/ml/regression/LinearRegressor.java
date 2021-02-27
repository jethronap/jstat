package jstat.ml.regression;


import jstat.maths.functions.LinearVectorPolynomial;

/**
 * Linear regression modelling
 */
public class LinearRegressor extends RegressorBase {

    /**
     * Constructor
     */
    public LinearRegressor(int numFeatures, boolean withIntercept){
            super(new LinearVectorPolynomial(numFeatures, withIntercept));
            super.withIntercept = withIntercept;
    }

    /**
     * Constructor
     */
    public LinearRegressor(LinearVectorPolynomial hypothesis){
        super(hypothesis);
        super.withIntercept = hypothesis.hasIntercept();
    }

}
