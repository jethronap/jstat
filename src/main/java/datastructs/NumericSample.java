package datastructs;

import stats.Statistics;
import utils.ArrayOperations;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumericSample implements ISample<Double> {


	/**
	 * Constructor
	 */
	public NumericSample(String name, int capacity){

		this.stats_ = new Statistics();
		this.name_ = name;
		this.data_ = new ArrayList<Double>(capacity);
		this.is_sorted_ = false;
	}

    /**
     * Constructor
     */
    public NumericSample(String name, List<Double> data, boolean is_sorted){

    	this(name, data.size());
		this.is_sorted_ = is_sorted;
		copy(data);
    }


    /**
     * The name of the sample
     */
    public String getName(){ return this.name_; }


    /**
     * Returns the size of the sample
     */
    public int getsize(){ return this.data_.size();}


    /**
     * Compute the statistics of the sample
     */
    public final Statistics getStatistics(){
		
		if(!this.stats_.is_valid){
			
			compute_sample_statistics();
		}
		
		return this.stats_;
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
	public final void add(Double value){

		this.data_.add(value);
		this.stats_.is_valid = false;
	}

	/**
	 * Set the i-th entry to the given value
	 */
	public final void set(int i, Double value){

		this.data_.set(i, value);
		this.stats_.is_valid = false;
	}


	/**
	 * Returns the i-th entry of the sample
	 */
	public final Double get(int i){

		return this.data_.get(i);
	}


	/**
	 * Prints information about the sample
	 */
	public void printInfo(){

		getStatistics();
		this.stats_.printInfo();
	}


	/**
	 * Copy the data from the given list
	 */
	protected void copy(final List<Double> data){

		if(data.size() == 0){
			throw new IllegalStateException("The input data set has zero size");
		}

		Collections.copy(this.data_, data);
		this.stats_.is_valid = false;
	}

	/**
	 * Compute the sample statistics
	 */
	protected void compute_sample_statistics(){


		Double sum = ArrayOperations.sum(this.data_ );
		this.stats_.mean = sum/this.data_.size();

		// compute variance
		double sqrSum = ArrayOperations.sumSqr( this.data_ );
		this.stats_.variance = ( 1.0/(this.data_.size() - 1) )*(sqrSum - (sum*sum)/this.data_.size());

		// compute min/max
		if(!this.is_sorted_){

			// sort the data for
			Collections.sort( this.data_ );
			this.is_sorted_ = true;
		}

		this.stats_.min = this.data_.get(0).doubleValue();
		this.stats_.max = this.data_.get(this.data_.size()-1).doubleValue();

		// compute median
		if (this.data_.size() % 2 == 0){
			this.stats_.median = (double)(this.data_.get(this.data_.size()/2) + this.data_.get(this.data_.size()/2 - 1))/2;
		}
		else{
			this.stats_.median = (double) this.data_.get(this.data_.size()/2);
		}

		this.stats_.is_valid = true;
	}

	
	protected Statistics stats_ = null;
	protected String name_ = null;
	protected List<Double> data_ = null;
	protected boolean is_sorted_ = false;

}
