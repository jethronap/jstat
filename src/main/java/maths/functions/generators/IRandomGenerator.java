package maths.functions.generators;

import datastructs.I2DDataSet;
import datastructs.IVector;

import java.util.List;

/**
 * General interface to model
 */
public interface IRandomGenerator<PointType> {

    <DataSetTp extends I2DDataSet<IVector<PointType>>>  List<IVector<PointType>> generate(DataSetTp dataSet, int n);
}
