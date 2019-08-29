package datastructs;

import stats.Statistics;
import utils.ArrayOperations;
import java.util.ArrayList;
import java.util.List;

public abstract class NumericSampleBase< T extends Number > implements ISample<T> {


	/**
	 * Constructor
	 */
	public NumericSampleBase(String name, int capacity){

		this.stats = new Statistics();
		this.name = name;
		this.data = new ArrayList<T>(capacity);
		this.is_sorted = false;
	}

    /**
     * Constructor
     */
    protected NumericSampleBase(String name, ArrayList<T> data, boolean is_sorted){

		this.stats = new Statistics();
        this.name = name;
		copy(data);
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
	 * Add the value to the sample
	 */
	public final void add(T value){

		data.add(value);
		stats.is_valid = false;
	}

	/**
	 * Set the i-th entry to the given value
	 */
	public final void set(int i, T value){

		data.set(i, value);
		//stats.is_valid = false;
	}

	/**
	 * Returns the i-th entry of the sample
	 */
	public final T get(int i){

		return data.get(i);
	}

	/**
	 * Prints information about the sample
	 */
	public void printInfo(){

		getStatistics();
		stats.printInfo();
	}

	/**
	 * Compute the sample statistics
	 */
	protected abstract  void compute_sample_statistics();

	/**
	 * Copy the data from the given list
	 */
	protected void copy(final List<T> data){

		if(data.size() == 0){
			throw new IllegalStateException("The input data set has zero size");
		}

		this.data = new ArrayList<T>(data.size());

		for(int i=0; i<data.size(); ++i){

			this.data.add(data.get(i));
		}
	}


	protected Statistics stats = null;
	protected String name = null;
	protected ArrayList<T> data = null;
	protected boolean is_sorted = false;

}
