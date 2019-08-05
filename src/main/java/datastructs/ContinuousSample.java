package datastructs;

import stats.Statistics;

import java.util.ArrayList;

public class ContinuousSample extends NumericSampleBase<Double> {

    /**
     * Construct by using an ArrayList
     * @param data
     */
    public ContinuousSample(String name, ArrayList<Double> data){
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
