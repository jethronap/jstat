package jstat.ml.regression;

import jstat.datasets.DenseMatrixSet;
import jstat.maths.functions.NonLinearVectorPolynomial;
import jstat.maths.functions.ScalarMonomial;

public class NonLinearRegressor<DataSetType extends DenseMatrixSet<Double>> extends RegressorBase<DataSetType, NonLinearVectorPolynomial > {

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
