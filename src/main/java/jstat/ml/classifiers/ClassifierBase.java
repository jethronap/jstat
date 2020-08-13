package jstat.ml.classifiers;

import jstat.datastructs.I2DDataSet;
import jstat.datasets.VectorDouble;


public abstract  class ClassifierBase<DataSetType extends I2DDataSet> {


    /**
     * Train the model using the provided dataset
     *
     * @param <OutputType> A generic output type
     * @param dataSet The trained data set
     * @param labels The given labels
     * @return A trained data set
     */
    public abstract  <OutputType> OutputType train(final DataSetType dataSet, final VectorDouble labels);

    /**
     * Predict the class of the given data point
     *
     * @param <PointType> A generic point type
     * @param point The predicted point
     * @return A point
     */
    public abstract <PointType> Integer  predict(PointType point);

    /**
     * Constructor
     */
    protected ClassifierBase()
    {}
}
