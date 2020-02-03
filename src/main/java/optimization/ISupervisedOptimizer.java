package optimization;

import maths.I2DDataSet;
import maths.IVector;
import maths.Vector;
import maths.functions.IVectorRealFunction;

public interface ISupervisedOptimizer {

    /**
     * Optimize approximate function f on the given dataset and the
     * given labels. Derived classes specify the output
     */
    <OutPutType, DataSetType extends I2DDataSet<IVector<Double>>> OutPutType
    optimize(final DataSetType data, final Vector y, IVectorRealFunction f);
}
