package jstat.ml.models;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.List;
import java.util.Map;

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
    public final INDArray viterbi(INDArray sequence){

        return null;
    }

    /**
     * Compute the viterbi path for the given sequence
     */
    public final INDArray viterbi(List<String> sequence){

        INDArray delta = Nd4j.zeros(sequence.size(), this.config.A.shape()[0]);
        INDArray previous = Nd4j.zeros(sequence.size(), this.config.A.shape()[0]);

        Map<String, Integer> obsToIdx = this.config.obsToIdx;
        long startObsIdx = obsToIdx.get(sequence.get(0));

        INDArray pi = this.config.pi;;
        INDArray A = this.config.A;
        INDArray B = this.config.B;

        // initialize alpha
        for(int i=0; i<A.shape()[0]; ++i){
            double piVal = pi.getDouble(0,i)*B.getDouble(i, startObsIdx);
            delta.putScalar(0, i, piVal);
        }

        // run Viterbi algorithm
        for(int t=1; t<sequence.size(); ++t){

            long obsIdx = obsToIdx.get(sequence.get(t));
            for(int i=1; i<A.shape()[0]; ++i){

                INDArray probs = Nd4j.zeros(A.shape()[0]);
                for(int j=0; j<A.shape()[0]; ++j){
                    double val = delta.getDouble((long)t-1, (long)j)*A.getDouble((long)j, (long)i)*B.getDouble(i,obsIdx);
                    probs.putScalar(0, j, val);
                }

                delta.putScalar(t, i, Nd4j.max(probs).getDouble(0));
                previous.putScalar(t-1, i, Nd4j.argMax(probs).getInt(0));

            }

        }

        INDArray states = Nd4j.zeros(sequence.size());
        long last_state = Nd4j.argMax(delta.getRow(sequence.size()-1)).getInt(0);
        states.putScalar(0, last_state);
        long backtrack_idx = 1;

        for(int  i= sequence.size()-2; i >= 0; --i) {

            states.putScalar(backtrack_idx, previous.getInt(i, (int)last_state));
            last_state = previous.getInt(i, (int)last_state);
            backtrack_idx += 1;
        }


        return Nd4j.reverse(states);

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
