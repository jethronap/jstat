                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  package jstat.stats.utils;

import jstat.datasets.VectorDouble;
import jstat.datastructs.IVector;

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
    public static IVector<Double> resample(final VectorDouble in, int size , int seed){

        if(size==0){
            throw new IllegalArgumentException("Cannot resample on a sample of zero size...");
        }

        VectorDouble outSample;
        if(size < in.size()){

            outSample = Resample.resampleWithSmallerSize(in, size, seed);
        }
        else if(size > in.size()){
            throw new IllegalArgumentException("resample size>in.size()..  Not implemented yet");
        }
        else{

            Random random = new Random();
            random.setSeed(seed);
            outSample = new VectorDouble(size);

            for(int i=0; i<outSample.size(); i++){

                //get a random index between [0, in.size())
                int idx = random.nextInt(in.size());

                Double val = in.get(idx);
                outSample.set(idx, val);
            }
        }

        return outSample;
    }


    public static VectorDouble resampleWithSmallerSize(final VectorDouble in, int size ,int seed){

        if(size==0){
            throw new IllegalArgumentException("Cannot resample on a sample of zero size...");
        }

        if(size > in.size()){
            throw new IllegalArgumentException("Resample size should be smaller that in.size()...");
        }

        Random random = new Random();
        random.setSeed(seed);

        VectorDouble outSample = new VectorDouble(size);
        int position = 0; // the next available position in  outSample

        for(int i=0; i<outSample.size(); i++){

            //get a random index between [0, in.size())
            int idx = random.nextInt(in.size());

            Double val = in.get(idx);
            outSample.set(position, val);
            position++;
        }

        return outSample;
    }





}
