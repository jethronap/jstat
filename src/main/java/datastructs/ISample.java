package datastructs;


/**
 * Interface to model a sample from the data
 */
public interface ISample<T> {

    /**
     * The name of the sample
     */
    public String getName();

    /**
     * Returns the size of the sample
     */
    public int getsize();
	
	/**
	  * Returns a SampleView of the sample
	  */
	public SampleView<T> getView(int start, int end);
	
	/**
	  * Prints information about the sample
	  */
	public void printInfo();  

}
