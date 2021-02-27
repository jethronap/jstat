package jstat.maths.errorfunctions;

import jstat.maths.functions.IRegularizerFunction;
import jstat.maths.functions.IVectorRealFunction;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * The Mean Square Error or MSE is defined as
 * MSE = 1/N Sum_{i = 1}^N (y_i - \hat{y}_i)^2
 *
 * The \hat{y} value is modeled after the IVectorRealFunction passed
 * to the object when instantiated
 */
public class MSEFunction implements ILossFunction {

    /**
     * Constructor
     */
    public MSEFunction(IVectorRealFunction hypothesis ){

        this.hypothesis = hypothesis;
        this.regularizerFunction = null;
    }

    /**
     * Constructor
     */
    public MSEFunction(IVectorRealFunction hypothesis, IRegularizerFunction regularizerFunction ){

        this.hypothesis = hypothesis;
        this.regularizerFunction = regularizerFunction;
    }

    /**
     * Evaluate the error function using the given data, labels
     * @param data
     * @param labels
     * @return
     */
    @Override
    public double evaluate(INDArray data, INDArray labels){

        if(data.size(0) != labels.size(0)){
            throw new IllegalArgumentException("Invalid number of data points and labels vector size");
        }

        double result = 0.0;

        for(int idx=0; idx<data.size(0); ++idx){
            //INDArray row = data.getRow(rowIdx);
            double diff = labels.getDouble(idx) - data.getDouble(idx);//this.hypothesis.evaluate(row);
            diff *= diff;
            result += diff;
        }

        result /= data.size(0);

        if(regularizerFunction != null){
            result += regularizerFunction.evaluate(null);
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

            INDArray row =  data.getRow(rowIdx);

            double diff = (labels.getDouble(rowIdx) - this.hypothesis.evaluate(row));

            INDArray hypothesisGrads = this.hypothesis.coeffGradients(row);

            for(int coeff=0; coeff<this.hypothesis.numCoeffs(); ++coeff){
                double grad = gradients.getDouble(coeff) - (2.0/data.size(0))*diff*hypothesisGrads.getDouble(coeff);
                gradients.putScalar(coeff, grad);
            }
        }

        return gradients;
    }

    private IVectorRealFunction hypothesis;
    IRegularizerFunction regularizerFunction;
}
