package datastructs;

import stats.Statistics;
import java.util.ArrayList;

/**
 * Models a discrete value sample
 */
public final class DiscreteSample extends NumericSampleBase<Integer> {


    /**
     * Construct by using an ArrayList
     * @param data
     */
    public DiscreteSample(String name, ArrayList<Integer> data){
        super(name, data);
    }

}
