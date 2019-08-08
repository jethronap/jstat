package datastructs;

import stats.Statistics;
import utils.ArrayOperations;

import java.util.ArrayList;

/**
 * Models a discrete value sample
 */
public final class DiscreteSample extends NumericSampleBase<Integer> {


    /**
     * Construct by using an ArrayList
     * @param data
     */
    public DiscreteSample(String name, ArrayList<Integer> data, boolean is_sorted){
        super(name, data, is_sorted);
    }

    /**
     * Compute the sample statistics
     */
    protected void compute_sample_statistics(){

        Double sum = ArrayOperations.sum(data, new Integer(0)).doubleValue();
        stats.mean = sum/data.size();

        // compute variance

        // compute median

        // compute min/max
        if(is_sorted){

            stats.min = data.get(0).doubleValue();
            stats.max = data.get(data.size()-1).doubleValue();

        }
        else{

            stats.min = ArrayOperations.max( data, new Integer(0) ).doubleValue();
            stats.max = ArrayOperations.min( data, new Integer(0) ).doubleValue();
        }

        stats.is_valid = true;
    }

}
