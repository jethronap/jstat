package datastructs;

import stats.Statistics;

import java.util.ArrayList;

public class ContinuousSample extends NumericSampleBase<Double> {

    /**
     * Construct by using an ArrayList
     * @param data
     */
    public ContinuousSample(String name, ArrayList<Double> data, boolean is_sorted){
        super(name, data, is_sorted);
    }

}
