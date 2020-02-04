package ml.classifiers;

import maths.I2DDataSet;
import maths.Vector;


public abstract  class ClassifierBase<DataSetType extends I2DDataSet> {


    /**
     * Train the model using the provided dataset
     */
    public abstract  <OutputType> OutputType train(final DataSetType dataSet, final Vector labels);

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
