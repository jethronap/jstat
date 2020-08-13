package jstat.ml.models;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.List;
import java.util.Map;

/**
 * Helpers for Hidden Markov model class
 */
public class HMMHelpers {

    /**
     * Apply the forward algorithm for the sequence
     */
    public static final INDArray forward(INDArray sequence, INDArray A, INDArray B, INDArray pi){

        INDArray alpha = HMMHelpers.initForward(sequence.shape()[0], A, B, pi);

        // calculate matrix
        for(int t = 1; t <sequence.shape()[0]; ++t) {
            for (int j = 0; j < A.shape()[0]; ++j) {

                alpha.putScalar(t, j, 0.0);

                // fix j = state_idx and sum over the states
                for(int i=0; i<A.shape()[0]; ++i) {
                    double alphaPrevious = alpha.getDouble(t-1, i);
                    double transProb = A.getDouble(i,j);
                    double alphaVal = alpha.getDouble(t, j) + alphaPrevious*transProb;
                    alpha.putScalar(t, j, alphaVal);
                }
                double alphaVal = alpha.getDouble(t, j)*B.getDouble(j, t);
                alpha.putScalar(t, j, alphaVal);
            }
        }

        return alpha;
    }

    /**
     * Apply the forward algorithm for the sequence
     */
    public static final INDArray  forward(List<String> sequence, INDArray A,
                                          INDArray B, INDArray pi, Map<String, Integer> obsToIdx){

        INDArray alpha = Nd4j.zeros(sequence.size(), A.shape()[0]);
        long startIdx = obsToIdx.get(sequence.get(0));

        // initialize alpha
        for(int i=0; i<A.shape()[0]; ++i){
            double piVal = pi.getDouble(i)*B.getDouble(i, startIdx);
            alpha.putScalar(0, i, piVal);
        }

        // calculate matrix
        for(int t = 1; t <sequence.size(); ++t) {
            for (int j = 0; j < A.shape()[0]; ++j) {

                alpha.putScalar(t, j, 0.0);

                // fix j = state_idx and sum over the states
                for(int i=0; i<A.shape()[0]; ++i) {
                    double alphaPrevious = alpha.getDouble(t-1, i);
                    double transProb = A.getDouble(i,j);
                    double alphaVal = alpha.getDouble(t, j) + alphaPrevious*transProb;
                    alpha.putScalar(t, j, alphaVal);
                }

                long idx = obsToIdx.get(sequence.get(t));
                double alphaVal = alpha.getDouble(t, j)*B.getDouble(j, idx);
                alpha.putScalar(t, j, alphaVal);
            }
        }

        return alpha;
    }

    /**
     * Apply the forward algorithm for the sequence
     */
    public static final INDArray  backward(INDArray sequence, INDArray A,
                                           INDArray B, INDArray pi){


        INDArray beta = HMMHelpers.initBackward(sequence.shape()[0], A);
        long lastRowIdx = sequence.shape()[0] - 1;

        // calculate matrix
        // we loop backwards in the observation array
        // start at the position one before the end
        // proceed until t is -1. Move back one step at the time
        for( long t=lastRowIdx-1;  t >= 0; --t) {
            for (long i=0; i< A.shape()[0]; ++i) {

                double betaVal = 0.0;

                for (long j=0; j< A.shape()[0]; ++j) {
                    betaVal += B.getDouble(j,t+1)*A.getDouble(i,j)*beta.getDouble(t+1,j);
                }
                beta.putScalar(t, i, betaVal);
            }
        }

        return beta;
    }

    /**
     * Apply the forward algorithm for the sequence
     */
    public static  final INDArray  backward(List<String> sequence, INDArray A,
                                            INDArray B, INDArray pi, Map<String, Integer> obsToIdx){

        INDArray beta = Nd4j.zeros(sequence.size(), A.shape()[0]);

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
            for (long i=0; i< A.shape()[0]; ++i) {

                double betaVal = 0.0;

                for (long j=0; j< A.shape()[0]; ++j) {
                    String obs = sequence.get((int)(t + 1));
                    long idx = obsToIdx.get(obs);
                    betaVal += B.getDouble(j,idx)*A.getDouble(i,j)*beta.getDouble(t+1,j);
                }
                beta.putScalar(t, i, betaVal);
            }
        }

        return beta;
    }



    private static final INDArray initForward(long size, INDArray A, INDArray B, INDArray pi){

        INDArray alpha = Nd4j.zeros(size, A.shape()[0]);

        // initialize alpha
        for(int i=0; i<A.shape()[0]; ++i){
            double piVal = pi.getDouble(i)*B.getDouble(i, 0);
            alpha.putScalar(0, i, piVal);
        }

        return alpha;
    }

    private static final INDArray initBackward(long size, INDArray A){

        INDArray beta = Nd4j.zeros(size, A.shape()[0]);

        // initialize the bottom row with 1
        long lastRowIdx = size - 1;

        for(int i=0; i<beta.shape()[1]; ++i){
            beta.putScalar(lastRowIdx, i, 1.0);
        }

        return beta;
    }
}
