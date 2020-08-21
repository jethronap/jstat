package jstat.ml.models;

import org.apache.commons.math3.distribution.MultivariateNormalDistribution;
import org.apache.commons.math3.distribution.fitting.MultivariateNormalMixtureExpectationMaximization;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.tensorflow.framework.TensorShapeProto;

import java.util.ArrayList;
import java.util.List;

/**
 * Gaussian mixture model
 */
public class GaussianMixtureModel {

    /**
     * Constructor
     * @param components
     */
    public GaussianMixtureModel(int components){

        this.weights = Nd4j.zeros(components);
        this.mixture_mus = new ArrayList<>();
        this.mixture_sigmas = new ArrayList<>();
        this.dists = new ArrayList<>();
    }

    /**
     * Constructor
     */
    public GaussianMixtureModel(INDArray weights){
        this((int)weights.size(0));
    }

    /**
     * Returns the probability of the vector x
     * @param x
     * @return
     */
    public double prob(INDArray x){

        double value = 0.0;

        double[] vals = x.toDoubleVector();

        try{

            for(int i=0; i<dists.size(); ++i){
                value += weights.getDouble(i)*dists.get(i).density(vals);
            }

        }
        catch (DimensionMismatchException e){

        }

        return value;

    }

    /**
     * Returns the weights of the mixture
     */
    public INDArray getWeights(){return this.weights;}

    /**
     * Evaluate the component parameters of the GMM using EM
     * algorithm
     */
    public void train(INDArray x){

    }

    /**
     * The weights of the mixture
     */
    private INDArray weights;

    /**
     * The mean vectors of the mixture
     */
    private List<INDArray> mixture_mus;

    /**
     * The covariance matrices of the mixture
     */
    private List<INDArray> mixture_sigmas;

    /**
     * The list of the Gaussian distributions
     */
    private List<MultivariateNormalDistribution> dists;
}
