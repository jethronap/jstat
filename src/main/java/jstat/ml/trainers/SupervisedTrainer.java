package jstat.ml.trainers;

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

    public SupervisedTrainer(ISupervisedModel model, IOptimizer optimizer,  int numItrs, double tol){

        this.model = model;
        this.optimizer = optimizer;
        this.iterationController = new DefaultIterativeAlgorithmController(numItrs, tol);

    }

    public IterativeAlgorithmResult train(INDArray data, INDArray y){

        while(this.iterationController.continueIterations()){

            // do a model prediction
            INDArray result = model.predict(data);

            // do one step of the optimizer
            // this updates the coefficients of
            // the model
            optimizer.step(data, y);

            // check for convergence

            System.out.println("Iteration: "+this.iterationController.getCurrentIteration());
            //System.out.println("\tJold: "+jOld + " Jcur: " + jCurr);
            //System.out.println("\tError |Jcur-Jold|: "+ error);
            System.out.println("\tExit tolerance: "+iterationController.getExitTolerance());

        }

        IterativeAlgorithmResult reslt =  this.iterationController.getState();
        reslt.numThreadsUsed = 1;
        return reslt;
    }

    private ISupervisedModel model;
    private IOptimizer optimizer;
    private IterativAlgorithmController iterationController;
}
