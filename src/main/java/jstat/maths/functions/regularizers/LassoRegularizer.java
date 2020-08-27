package jstat.maths.functions.regularizers;

import jstat.maths.functions.IRegularizerFunction;
import jstat.maths.functions.IVectorRealFunction;
import org.nd4j.linalg.api.ndarray.INDArray;

public class LassoRegularizer implements IRegularizerFunction {

    /**
     * Constructor.
     */
    public LassoRegularizer(double lambda, int startCoeffs, IVectorRealFunction hypothesis){

        this.startCoeffs = startCoeffs;
        this.lambda = lambda;
        this.hypothesis = hypothesis;
    }

    /**
     * Returns the value of the regularizer
     */
    @Override
    public Double evaluate(Void input){

        INDArray coeffs = hypothesis.getCoeffs();

        if(coeffs == null){
            throw new IllegalStateException("Hypothesis coefficients are null");
        }

        double sum = 0.0;

        for(int c=startCoeffs; c<coeffs.size(0); ++c){

            double coeff = coeffs.getDouble(c);
            sum += Math.abs(coeff);
        }

        return lambda*sum;
    }


    int startCoeffs;
    double lambda;
    IVectorRealFunction hypothesis;
}
