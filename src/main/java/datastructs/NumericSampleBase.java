package datastructs;

import stats.Statistics;
import utils.ArrayOperations;
import java.util.ArrayList;

public abstract class NumericSampleBase< T extends Number > implements ISample {


    /**
     * Constructor
     */
    protected NumericSampleBase(String name, ArrayList<T> data, boolean is_sorted){

		this.stats = new Statistics();
        this.name = name;
        this.data = data;
		this.is_sorted = is_sorted;
    }
	
	

    /**
     * The name of the sample
     */
    public String getName(){ return this.name; }

    /**
     * Returns the size of the sample
     */
    public int getsize(){ return data.size();}

    /**
     * Compute the statistics of the sample
     * @return
     */
    public final Statistics getStatistics(){
		
		if(!stats.is_valid){
			
			compute_sample_statistics();
		}
		
		return stats;
	}

    /**
     * Compute the mean of the sample
     */
    public final double getMean(){return getStatistics().mean;}


    /**
     * Compute the variance of the sample
     */
    public final double getVar(){return getStatistics().variance;}
	
	/**
	  * Returns the median of the sample
	  */
	public final double getMedian(){return getStatistics().median;}
	
	/**
	  * Returns the maximum of the sample
	  */
	public final double getMax(){return getStatistics().max;}
	
	/**
	  * Returns the minimum of the sample
	  */
	public final double getMin(){return getStatistics().min;}

	/**
	 * Compute the sample statistics
	 */
	protected abstract  void compute_sample_statistics();


	protected Statistics stats = null;
	protected String name = null;
	protected ArrayList<T> data = null;
	protected boolean is_sorted = false;

}
