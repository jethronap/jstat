package jstat.optimization;

import jstat.datasets.VectorDouble;
import jstat.utils.IterativeAlgorithmResult;
import jstat.datastructs.I2DDataSet;
import jstat.datastructs.IVector;
import jstat.maths.functions.IVectorRealFunction;

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
    public <OutPutType, DataSetType extends I2DDataSet<IVector<Double>>> OutPutType  optimize(final DataSetType data, final VectorDouble y, IVectorRealFunction f){

        // compute the value of f with the current weights
        double jOld = this.input.errF.evaluate(data, y);
        double jCurr = 0.0;

        IVector<Double> coeffs = f.getCoeffs();

        while(this.input.iterationContorller.continueIterations()){

            //the gradients of the error function.
            VectorDouble jGrads = this.input.errF.gradients(data, y);

            // update the
            for(int c=0; c<coeffs.size(); ++c){
                coeffs.add(c, -this.input.eta*jGrads.get(c));
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
            jGrads.zero();
        }

        IterativeAlgorithmResult reslt =  this.input.iterationContorller.getState();
        reslt.numThreadsUsed = 1;
        return (OutPutType) reslt;
    }
    GDInput input;
}
