package jstat.ml;

import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * Supervised learning models should implement
 * the general interface for having get/set model
 * parameters
 */
public interface ISupervisedModel {

    /**
     * Set the model paramters
     * @param parameters
     */
    void setParameters(INDArray parameters);

    /**
     * Returns the model parameters
     * @return INDArray
     */
    INDArray getParameters();
}
