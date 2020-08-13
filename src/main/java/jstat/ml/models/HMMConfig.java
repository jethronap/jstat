package jstat.ml.models;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.List;
import java.util.Map;

public class HMMConfig {

    /**
     *  Transition probability matrix
     */
    public INDArray A;

    /**
     *  Emission probability matrix
     */
    public INDArray B;

    /**
     *  State initialization vector
     */
    public INDArray pi;

    /**
     * State names
     */
    List<String> states;

    /**
     * Map providing an association between
     * a string observation and its index
     */
    Map<String, Integer> obsToIdx;
}
