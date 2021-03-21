package jstat.maths.errorfunctions;

import jstat.base.CommonConstants;
import jstat.maths.functions.IVectorRealFunction;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * LogisticSSEVectorFunction implements
 * J = \sum
 */
public class LogisticSSEFunction implements ILossFunction {

    /**
     * Constructor
     */
    public LogisticSSEFunction(IVectorRealFunction hypothesis ){

        if(hypothesis == null){
            throw new IllegalArgumentException("Hypothesis function cannot be null");
        }
        this.hypothesis = hypothesis;
    }

    /**
     * Evaluate the error function using the given data, labels
     */
    @Override
    public double evaluate(INDArray data, INDArray labels){

        if(data.size(0) != labels.size(0)){
            throw new IllegalArgumentException("Invalid number of data points and labels vector size");
        }

        double result = 0.0;

        for(int rowIdx=0; rowIdx<data.size(0); ++rowIdx){

            INDArray row =  data.getRow(rowIdx);
            double y = labels.getDouble(rowIdx);

            double hypothesisValue = this.hypothesis.evaluate(row);

            //h is close to one
            if(Math.abs(hypothesisValue)  - 1.0 < CommonConstants.getTol()){

                //we plug a large error contribution if y is anything than one
                if( y != 1.){
                    result += 1.0;
                }
            }
            else if(Math.abs(hypothesisValue) < CommonConstants.getTol()){

                // std::cout<<" log_h infinity"<<std::endl;
                //hval is zero. we only get contribution
                //if the label is not zero as well
                if( y > CommonConstants.getTol()){
                    result += 1.0;
                }
            }
            else{

                //do it normally
                //calculate the logarithms and check if they are
                //infinite or nana
                double log_one_minus_h = Math.log(1. - hypothesisValue);
                double log_h = Math.log(hypothesisValue);
                result += y*log_h +(1.-y)*log_one_minus_h;
            }
        }
        return -result;
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
                double grad = gradients.getDouble(coeff) -2.0*diff*hypothesisGrads.getDouble(coeff);
                gradients.putScalar(coeff, grad);
            }
        }

        return gradients;
    }



    private IVectorRealFunction hypothesis;
}
