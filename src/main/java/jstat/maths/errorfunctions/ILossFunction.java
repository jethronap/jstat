package jstat.maths.errorfunctions;

import jstat.maths.functions.IFunction;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * General interface to model Loss functions
 */
public interface ILossFunction {

    /**
     * Given the output and the target evaluate the
     * loss function
     * @param output
     * @param target
     * @return
     */
    double evaluate(INDArray output, INDArray target);

    /**
     * Returns the gradients with respect to the parameters
     * on the given data
     */
    INDArray paramGradients(INDArray data, INDArray y);
}
