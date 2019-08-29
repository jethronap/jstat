package datastructs;

import java.util.ArrayList;
import java.util.*;

public class CategoricalSample implements ISample<String>
{

	/**
	 * Constructor
	 */
	public CategoricalSample(String name, int capacity){

		this.name = name;
		this.data = new ArrayList<String>(capacity);
	}

	/**
	 * Constructor
	 */
	public CategoricalSample(String name, List<String> data){
		this.name = name;
		this.data = data;
		this.categories = null;
	}
	
	 /**
     * The name of the sample
     */
    public final String getName(){return name;}

    /**
     * Returns the size of the sample
     */
    public final int getsize(){return data.size();}


	/**
	  * Returns the unique categories
	  */
	public final Set<String> getCategories(){
		
		if(categories != null){
			
			return categories.keySet();
		}
		
		categories = new HashMap<String, Integer>();
		
		for(int i=0; i<data.size(); ++i){
			
			if(categories.containsKey(data.get(i)))
			{
				categories.put(data.get(i), categories.get(data.get(i)) +1 ); 
			}
			else{
				
				categories.put(data.get(i), 1);
			}
				
		}

		return categories.keySet();
	}
	
	/**
	  * Returns the frequency of the category
	  */
	public final int getCategoryFrequency(String category){
		
		if(categories == null){
			getCategories();
		}

		return categories.get(category);
	}

	/**
	 * Prints information about the sample
	 */
	public void printInfo(){

		for(Map.Entry<String, Integer> entry : categories.entrySet()){

			System.out.println(entry.getKey() + ", " + entry.getValue());
		}

	}

	/*
	 * Add the value to the sample
	 */
	public final void add(String value){

		data.add(value);
	}

	/**
	 * Set the i-th entry to the given value
	 */
	public final void set(int i, String value){

		data.set(i, value);
	}

	/**
	 * Returns the i-th entry of the sample
	 */
	public final String get(int i){

		return data.get(i);
	}
	
	private String name = null;
	List<String> data = null;
	HashMap<String, Integer> categories = null;

}	