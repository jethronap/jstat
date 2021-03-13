package jstat.optimization;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class GradientDescent implements IOptimizer {

    /**
     * Constructor
     */
    public GradientDescent(GDInput input){

        if(input.parameters == null){
            throw new IllegalArgumentException("Model parameters are not set");
        }

        this.input = input;
    }

    /**
     * Returns the optimize parameters
     */
    @Override
    public INDArray getOptimizedParameters(){
        return this.input.parameters;
    }

    /**
     * Zero the computed gradients
     */
    public void zeroGradients(){
        lossFunctionGrads = Nd4j.zeros(lossFunctionGrads.size(0));
    }

    /**
     * Compute the gradients on the given labeled data set
     * @param data
     * @param y
     */
    public void computeGradients(INDArray data, INDArray y){
        lossFunctionGrads = this.input.lossFunction.paramGradients(data, y);
    }

    /**
     * Update the coefficients
     */
    public void updateParameters(){

        for(int c=0; c<this.input.parameters.size(0); ++c){
            double coeff = this.input.parameters.getDouble(c) - this.input.eta* lossFunctionGrads.getDouble(c);
            this.input.parameters.putScalar(c, coeff);
        }
    }



    /**
     * Optimize approximate function f on the given dataset and the
     * given labels. Derived classes specify the output
     */
    @Override
    public void step(INDArray data, INDArray y){

        // update the gradients
        computeGradients(data, y);

        // update the parameters
        updateParameters();

        // zero the gradients
        zeroGradients();

        System.out.println("Update parameters: " + this.input.parameters);

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
     * The gradients returned by the error function
     */
    INDArray lossFunctionGrads;
}
