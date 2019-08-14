package datastructs;

import stats.Statistics;
import utils.ArrayOperations;

import java.util.Collections;
import java.util.List;

public class DiscreteSampleView extends SampleView<Integer>
{
	
	public DiscreteSampleView(int capacity){
		
		super(capacity);
		stats = new Statistics();
	}
	
	public DiscreteSampleView(List<Integer> data, boolean is_sorted){
		super(data, is_sorted);
		stats = new Statistics();
	}
	
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
	 * Print information about the sample
	 */
	public final void printInfo(){

		getStatistics();
		stats.printInfo();
	}

	/**
	 * Compute the sample statistics
	 */
	protected void compute_sample_statistics(){
		
		Integer sum = ArrayOperations.sum(data, new Integer(0));
        stats.mean = sum/data.size();

        // compute variance
		double sqrSum = ArrayOperations.sumSqr( data, new Integer (0));
		stats.variance = ( 1.0/(data.size() - 1) )*(sqrSum - (sum*sum)/data.size());

        // compute min/max
        if(!is_sorted){
			
			// sort the data for 
			Collections.sort( data );
        }
        
		stats.min = data.get(0).doubleValue();
        stats.max = data.get(data.size()-1).doubleValue();
		
		// compute median
		if (data.size() % 2 == 0){
			stats.median = (double)(data.get(data.size()/2) + data.get(data.size()/2 - 1))/2;
		}
		else{
			stats.median = (double) data.get(data.size()/2);
		}

        stats.is_valid = true;
				
	}

	protected Statistics stats = null;
	
}