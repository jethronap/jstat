package datastructs;

import java.util.ArrayList;
import java.util.List;

public class SampleView<T>
{

	public SampleView(int capacity){
		data = new ArrayList<T>(capacity);
		this.is_sorted = false;
	}
	
	public SampleView(List<T> data, boolean is_sorted){
		
		this.data = new ArrayList<T>(data.size());
		
		for(int i = 0; i <data.size(); ++i){
			
			this.set(i, data.get(i));
		}
		this.is_sorted = is_sorted;
	}

	public final void add(T value){

		data.add(value);
	}
	
	public final void set(int i, T value){
		
		data.set(i, value);
	}
	
	public final T get(int i){
		
		return data.get(i);
	}
	
	/**
     * Returns the size of the sample
     */
    public int getsize(){ return data.size();}
	
	
	protected ArrayList<T> data;
	protected boolean is_sorted = false;

}