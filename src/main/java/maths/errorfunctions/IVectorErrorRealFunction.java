package maths.errorfunctions;

import maths.I2DDataSet;
import maths.Vector;

public interface IVectorErrorRealFunction {


    /**
     * Evaluate the error function using the given data, labels
     * @param data
     * @param labels
     * @return
     */
    <DataSetType extends I2DDataSet> double evaluate(DataSetType data, Vector labels);

    /**
     * Returns the gradients on the given data
     */
    <DataSetType extends I2DDataSet> Vector gradients(DataSetType data, Vector labels);


}
