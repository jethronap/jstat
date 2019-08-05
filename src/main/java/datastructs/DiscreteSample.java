package datastructs;

import stats.Statistics;
import java.util.ArrayList;

/**
 * Models a discrete value sample
 */
public final class DiscreteSample extends NumericSampleBase<Integer> {


    /**
     * Construct by using an ArrayList
     * @param data
     */
    public DiscreteSample(String name, ArrayList<Integer> data){
        super(name, data);
    }

    /**
     * Compute the statistics of the sample
     * @return
     */
    public Statistics getStatistics(){

        Statistics stats = new Statistics();
        return stats;

    }

    /**
     * Compute the mean of the sample
     */
    public double getMean(){

        return 0.0;

    }

    /**
     * Compute the variance of the sample
     */
    public double getVar(){

        return 0.0;
    }
}
