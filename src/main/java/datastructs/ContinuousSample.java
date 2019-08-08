package datastructs;

import stats.Statistics;

import java.util.ArrayList;

public class ContinuousSample extends NumericSampleBase<Double> {

    /**
     * Construct by using an ArrayList
     * @param data
     */
    public ContinuousSample(String name, ArrayList<Double> data){
        super(name, data);
    }

}
