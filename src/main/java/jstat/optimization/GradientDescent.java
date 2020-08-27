package jstat.optimization;

import jstat.utils.IterativeAlgorithmResult;
import jstat.maths.functions.IVectorRealFunction;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class GradientDescent implements ISupervisedOptimizer {

    /**
     * Constructor
     */
    public GradientDescent(GDInput input){

        this.input = input;
    }


    /**
     * Optimize approximate function f on the given dataset and the
     * given labels. Derived classes specify the output
     */
    @Override
    public <OutPutType> OutPutType  optimize(final INDArray data, final INDArray y, IVectorRealFunction f){

        // compute the value of f with the current weights
        double jOld = this.input.errF.evaluate(data, y);
        double jCurr = 0.0;

        INDArray coeffs = f.getCoeffs();

        while(this.input.iterationContorller.continueIterations()){

            //the gradients of the error function.
            INDArray jGrads = this.input.errF.gradients(data, y);

            // update the
            for(int c=0; c<coeffs.size(0); ++c){
                double coeff = coeffs.getDouble(c) -this.input.eta*jGrads.getDouble(c);
                coeffs.putScalar(c, coeff);
            }

            f.setCoeffs(coeffs);

            jCurr = this.input.errF.evaluate(data, y);
            double error = Math.abs(jOld-jCurr);
            this.input.iterationContorller.updateResidual(error);

            if(this.input.showIterations){

                System.out.println("BatchGD: iteration: "+this.input.iterationContorller.getCurrentIteration());
                System.out.println("\tJold: "+jOld + " Jcur: " + jCurr);
                System.out.println("\terror |Jcur-Jold|: "+ error);
                System.out.println("\texit tolerance: "+this.input.iterationContorller.getExitTolerance());
            }

            jOld = jCurr;
            jGrads = Nd4j.zeros(jGrads.size(0));
        }

        IterativeAlgorithmResult reslt =  this.input.iterationContorller.getState();
        reslt.numThreadsUsed = 1;
        return (OutPutType) reslt;
    }
    GDInput input;
}
