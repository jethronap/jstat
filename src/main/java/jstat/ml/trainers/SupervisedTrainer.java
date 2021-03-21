package jstat.ml.trainers;

import jstat.base.CommonConstants;
import jstat.maths.errorfunctions.ILossFunction;
import jstat.ml.ISupervisedModel;
import jstat.optimization.IOptimizer;
import jstat.utils.DefaultIterativeAlgorithmController;
import jstat.utils.IterativAlgorithmController;
import jstat.utils.IterativeAlgorithmResult;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 *  Utility class to wrap training for
 *  supervised learning
 */
public class SupervisedTrainer {

    public SupervisedTrainer(ISupervisedModel model, IOptimizer optimizer, ILossFunction loss, int numItrs, double tol){

        this.model = model;
        this.optimizer = optimizer;
        this.loss = loss;
        this.iterationController = new DefaultIterativeAlgorithmController(numItrs, tol);

    }

    public IterativeAlgorithmResult train(INDArray data, INDArray y){

        double previousLoss = Double.MAX_VALUE;
        while(this.iterationController.continueIterations()){

            // do a model prediction
            INDArray result = model.predict(data);

            // what is the loss
            double currentLoss = this.loss.evaluate(result, y);

            // do one step of the optimizer
            // this updates the coefficients of
            // the model
            optimizer.step(data, y);

            // check for convergence
            double absError = Math.abs(previousLoss - currentLoss);

            // update the residual of the controller
            this.iterationController.updateResidual(absError);

            // update the model parameters
            this.model.setParameters(optimizer.getOptimizedParameters());

            System.out.println(CommonConstants.INFO + " Iteration: " + this.iterationController.getCurrentIteration());
            System.out.println(CommonConstants.INFO + " Abs Error |Jcur-Jold|: "+ this.iterationController.getResidual());
            System.out.println(CommonConstants.INFO + " Exit tolerance: " + this.iterationController.getExitTolerance());
            previousLoss = currentLoss;
        }

        IterativeAlgorithmResult reslt =  this.iterationController.getState();
        reslt.numThreadsUsed = 1;
        return reslt;
    }

    private ISupervisedModel model;
    private IOptimizer optimizer;
    private ILossFunction loss;
    private IterativAlgorithmController iterationController;
}
