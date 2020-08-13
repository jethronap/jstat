package jstat.ml.models;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.List;

/**
 * Implements Hidden Markov Model for discrete sequences
 */
public class HiddenMarkovModel {

    /**
     * Constructor
     * @param transProbMat
     * @param emissionMat
     * @param pi
     */
    public HiddenMarkovModel(HMMConfig config){

        this.config = config;
    }

    /**
     * Compute the viterbi path for the given sequence
     */
    public final void viterbi(INDArray sequence){

    }

    /**
     * Compute the viterbi path for the given sequence
     */
    public final void viterbi(List<String> sequence){

    }

    /**
     * Train the HMM on the given sequences
     */
    public final  void train(INDArray sequences, HMMTrainConfig config){

    }


    /**
     * The configuration for the HMM
     */
    HMMConfig config;
}
