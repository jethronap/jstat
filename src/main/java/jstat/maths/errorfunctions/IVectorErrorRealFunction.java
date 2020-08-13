package jstat.maths.errorfunctions;

import jstat.datasets.VectorDouble;
import jstat.datastructs.I2DDataSet;

public interface IVectorErrorRealFunction {


    /**
     * Evaluate the error function using the given data, labels
     * @param data
     * @param labels
     * @return
     */
    <DataSetType extends I2DDataSet> double evaluate(DataSetType data, VectorDouble labels);

    /**
     * Returns the gradients on the given data
     */
    <DataSetType extends I2DDataSet> VectorDouble gradients(DataSetType data, VectorDouble labels);


}
