package jstat.ml.classifiers;

import org.nd4j.linalg.api.ndarray.INDArray;


public abstract  class ClassifierBase {


    /**
     * Train the model using the provided dataset
     *
     * @param <OutputType> A generic output type
     * @param dataSet The trained data set
     * @param labels The given labels
     * @return A trained data set
     */
    public abstract  <OutputType> OutputType train(final INDArray dataSet, final INDArray labels);

    /**
     * Predict the class of the given data point
     *
     * @param <PointType> A generic point type
     * @param point The predicted point
     * @return A point
     */
    public abstract Integer  predict(INDArray point);

    /**
     * Constructor
     */
    protected ClassifierBase()
    {}
}
