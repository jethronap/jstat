package datastructs;

import stats.Statistics;


/**
 * Interface to model a sample from the data
 */
public interface ISample {


    /**
     * Compute the statistics of the sample
     * @return
     */
    public Statistics getStatistics();

    /**
     * Compute the mean of the sample
     */
    public double getMean();

}
