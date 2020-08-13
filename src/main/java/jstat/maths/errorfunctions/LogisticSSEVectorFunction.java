package jstat.maths.errorfunctions;

import jstat.base.CommonConstants;
import jstat.datasets.VectorDouble;
import jstat.datastructs.I2DDataSet;
import jstat.datastructs.IVector;
import jstat.maths.functions.IVectorRealFunction;

/**
 * LogisticSSEVectorFunction implements
 * J = \sum
 */
public class LogisticSSEVectorFunction implements IVectorErrorRealFunction {

    /**
     * Constructor
     */
    public LogisticSSEVectorFunction(IVectorRealFunction<IVector<Double>> hypothesis ){

        if(hypothesis == null){
            throw new IllegalArgumentException("Hypothesis function cannot be null");
        }
        this.hypothesis = hypothesis;
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
            double y = labels.get(rowIdx);

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
}
