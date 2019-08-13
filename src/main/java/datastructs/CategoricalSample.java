package datastructs;


class CategoricalSample implements ISample
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
	  * Returns the unique categories
	  */
	public final Set<String> getCategories(){
		
		if(categories != null){
			
			return categories.keySet();
		}
		
		categories = new HashMap<String, Integer>();
		
		for(int i=0; i<data.size(); ++i){
			
			if(uniqueCategory.contains(data.get(i)){
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
	
	private String name = null;
	List<String> data = null;
	HashMap<String, Integer> categories = null;

}	