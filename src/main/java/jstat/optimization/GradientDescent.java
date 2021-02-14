package jstat.optimization;

import jstat.utils.IterativeAlgorithmResult;
import jstat.maths.functions.IVectorRealFunction;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class GradientDescent implements IOptimizer {

    /**
     * Constructor
     */
    public GradientDescent(GDInput input){

        this.input = input;
    }


    public void zeroGradients(){
        errorFunctionGrads = Nd4j.zeros(errorFunctionGrads.size(0));
    }

    public void compute_gradients(INDArray data, INDArray y){
        errorFunctionGrads = this.input.errF.gradients(data, y);
    }

    /**
     * Update the coefficients
     */
    public void updateParameters(){

        for(int c=0; c<this.parameters.size(0); ++c){
            double coeff = parameters.getDouble(c) - this.input.eta*errorFunctionGrads.getDouble(c);
            parameters.putScalar(c, coeff);
        }

    }


    /**
     * Optimize approximate function f on the given dataset and the
     * given labels. Derived classes specify the output
     */
    @Override
    public void step(INDArray data, INDArray y){

        // update the gradients
        compute_gradients(data, y);

        // update the parameters
        updateParameters();

        // compute the value of f with the current weights
        /*double jOld = this.input.errF.evaluate(data, y);
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
            this.input.iterationContorller.updateResidual(error);*/

            /*if(this.input.showIterations){

                System.out.println("BatchGD: iteration: "+this.input.iterationContorller.getCurrentIteration());
                System.out.println("\tJold: "+jOld + " Jcur: " + jCurr);
                System.out.println("\terror |Jcur-Jold|: "+ error);
                System.out.println("\texit tolerance: "+this.input.iterationContorller.getExitTolerance());
            }

            jOld = jCurr;
            jGrads = Nd4j.zeros(jGrads.size(0));
        }*/

        /*IterativeAlgorithmResult reslt =  this.input.iterationContorller.getState();
        reslt.numThreadsUsed = 1;
        return (OutPutType) reslt;*/
    }

    /**
     * The input to the optimizer
     */
    private GDInput input;

    /**
     * The parameters to optimize
     */
    private INDArray parameters;

    /**
     * The gradients returned by the
     */
    INDArray errorFunctionGrads;
}
