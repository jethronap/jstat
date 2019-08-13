package datastructs;

class SampleView<T>
{

	public SampleView(){
		
		data_ = new ArrayList<T>();
	}
	
	
	public final void set(int i, T value){
		
		data_.set(i, value);
	}
	
	public final void get(int i){
		
		return data_.get(i);
	}
	
	
	private ArrayList<T> data_;


}