package jstat.maths.functions.regularizers;

import jstat.datastructs.IVector;
import jstat.maths.functions.IRegularizerFunction;
import jstat.maths.functions.IVectorRealFunction;
import org.nd4j.linalg.api.ndarray.INDArray;

public class ElasticNetRegularizer implements IRegularizerFunction {

    /**
     * Constructor.
     */
    public ElasticNetRegularizer(double lambda1, double lambda2, int startCoeffs, IVectorRealFunction hypothesis){

        this.startCoeffs = startCoeffs;
        this.lambda1 = lambda1;
        this.lambda2 = lambda2;
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

        double sum1 = 0.0;
        double sum2 = 0.0;

        for(int c=startCoeffs; c<coeffs.size(0); ++c){

            double coeff = coeffs.getDouble(c);
            sum1 += Math.abs(coeff);
            sum2 += coeff*coeff;
        }

        return this.lambda1*sum1 + this.lambda2*sum2;
    }

    private int startCoeffs;
    private double lambda1;
    private double lambda2;
    private IVectorRealFunction hypothesis;
}
