package jstat.maths.functions;

import jstat.datastructs.IVector;
import org.nd4j.linalg.api.ndarray.INDArray;

public interface IVectorRealFunction extends IRealFunction<INDArray> {


    /**
     * Returns the gradients with respect to the coefficients at the given data point
     */
    INDArray gradidents(INDArray data);

    /**
     * Compute the gradients with respect to the coefficients
     */
    INDArray coeffGradients(INDArray data);

    /**
     * Returns the i-th gradient
     */
    double gradient(int i, INDArray data);

    /**
     * Returns the gradient with respect to the i-th coeff
     */
    double coeffGradient(int i, INDArray data);


}
