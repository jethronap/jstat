package datastructs;

class CategoricalSampleView extends SampleView<String>
{
	
		public CategoricalSampleView(){
		
			super();
			this.categories = null;
		}
	
	public CategoricalSampleView(String name, List<String> data){
		super(data, false);
		this.categories = null;
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
			
			if(categories.contains(data.get(i)){
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
	
	HashMap<String, Integer> categories = null;

}