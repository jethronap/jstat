package jstat.maths.errorfunctions;


import jstat.maths.functions.IRegularizerFunction;
import jstat.maths.functions.IVectorRealFunction;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * The Sum Square Error or SSE is defined as
 * SSE =  Sum_{i = 1}^N (y_i - \hat{y}_i)^2
 *
 * The \hat{y} value is modeled after the IVectorRealFunction passed
 * to the object when instantiated
 */
public class SSEFunction implements ILossFunction {

    /**
     * Compute the SSE error over the two vectors
     */
    static public double error(INDArray y, INDArray yhat){

        if(y.size(0) != yhat.size(0)){
            throw new IllegalArgumentException("Invalid size of vectors ");
        }

        double rlst = 0.0;

        for(int i=0; i<y.size(0); ++i){

            double diff = y.getDouble(i) - yhat.getDouble(i);
            diff *= diff;
            rlst += diff;
        }
        return rlst;
    }

    /**
     * Constructor
     */
    public SSEFunction(IVectorRealFunction hypothesis ){

        if(hypothesis == null){
            throw new IllegalArgumentException("Hypothesis function cannot be null");
        }
        this.hypothesis = hypothesis;
    }
    /**
      * Constructor
     */
    public SSEFunction(IVectorRealFunction hypothesis, IRegularizerFunction regularizerFunction){

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
    public  double evaluate(INDArray data, INDArray labels){

        if(data.size(0) != labels.size(0)){
            throw new IllegalArgumentException("Invalid number of data points and labels vector size");
        }

        double result = 0.0;

        for(int rowIdx=0; rowIdx<data.size(0); ++rowIdx){

            INDArray row = data.getRow(rowIdx);
            double diff = labels.getDouble(rowIdx) - this.hypothesis.evaluate(row);
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
    public INDArray paramGradients(INDArray data, INDArray labels){


        INDArray gradients = Nd4j.zeros(this.hypothesis.numCoeffs());

        for(int rowIdx=0; rowIdx<data.size(0); ++rowIdx){

            INDArray row = data.getRow(rowIdx);

            double diff = (labels.getDouble(rowIdx) - this.hypothesis.evaluate(row));

            INDArray hypothesisGrads = this.hypothesis.coeffGradients(row);

            for(int coeff=0; coeff<this.hypothesis.numCoeffs(); ++coeff){
                double grad = gradients.getDouble(coeff) -2.0*diff*hypothesisGrads.getDouble(coeff);
                gradients.putScalar(coeff, grad);
            }
        }

        return gradients;
    }

    private IVectorRealFunction hypothesis;
    private IRegularizerFunction regularizerFunction;
}
