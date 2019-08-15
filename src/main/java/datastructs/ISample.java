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
	 * Add the value to the sample
	 */
	public void add(T value);

	/**
	 * Set the i-th entry to the given value
	 */
	public  void set(int i, T value);

	/**
	 * Returns the i-th entry of the sample
	 */
	public T get(int i);


	/**
	  * Prints information about the sample
	  */
	public void printInfo();  

}
