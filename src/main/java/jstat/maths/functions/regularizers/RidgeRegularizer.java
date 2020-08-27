package jstat.maths.functions.regularizers;

import jstat.maths.functions.IRegularizerFunction;
import jstat.maths.functions.IVectorRealFunction;
import org.nd4j.linalg.api.ndarray.INDArray;

public class RidgeRegularizer implements IRegularizerFunction {

    /**
     * Constructor.
     */
    public RidgeRegularizer(double lambda, int start_coeffs, IVectorRealFunction hypothesis){

        this.start_coeffs = start_coeffs;
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

        for(int c=start_coeffs; c<coeffs.size(0); ++c){

            double coeff = coeffs.getDouble(c);
            sum += coeff*coeff;

        }

        return lambda*sum;
    }

    /**
     *
     */
    private int start_coeffs;

    /**
     *
     */
    private double lambda;

    /**
     * The hypothesis function to regularize
     */
    private IVectorRealFunction hypothesis;
}
