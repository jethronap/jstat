package datastructs;

import stats.Statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.rank.Median;

public class NumericSample implements ISample<Double> {


    /**
     * Constructor
     */
    public NumericSample(String name, int capacity) {

        this.stats_ = new Statistics();
        this.name_ = name;

        if (capacity == 0) {
            this.data_ = new ArrayList<Double>();
        } else {
            this.initialize(capacity);
        }
    }

    /**
     * Constructor
     */
    public NumericSample(String name, List<Double> data) {

        this(name, data.size());
        copy(data);
    }


    /**
     * The name of the sample
     */
    public String name() {
        return this.name_;
    }


    /**
     * Returns the size of the sample
     */
    public int size() {
        return this.data_.size();
    }


    /**
     * Compute the statistics of the sample
     */
    public final Statistics getStatistics() {

        if (!this.stats_.isValid) {

            compute_sample_statistics();
        }

        return this.stats_;
    }


    /**
     * Returns true if the statistics have been calculated
     */
    public final boolean isStatisticsValid() {
        return this.stats_.isValid;
    }


    /**
     * Compute the mean of the sample
     */
    public final double getMean() {
        return getStatistics().mean;
    }


    /**
     * Compute the variance of the sample
     */
    public final double getVar() {
        return getStatistics().variance;
    }


    /**
     * Returns the median of the sample
     */
    public final double getMedian() {
        return getStatistics().median;
    }


    /**
     * Returns the maximum of the sample
     */
    public final double getMax() {
        return getStatistics().max;
    }


    /**
     * Returns the minimum of the sample
     */
    public final double getMin() {
        return getStatistics().min;
    }


    /**
     * Returns the kurtosis statistic
     */
    public final double getKurtosis() {
        return getStatistics().kurtosis;
    }


    /**
     * Returns the skewness statistic
     */
    public final double getSkewness() {
        return getStatistics().skewness;
    }


    /**
     * Returns the held data as an array
     */
    public final double[] asArray() {

        double[] data = new double[this.data_.size()];

        for (int i = 0; i < this.data_.size(); i++) {

            data[i] = this.data_.get(i);

        }

        return data;
    }


    /**
     * Add the value to the sample
     */
    public final void add(Double value) {

        this.data_.add(value);
        this.falsifyCalculations();
    }


    /**
     * Set the i-th entry to the given value
     */
    public final void set(int i, Double value) {

        this.data_.set(i, value);
        this.falsifyCalculations();
    }


    /**
     * Returns the i-th entry of the sample
     */
    public final Double get(int i) {
        return this.data_.get(i);
    }


    /**
     * Prints information about the sample
     */
    public void printInfo() {

        getStatistics();
        this.stats_.printInfo();
    }


    /**
     * Copy the data from the given list
     */
    public void copy(final List<Double> data) {


        if (data.size() == 0) {
            throw new IllegalStateException("The input data set has zero size");
        }

        if (this.data_.size() != data.size()) {

            // remove data completely
            this.initialize(data.size());
        }

        Collections.copy(this.data_, data);
        this.falsifyCalculations();
    }


    /**
     * Initialize the sample with zero entries
     */
    protected final void initialize(int size) {

        this.data_ = new ArrayList<Double>(size);

        for (int i = 0; i < size; ++i) {
            this.data_.add(0.0);
        }
    }

    /**
     * Compute the sample statistics
     */
    protected void compute_sample_statistics() {

        double[] arrayData = this.asArray();
        DescriptiveStatistics stats = new DescriptiveStatistics(arrayData);

        this.stats_.mean = stats.getMean();
        this.stats_.variance = stats.getVariance();
        this.stats_.min = stats.getMin();
        this.stats_.max = stats.getMax();
        this.stats_.kurtosis = stats.getKurtosis();
        this.stats_.skewness = stats.getSkewness();
        this.stats_.median = new Median().evaluate(arrayData);
        this.stats_.isValid = true;
    }

    protected final void falsifyCalculations() {

        this.stats_.isValid = false;
    }

    protected Statistics stats_;
    protected String name_;
    protected List<Double> data_ = null;

}
