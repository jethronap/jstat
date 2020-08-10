package ml;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

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
     * Apply the forward algorithm for the sequence
     */
    public final INDArray  forward(INDArray sequence){

        INDArray alpha = Nd4j.zeros(sequence.shape()[0], this.config.A.shape()[0]);

        // initialize alpha
        for(int i=0; i<this.config.A.shape()[0]; ++i){
            double pi = this.config.pi.getDouble(i)*this.config.B.getDouble(i, 0);
            alpha.putScalar(0, i, pi);
        }

        // calculate matrix
        for(int t = 1; t <sequence.shape()[0]; ++t) {
            for (int j = 0; j < this.config.A.shape()[0]; ++j) {

                alpha.putScalar(t, j, 0.0);

                // fix j = state_idx and sum over the states
                for(int i=0; i<this.config.A.shape()[0]; ++i) {
                    double alphaPrevious = alpha.getDouble(t-1, i);
                    double transProb = this.config.A.getDouble(i,j);
                    double alphaVal = alpha.getDouble(t, j) + alphaPrevious*transProb;
                    alpha.putScalar(t, j, alphaVal);
                }
                double alphaVal = alpha.getDouble(t, j)*this.config.B.getDouble(j, t+1);
                alpha.putScalar(t, j, alphaVal);
            }
        }

        return alpha;
    }


    /**
     * Apply the forward algorithm for the sequence
     */
    public final INDArray  forward(List<String> sequence){

        INDArray alpha = Nd4j.zeros(sequence.size(), this.config.A.shape()[0]);

        long startIdx = this.config.obsToIdx.get(sequence.get(0));
        // initialize alpha
        for(int i=0; i<this.config.A.shape()[0]; ++i){
            double pi = this.config.pi.getDouble(i)*this.config.B.getDouble(i, startIdx);
            alpha.putScalar(0, i, pi);
        }

        // calculate matrix
        for(int t = 1; t <sequence.size(); ++t) {
            for (int j = 0; j < this.config.A.shape()[0]; ++j) {

                alpha.putScalar(t, j, 0.0);

                // fix j = state_idx and sum over the states
                for(int i=0; i<this.config.A.shape()[0]; ++i) {
                    double alphaPrevious = alpha.getDouble(t-1, i);
                    double transProb = this.config.A.getDouble(i,j);
                    double alphaVal = alpha.getDouble(t, j) + alphaPrevious*transProb;
                    alpha.putScalar(t, j, alphaVal);
                }

                long idx = this.config.obsToIdx.get(sequence.get(t+1));
                double alphaVal = alpha.getDouble(t, j)*this.config.B.getDouble(j, idx);
                alpha.putScalar(t, j, alphaVal);
            }
        }

        return alpha;
    }

    /**
     * Apply the forward algorithm for the sequence
     */
    final INDArray  backward(INDArray sequence){

        INDArray beta = Nd4j.zeros(sequence.shape()[0], this.config.A.shape()[0]);

        // initialize the bottom row with 1
        long lastRowIdx = sequence.shape()[0] - 1;

        for(int i=0; i<beta.shape()[1]; ++i){
            beta.putScalar(lastRowIdx, i, 1.0);
        }

        // calculate matrix
        // we loop backwards in the observation array
        // start at the position one before the end
        // proceed until t is -1. Move back one step at the time
        for( long t=lastRowIdx-1;  t >= 0; --t) {
            for (long i=0; i< this.config.A.shape()[0]; ++i) {

                double betaVal = 0.0;

                for (long j=0; j< this.config.A.shape()[0]; ++j) {
                    betaVal += this.config.B.getDouble(j,t+1)*this.config.A.getDouble(i,j)*beta.getDouble(t+1,j);
                }
                beta.putScalar(t, i, betaVal);
            }
        }

        return beta;
    }

    /**
     * Apply the forward algorithm for the sequence
     */
    final INDArray  backward(List<String> sequence){

        INDArray beta = Nd4j.zeros(sequence.size(), this.config.A.shape()[0]);

        // initialize the bottom row with 1
        long lastRowIdx = sequence.size() - 1;

        for(int i=0; i<beta.shape()[1]; ++i){
            beta.putScalar(lastRowIdx, i, 1.0);
        }

        // calculate matrix
        // we loop backwards in the observation array
        // start at the position one before the end
        // proceed until t is -1. Move back one step at the time
        for( long t=lastRowIdx-1;  t >= 0; --t) {
            for (long i=0; i< this.config.A.shape()[0]; ++i) {

                double betaVal = 0.0;

                for (long j=0; j< this.config.A.shape()[0]; ++j) {
                    String obs = sequence.get((int)(t + 1));
                    long idx = this.config.obsToIdx.get(obs);
                    betaVal += this.config.B.getDouble(j,idx)*this.config.A.getDouble(i,j)*beta.getDouble(t+1,j);
                }
                beta.putScalar(t, i, betaVal);
            }
        }

        return beta;
    }

    /**
     * The configuration for the HMM
     */
    HMMConfig config;
}
