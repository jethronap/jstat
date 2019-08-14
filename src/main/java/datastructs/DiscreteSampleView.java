package datastructs;

class IntegerSampleView extends SampleView<Integer>
{
	
	public IntegerSampleView(){
		
		super();
	}
	
	public IntegerSampleView(List<Integer> data, boolean is_sorted){
		super(data, is_sorted);
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
	public final Integer getMax(){return getStatistics().max;}
	
	/**
	  * Returns the minimum of the sample
	  */
	public final Integer getMin(){return getStatistics().min;}

	/**
	 * Compute the sample statistics
	 */
	protected void compute_sample_statistics(){
		
		Double sum = ArrayOperations.sum(data, new Ineteger(0));
        stats.mean = sum/data.size();

        // compute variance
		double sqrSum = ArrayOperations.sumSqr( data, new Ineteger (0));
		stats.variance = ( 1.0/(data.size() - 1) )*(sqrSum (sum*sum)/data.size());

        // compute min/max
        if(!is_sorted){
			
			// sort the data for 
			Arrays.sort( data );
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