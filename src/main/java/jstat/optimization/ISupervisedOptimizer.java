package jstat.optimization;

import jstat.maths.functions.IVectorRealFunction;
import org.nd4j.linalg.api.ndarray.INDArray;

public interface ISupervisedOptimizer {

    /**
     * Optimize approximate function f on the given dataset and the
     * given labels. Derived classes specify the output
     */
    <OutPutType> OutPutType
    optimize(final INDArray data, final INDArray y, IVectorRealFunction f);
}
