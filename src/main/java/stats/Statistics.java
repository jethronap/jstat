package stats;

/**
 * Simple class that holds some basic statistics usually
 * required by an application when working on a dataset
 */
public class Statistics {

    public double mean=0.0;
    public double variance=0.0;
    public double median=0.0;
    public double max=0.0;
    public double min=0.0;
	public boolean is_valid=false;

	public final void printInfo(){

	    System.out.println("Mean:     "+mean);
        System.out.println("Variance: "+variance);
        System.out.println("Median:   "+median);
        System.out.println("Max:      "+max);
        System.out.println("Min:      "+min);
        System.out.println("Valid:    "+is_valid);
    }
}
