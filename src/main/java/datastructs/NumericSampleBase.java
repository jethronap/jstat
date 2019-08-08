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
	  * Compute the sample statistics
	  */
	protected void compute_sample_statistics(){
		
		T sum = ArrayOperations.sum(data);
		stats.mean = sum/data.size();
		
		// compute variance 
		
		// compute median
		
		// compute min/max
		if(is_sorted){
			stats.min = data.get(0);
			stats.max = data.get(data.size()-1);
		}
		else{
			
			stats.min = ArrayOperations<T>.max( data );
			stats.max = ArrayOperations<T>.min( data );
		}
		
		stats.is_valid = true;
	}	

	private Statistics stats = null;
    private String name = null;
    private ArrayList<T> data = null;
	boolean is_sorted = false;

}
