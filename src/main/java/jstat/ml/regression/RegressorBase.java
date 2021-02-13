package jstat.ml.regression;


import jstat.ml.ISupervisedModel;
import jstat.optimization.IOptimizer;
import jstat.maths.functions.IVectorRealFunction;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class RegressorBase implements ISupervisedModel {


    /**
     * Set the model paramters
     * @param parameters
     */
    @Override
    public void setParameters(INDArray parameters){

    }

    @Override
    public INDArray getParameters(){
        return null;
    }

    /**
     * Predict the value for the given input
     */
    /*public double predict(INDArray y){
        return 0.0; //(double) this.hypothesisType.evaluate(y);
    }*/

    /**
     * Predict the outputs over the given dataset
     */
    public INDArray predict(INDArray dataSet){

        INDArray predictions = Nd4j.zeros(dataSet.size(0));

        for(int idx=0; idx<dataSet.size(0); ++idx){
            predictions.putScalar(idx, this.hypothesisType.evaluate(dataSet.getRow(idx)));
        }

        return predictions;
    }


    /**
     * Returns the errors over the given dataset with respect to the given labels
     */
    public INDArray getErrors(final INDArray dataSet, final INDArray y){

        if(y.size(1) != dataSet.size(0)){
            throw new IllegalArgumentException("Dataset number of rows: "+dataSet.size(0)+" not equal to "+y.size(1));
        }

        INDArray errs = Nd4j.zeros(y.size(1));

        for(int row = 0; row<dataSet.size(0); ++row){

            INDArray r = dataSet.getRow(row);
            double error = -1.0; //y.getScalar(row) - this.hypothesisType.evaluate(r);
            errs.putScalar(row , error);
        }

        return errs;
    }

    /**
     * Protected constructor. Set the hypothesis function
     * the regressor is using
     */
    protected RegressorBase(IVectorRealFunction hypothesis){
        this.hypothesisType = hypothesis;
    }


    /**
     * The hypothesis function assumed by the regressor
     */
    protected IVectorRealFunction hypothesisType;
}