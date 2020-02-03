package ml.regression;

import optimization.ISupervisedOptimizer;
import maths.IVector;
import maths.DenseMatrixSet;
import maths.Vector;
import maths.functions.IVectorRealFunction;

public class RegressorBase<DataSetType extends DenseMatrixSet<Double>, HypothesisType extends IVectorRealFunction<IVector<Double>>> {



    /**
     * Train the regressor on the given dataset
     */
    public <OutputType> OutputType train(DataSetType dataSet, Vector y, ISupervisedOptimizer optimizer){
        return optimizer.optimize(dataSet, y, this.hypothesisType);
    }

    /**
     * Predict the value for the given input
     */
    public double predict(Vector y){
        return (double) this.hypothesisType.evaluate(y);
    }

    /**
     * Predict the outputs over the given dataset
     */
    public Vector predict(DataSetType dataSetType){

        Vector predictions = new Vector(dataSetType.m(), 0.0);

        for(int idx=0; idx<dataSetType.m(); ++idx){
            predictions.set(idx, this.hypothesisType.evaluate(dataSetType.getRow(idx)));
        }

        return predictions;
    }


    /**
     * Returns the errors over the given dataset with respect to the given labels
     */
    public Vector getErrors(final DataSetType dataSet, final Vector y){

        if(y.size() != dataSet.m()){
            throw new IllegalArgumentException("Dataset number of rows: "+dataSet.m()+" not equal to "+y.size());
        }

        Vector errs = new Vector(y.size(), 0.0);

        for(int row = 0; row<dataSet.m(); ++row){

            IVector<Double> r = dataSet.getRow(row);
            double error = y.get(row) - this.hypothesisType.evaluate(r);
            errs.set(row , error);
        }

        return errs;
    }

    /**
     * Protected constructor.
     */
    protected RegressorBase(HypothesisType hypothesis){
        this.hypothesisType = hypothesis;
    }


    /**
     * The hypothesis function assumed by the regressor
     */
    protected HypothesisType hypothesisType;
}