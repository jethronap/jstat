package jstat.optimization;

import jstat.maths.functions.IVectorRealFunction;
import org.nd4j.linalg.api.ndarray.INDArray;

public interface IOptimizer {

    /**
     * Optimize over the input data points and labels
     */
    void step(INDArray data, INDArray y);

}
