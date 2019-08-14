package datastructs;

import stats.Statistics;
import utils.ArrayOperations;

import java.util.ArrayList;
import java.util.Collections;

public class ContinuousSample extends NumericSampleBase<Double> {

    /**
     * Construct by using an ArrayList
     * @param data
     */
    public ContinuousSample(String name, ArrayList<Double> data, boolean is_sorted){

        super(name, data, is_sorted);
    }
	
	public SampleView<Double> getView(int start, int end){
		
		 SampleView<Double> view = new ContinuousSampleView(end - start);

		int counter=0;
		for(int i=start; i<end; ++i){

			view.set(counter++, data.get(i));
		}

		 return view;
	}

    /**
     * Compute the sample statistics
     */
    protected void compute_sample_statistics(){

        Double sum = ArrayOperations.sum(data, new Double(0.));
        stats.mean = sum/data.size();

        // compute variance
		double sqrSum = ArrayOperations.sumSqr( data, new Double (0.0));
		stats.variance = ( 1.0/(data.size() - 1) )*(sqrSum - (sum*sum)/data.size());

        // compute min/max
        if(!is_sorted){
			
			// sort the data for 
			Collections.sort( data );
			is_sorted=true;
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

}
