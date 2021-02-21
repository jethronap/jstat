package jstat.ml.regression;


import jstat.ml.ISupervisedModel;

import jstat.maths.functions.IVectorRealFunction;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;


/**
 * Base class for regression models. Implements ISupervisedModel
 * interface
 */
public class RegressorBase implements ISupervisedModel, IVectorRealFunction {

    /**
     * Evaluate the function on the given input
     */
    @Override
    public Double evaluate(INDArray data){
        return this.hypothesisFunction.evaluate(data);
    }

    /**
     * Returns the number of coefficients
     */
    @Override
    public int numCoeffs(){return (int)this.getParameters().size(0);}

    /**
     * Returns the coefficients of the vector function
     */
    @Override
    public INDArray getCoeffs(){return this.getParameters();}

    /**
     * Returns the i-th coefficient
     */
    @Override
    public double getCoeff(int i){return this.hypothesisFunction.getCoeff(i);}

    /**
     * Set the coefficients of the function
     */
    @Override
    public void setCoeffs(Double[] coeffs){this.hypothesisFunction.setCoeffs(coeffs);}

    /**
     * Set the coefficients of the function
     */
    @Override
    public void setCoeffs(double[] coeffs){this.hypothesisFunction.setCoeffs(coeffs);}

    /**
     * Returns the gradients with respect to the coefficients at the given data point
     */
    @Override
    public INDArray gradidents(INDArray data){return hypothesisFunction.gradidents(data);}

    /**
     * Compute the gradients with respect to the coefficients
     */
    @Override
    public INDArray coeffGradients(INDArray data){return this.hypothesisFunction.coeffGradients(data);}

    /**
     * Returns the i-th gradient
     */
    @Override
    public double gradient(int i, INDArray data){return this.hypothesisFunction.gradient(i, data);}

    /**
     * Returns the gradient with respect to the i-th coeff
     */
    @Override
    public double coeffGradient(int i, INDArray data){return this.hypothesisFunction.coeffGradient(i, data);}

    /**
     * Set the model paramters
     * @param parameters
     */
    @Override
    public void setParameters(INDArray parameters){
        this.hypothesisFunction.setCoeffs(parameters);
    }

    /**
     * Returns the parameters of the model
     * @return
     */
    @Override
    public INDArray getParameters(){
        return this.hypothesisFunction.getCoeffs();
    }

    /**
     * Predict the outputs over the given dataset
     */
    @Override
    public INDArray predict(INDArray dataSet){

        INDArray predictions = Nd4j.zeros(dataSet.size(0));

        for(int idx=0; idx<dataSet.size(0); ++idx){
            predictions.putScalar(idx, this.hypothesisFunction.evaluate(dataSet.getRow(idx)));
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
        this.hypothesisFunction = hypothesis;
    }


    /**
     * The hypothesis function assumed by the regressor
     */
    protected IVectorRealFunction hypothesisFunction;
}