package datastructs;

import stats.Statistics;

import java.util.ArrayList;

public abstract class NumericSampleBase<T> implements ISample {


    /**
     * Constructor
     */
    protected NumericSampleBase(String name, ArrayList<T> data){


        this.name = name;
        this.data = data;
    }

    /**
     * The name of the sample
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns the size of the sample
     */
    public int getsize(){

        return data.size();
    }

    /**
     * Compute the statistics of the sample
     * @return
     */
    public abstract Statistics getStatistics();

    /**
     * Compute the mean of the sample
     */
    public abstract double getMean();


    /**
     * Compute the variance of the sample
     */
    public abstract double getVar();


    private String name=null;
    private ArrayList<T> data = null;


}
