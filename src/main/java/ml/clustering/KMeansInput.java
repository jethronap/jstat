package ml.clustering;

import utils.IterativAlgorithmController;

public class KMeansInput {

    /**
     * Flag indicating if information messages should be printed
     * as the algorithm executes
     */
    public boolean showIterations = true;

    /**
     * Number of clusters to consider
     */
    public int k;


    /**
     * The class that controls the iterations of the algorithm
     */
    public IterativAlgorithmController iterationContorller;
}
