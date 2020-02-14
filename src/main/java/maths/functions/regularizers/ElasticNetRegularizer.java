package maths.functions.regularizers;

import datastructs.IVector;
import maths.functions.IRegularizerFunction;
import maths.functions.IVectorRealFunction;

public class ElasticNetRegularizer implements IRegularizerFunction {

    /**
     * Constructor.
     */
    public ElasticNetRegularizer(double lambda1, double lambda2, int startCoeffs, IVectorRealFunction<IVector<Double>> hypothesis){

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

        IVector<Double> coeffs = hypothesis.getCoeffs();

        if(coeffs == null){
            throw new IllegalStateException("Hypothesis coefficients are null");
        }

        double sum1 = 0.0;
        double sum2 = 0.0;

        for(int c=startCoeffs; c<coeffs.size(); ++c){

            double coeff = coeffs.get(c);
            sum1 += Math.abs(coeff);
            sum2 += coeff*coeff;
        }

        return this.lambda1*sum1 + this.lambda2*sum2;
    }

    int startCoeffs;
    double lambda1;
    double lambda2;
    maths.functions.IVectorRealFunction<IVector<Double>> hypothesis;
}
