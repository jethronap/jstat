package jstat.ml.models;

import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.List;

/**
 *  Gaussian Mixture Model configuration
 */
public class GMMConfig {

    /**
     * Number of components
     */
    public int n_components=1;

    /**
     * Maximum number of iterations for EM algorithm
     */
    public int max_gmm_iterations=10;

    /**
     * Maximum number for KMeans initialization algorithm
     */
    public int max_kmeans_iterations=10;

    /**
     * Convergence tolerance
     */
    public double tolerance;

    /**
     * Initial weights of the mixture
     */
    public INDArray weights = null;

    /**
     * Initial mean vectors of the mixture
     */
    public List<INDArray> mixture_mus = null;

    /**
     * The covariance matrices of the mixture
     */
    public List<INDArray> mixture_sigmas = null;


}
