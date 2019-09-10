package datastructs;


/**
 * Interface to model a sample from the data
 */
public interface ISample<T> {

    /**
     * The name of the sample
     */
    String name();

    /**
     * Returns the size of the sample
     */
    int size();

	/**
	 * Add the value to the sample
	 */
	void add(T value);

	/**
	 * Set the i-th entry to the given value
	 */
	void set(int i, T value);

	/**
	 * Returns the i-th entry of the sample
	 */
	T get(int i);


	/**
	  * Prints information about the sample
	  */
	void printInfo();


}
