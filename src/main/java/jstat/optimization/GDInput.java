package jstat.optimization;

import jstat.utils.IterativAlgorithmController;
import jstat.maths.errorfunctions.IVectorErrorRealFunction;

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
    public double eta;

    /**
     * Use momentum implementation
     */
    public boolean useMomentum=false;

    /**
     * The error function used
     */
    public IVectorErrorRealFunction errF;

    /**
     * The class that controls the iterations of the algorithm
     */
    public IterativAlgorithmController iterationContorller;
}
