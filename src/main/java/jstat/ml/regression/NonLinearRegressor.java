package jstat.ml.regression;


import jstat.maths.functions.NonLinearVectorPolynomial;
import jstat.maths.functions.ScalarMonomial;

public class NonLinearRegressor extends RegressorBase {

    /**
     * Constructor
     */
    public NonLinearRegressor(ScalarMonomial...terms){
        super(new NonLinearVectorPolynomial(terms));
    }

    /**
     * Constructor
     */
    public NonLinearRegressor(NonLinearVectorPolynomial hypothesis){
        super(hypothesis);
    }

}
