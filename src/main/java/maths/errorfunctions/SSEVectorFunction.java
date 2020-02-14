package maths.errorfunctions;


import datasets.VectorDouble;
import datastructs.I2DDataSet;
import datastructs.IVector;
import maths.functions.IRegularizerFunction;
import maths.functions.IVectorRealFunction;

/**
 * The Sum Square Error or SSE is defined as
 * SSE =  Sum_{i = 1}^N (y_i - \hat{y}_i)^2
 *
 * The \hat{y} value is modeled after the IVectorRealFunction passed
 * to the object when instantiated
 */
public class SSEVectorFunction implements IVectorErrorRealFunction {

    /**
     * Compute the SSE error over the two vectors
     */
    static public double error(VectorDouble y, VectorDouble yhat){

        if(y.size() != yhat.size()){
            throw new IllegalArgumentException("Invalid size of vectors ");
        }

        double rlst = 0.0;

        for(int i=0; i<y.size(); ++i){

            double diff = y.get(i) - yhat.get(i);
            diff *= diff;
            rlst += diff;
        }
        return rlst;
    }

    /**
     * Constructor
     */
    public SSEVectorFunction(IVectorRealFunction<IVector<Double>> hypothesis ){

        if(hypothesis == null){
            throw new IllegalArgumentException("Hypothesis function cannot be null");
        }
        this.hypothesis = hypothesis;
    }
    /**
      * Constructor
     */
    public SSEVectorFunction(IVectorRealFunction<IVector<Double>> hypothesis, IRegularizerFunction regularizerFunction){

        if(hypothesis == null){
            throw new IllegalArgumentException("Hypothesis function cannot be null");
        }
        this.hypothesis = hypothesis;
        this.regularizerFunction = regularizerFunction;
    }

    /**
     * Evaluate the error function using the given data, labels
     */
    @Override
    public <DataSetType extends I2DDataSet> double evaluate(DataSetType data, VectorDouble labels){

        if(data.m() != labels.size()){
            throw new IllegalArgumentException("Invalid number of data points and labels vector size");
        }

        double result = 0.0;

        for(int rowIdx=0; rowIdx<data.m(); ++rowIdx){

            VectorDouble row = (VectorDouble) data.getRow(rowIdx);
            double diff = labels.get(rowIdx) - this.hypothesis.evaluate(row);
            diff *= diff;
            result += diff;
        }

        if(this.regularizerFunction != null){
            result += this.regularizerFunction.evaluate(null);
        }

        return result;
    }

    /**
     * Returns the gradients on the given data
     */
    @Override
    public <DataSetType extends I2DDataSet> VectorDouble gradients(DataSetType data, VectorDouble labels){


        VectorDouble gradients = new VectorDouble(this.hypothesis.numCoeffs(), 0.0);

        for(int rowIdx=0; rowIdx<data.m(); ++rowIdx){

            VectorDouble row = (VectorDouble) data.getRow(rowIdx);

            double diff = (labels.get(rowIdx) - this.hypothesis.evaluate(row));

            IVector<Double> hypothesisGrads = this.hypothesis.coeffGradients(row);

            for(int coeff=0; coeff<this.hypothesis.numCoeffs(); ++coeff){
                gradients.add(coeff, -2.0*diff*hypothesisGrads.get(coeff));
            }
        }

        return gradients;
    }

    private IVectorRealFunction<IVector<Double>> hypothesis;
    private IRegularizerFunction regularizerFunction;
}
