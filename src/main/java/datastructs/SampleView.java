package datastructs;

class SampleView<T>
{

	public SampleView(){
		
		data = new ArrayList<T>();
	}
	
	public SampleView(List<T> data, boolean is_sorted){
		
		this.data = new ArrayList<T>();
		
		for(int i = 0; i <data.size(); ++i){
			
			this.set(i, data.get(i));
		}
		this.is_sorted = is_sorted;
	}
	
	public final void set(int i, T value){
		
		data.set(i, value);
	}
	
	public final void get(int i){
		
		return data.get(i);
	}
	
	/**
     * Returns the size of the sample
     */
    public int getsize(){ return data.size();}
	
	
	protected ArrayList<T> data;
	protected boolean is_sorted = false;

}