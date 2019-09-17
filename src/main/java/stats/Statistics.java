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
    public double skewness=0.0;
    public double kurtosis=0.0;
	public boolean isValid =false;

	public final void printInfo(){

	    System.out.println("Mean:     "+mean);
        System.out.println("Variance: "+variance);
        System.out.println("Median:   "+median);
        System.out.println("Max:      "+max);
        System.out.println("Min:      "+min);
        System.out.println("skewness: "+skewness);
        System.out.println("kurtosis: "+kurtosis);
        System.out.println("Valid:    "+ isValid);
    }

    @Override
    public final String toString(){

	    String str = new String();
	    str = "Mean:  "+new Double(mean).toString()+"\n";
        str += "Variance:  "+new Double(variance).toString()+"\n";
        str += "Variance:  "+new Double(median).toString()+"\n";
        str += "Variance:  "+new Double(max).toString()+"\n";
        str += "Variance:  "+new Double(min).toString()+"\n";
        str += "Variance:  "+new Double(skewness).toString()+"\n";
        str += "Variance:  "+new Double(kurtosis).toString()+"\n";
        str += "Valid:     "+new Boolean(isValid).toString()+"\n";
	    return str;
    }
}
