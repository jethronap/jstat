                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  package jstat.stats.utils;



import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.Random;

/**
 * Implements various resampling strategies
 */
public class Resample {

    /**
      * Populates NumericSample  with items from NumericSample in
      * by using an random number generator that uses the given seed
      * This function uses replacement
     */
    public static INDArray resample(final INDArray in, int size , int seed){

        if(size==0){
            throw new IllegalArgumentException("Cannot resample on a sample of zero size...");
        }

        INDArray outSample;
        if(size < in.size(0)){

            outSample = Resample.resampleWithSmallerSize(in, size, seed);
        }
        else if(size > in.size(0)){
            throw new IllegalArgumentException("resample size>in.size()..  Not implemented yet");
        }
        else{

            Random random = new Random();
            random.setSeed(seed);
            outSample = Nd4j.zeros(size);

            for(int i=0; i<outSample.size(0); i++){

                //get a random index between [0, in.size())
                int idx = random.nextInt((int) in.size(0));

                Double val = in.getDouble(idx);
                outSample.putScalar(idx, val);
            }
        }

        return outSample;
    }


    public static INDArray resampleWithSmallerSize(final INDArray in, int size ,int seed){

        if(size==0){
            throw new IllegalArgumentException("Cannot resample on a sample of zero size...");
        }

        if(size > in.size(0)){
            throw new IllegalArgumentException("Resample size should be smaller that in.size()...");
        }

        Random random = new Random();
        random.setSeed(seed);

        INDArray outSample = Nd4j.zeros(size);
        int position = 0; // the next available position in  outSample

        for(int i=0; i<outSample.size(0); i++){

            //get a random index between [0, in.size())
            int idx = random.nextInt((int)in.size(0));

            Double val = in.getDouble(idx);
            outSample.putScalar(position, val);
            position++;
        }

        return outSample;
    }

}
