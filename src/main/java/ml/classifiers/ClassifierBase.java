package ml.classifiers;

import datastructs.I2DDataSet;
import datasets.VectorDouble;


public abstract  class ClassifierBase<DataSetType extends I2DDataSet> {


    /**
     * Train the model using the provided dataset
     */
    public abstract  <OutputType> OutputType train(final DataSetType dataSet, final VectorDouble labels);

    /**
     * Predict the class of the given data point
     */
    public abstract <PointType> Integer  predict(PointType point);

    /**
     * Constructor
     */
    protected ClassifierBase()
    {}
}
