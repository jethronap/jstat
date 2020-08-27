package jstat.maths.errorfunctions;

import org.nd4j.linalg.api.ndarray.INDArray;

public interface IVectorErrorRealFunction {


    /**
     * Evaluate the error function using the given data, labels
     * @param data
     * @param labels
     * @return
     */
    double evaluate(INDArray data, INDArray labels);

    /**
     * Returns the gradients on the given data
     */
    INDArray gradients(INDArray data, INDArray labels);

}
