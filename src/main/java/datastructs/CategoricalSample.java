package datastructs;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CategoricalSample implements ISample<String>
{

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
	 * Returns a view of the sample
	 */
	public SampleView<String> getView(int start, int end){

		SampleView<String> view = new CategoricalSampleView(end - start);

		int counter=0;
		for(int i=start; i<end; ++i){

			view.set(counter++, data.get(i));
		}

		return view;

	}
	
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
	
	private String name = null;
	List<String> data = null;
	HashMap<String, Integer> categories = null;

}	