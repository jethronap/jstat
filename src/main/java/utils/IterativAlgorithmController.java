package utils;

public interface IterativAlgorithmController {

    /**
     * Returns true if the iterations of the algorithm should be continued
     */
    boolean continueIterations();

    /**
     * Returns the current iteration index
     */
    int getCurrentIteration();

    /**
     * Update residual
     */
    void updateResidual(double res);

    /**
     * Returns the exit tolerance for the algorithm
     */
    double getExitTolerance();

    /**
     * Returns the state of the controller
     */
    IterativeAlgorithmResult getState();
}
