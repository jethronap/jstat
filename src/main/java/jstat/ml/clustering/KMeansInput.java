package jstat.ml.clustering;

import jstat.maths.functions.distances.IDistanceMetric;
import jstat.maths.functions.generators.IRandomGenerator;
import jstat.utils.IterativAlgorithmController;

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

    IDistanceMetric distanceCalculator;

    IRandomGenerator randomGenerator;


    /**
     * The class that controls the iterations of the algorithm
     */
    public IterativAlgorithmController iterationContorller;
}
