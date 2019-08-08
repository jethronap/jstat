package utils;

class ArrayOperations
{
	
	public static < T extends Number > T sum(List<T> array){
		
		T rslt = Adder<T>.zero();
		
		for(T element : array){
			
			rslt += array.get(i);
		}
		
		return rslt;
		
	}
	
	public static < T extends Number > max(List< T > array){
		
		T rslt = array.get(0);
		
		for(T element : array){
			
			if(element > rslt){
					rslt = element;
			}	
		}
		
		return rslt;
	}
	
	public static < T extends Number > min(List< T > array){
		
		T rslt = array.get(0);
		
		for(T element : array){
			
			if(element < rslt){
					rslt = element;
			}	
		}
		
		return rslt;
	}
	
}