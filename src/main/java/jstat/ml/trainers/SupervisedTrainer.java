package jstat.ml.trainers;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 *  Utility class to wrap training for
 *  supervised learning
 */
public class SupervisedTrainer {

    public SupervisedTrainer(int numItrs, double tol){

        this.numItrs = numItrs;
        this.tolerance = tol;

    }

    public void train(final INDArray data, final INDArray y){

    }

    private int numItrs;
    private double tolerance;
}
