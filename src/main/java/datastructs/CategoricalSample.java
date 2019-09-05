package datastructs;

import java.util.ArrayList;
import java.util.*;

public class CategoricalSample implements ISample<String>
{

	/**
	 * Constructor
	 */
	public CategoricalSample(String name, int capacity){

		this.name_ = name;
		this.data_ = new ArrayList<String>(capacity);
		this.is_category_valid_= false;
	}


	/**
	 * Constructor
	 */
	public CategoricalSample(String name, List<String> data){
		this(name, data.size());
		copy(data);
	}


	 /**
     * The name of the sample
     */
    public final String getName(){return name_;}


    /**
     * Returns the size of the sample
     */
    public final int getsize(){return data_.size();}


	/**
	  * Returns the unique categories
	  */
	public final Set<String> getCategories(){
		
		if(categories_ != null && is_category_valid_ == true){
			
			return categories_.keySet();
		}
		
		categories_ = new HashMap<String, Integer>();
		
		for(int i=0; i<data_.size(); ++i){
			
			if(categories_.containsKey(data_.get(i)))
			{
				categories_.put(data_.get(i), categories_.get(data_.get(i)) +1 );
			}
			else{
				
				categories_.put(data_.get(i), 1);
			}
		}

		is_category_valid_ = true;
		return categories_.keySet();
	}


	/**
	  * Returns the frequency of the category
	  */
	public final int getCategoryFrequency(String category){
		
		if(categories_ == null || is_category_valid_ == false){
			getCategories();
		}

		return categories_.get(category);
	}


	/**
	 * Prints information about the sample
	 */
	public void printInfo(){

		for(Map.Entry<String, Integer> entry : categories_.entrySet()){

			System.out.println(entry.getKey() + ", " + entry.getValue());
		}

	}


	/**
	 * Copy the data from the given list
	 */
	public final void copy(List<String> data){

		if(this.data_ == null){
			this.data_ = new ArrayList<String>(data.size());
		}

		Collections.copy(this.data_, data);
	}


	/**
	 * Add the value to the sample
	 */
	public final void add(String value){

		data_.add(value);
		is_category_valid_ = false;
	}


	/**
	 * Set the i-th entry to the given value
	 */
	public final void set(int i, String value){

		data_.set(i, value);
		is_category_valid_ = false;
	}


	/**
	 * Returns the i-th entry of the sample
	 */
	public final String get(int i){

		return data_.get(i);
	}
	
	private String name_ = null;
	List<String> data_ = null;
	HashMap<String, Integer> categories_ = null;
	boolean is_category_valid_ = false;

}	