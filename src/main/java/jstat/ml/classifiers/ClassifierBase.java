package jstat.ml.classifiers;

import jstat.ml.ISupervisedModel;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;


public abstract  class ClassifierBase implements ISupervisedModel {


    /**
     * Predict the class of the given data point
     *
     * @param <PointType> A generic point type
     * @param point The predicted point
     * @return A point
     */
    //public abstract Integer  predict(INDArray point);


    /**
     * Constructor
     */
    protected ClassifierBase()
    {}
}
