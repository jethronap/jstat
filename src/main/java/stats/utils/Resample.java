package stats.utils;

import datastructs.ISample;
import java.util.Random;

/**
 * Implements various resampling strategies
 */
public class Resample {

    /**
      * Populates ISample out with items from ISample in
     * by using an random number generator thats uses the given seed
     * This function uses replacement
     */
    public static <T> void resample(final ISample<T> in, ISample<T> out, int size ,int seed){

        Random random = new Random();
        random.setSeed(seed);

        for(int i=0; i<size; i++){

            //get a random index between [0, in.size())
            int idx = random.nextInt(in.getsize());

            T val = in.get(idx);
            out.add(val);
        }
    }
}
