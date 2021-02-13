package jstat.maths.functions.generators;


import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.List;


/**
 * General interface to model
 */
public interface IRandomGenerator {

    List<INDArray> generate(INDArray dataSet, int n);
}
