package ml;
import org.nd4j.linalg.api.ndarray.INDArray;

/**
 * Implements Hidden Markov Model
 */
public class HiddenMarkovModel {

    /**
     * Constructor
     * @param transProbMat
     * @param emissionMat
     * @param pi
     */
    public HiddenMarkovModel(INDArray transProbMat, INDArray emissionMat, INDArray pi){

        this.transProbMat = transProbMat;
        this.emissionMat = emissionMat;
        this.pi = pi;
    }

    /**
     * Compute the viterbi path for the given sequence
     */
    void viterbi(INDArray sequence){

    }

    /**
     * Train the HMM on the given sequences
     */
    void train(INDArray sequences, HMMTrainConfig config){

    }

    /**
     * Apply the forward algorithm for the sequence
     */
    final INDArray  forward(INDArray sequence){

        return null;
    }

    /**
     * Apply the forward algorithm for the sequence
     */
    final INDArray  backward(INDArray sequence){

        return null;
    }

    /**
     * The transition probability matrix
     */
    private INDArray transProbMat;

    /**
     * The emission probability matrix
     */
    private INDArray emissionMat;

    /**
     * The initial state probabilities
     */
    private INDArray pi;
}
