package jstat.optimization;

import jstat.maths.errorfunctions.ILossFunction;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * Input class for Gradient Descent algorithm
 */
public class GDInput{

    /**
     * Flag indicating if information messages should be printed
     * as the algorithm executes
     */
    public boolean showIterations = true;

    /**
     * The learning rate used
     */
    public double eta = 0.01;

    /**
     * The error function used
     */
    public ILossFunction lossFunction;

    /**
     * The model parameters to optimize
     */
    public INDArray parameters;
}
