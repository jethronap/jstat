package optimization;

import datastructs.I2DDataSet;
import datastructs.IVector;
import datasets.VectorDouble;
import maths.functions.IVectorRealFunction;

public interface ISupervisedOptimizer {

    /**
     * Optimize approximate function f on the given dataset and the
     * given labels. Derived classes specify the output
     */
    <OutPutType, DataSetType extends I2DDataSet<IVector<Double>>> OutPutType
    optimize(final DataSetType data, final VectorDouble y, IVectorRealFunction f);
}
