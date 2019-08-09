package utils;

import java.util.List;


public class ArrayOperations
{
	/**
	  * Returns the sum of the array elements
	  */
	public static  Double sum(List< Double > array, Double none){

		if(array == null){
			throw new NullPointerException("Input List<T> is null");
		}

		double rslt = 0.0;

		for(Double element : array){
			rslt += element;
		}

		return rslt;
	}
	
	/**
	  * Returns the squared sum of the arraty elements
	  */
	public static  Double sumSqr(List< Double > array, Double none){

		if(array == null){
			throw new NullPointerException("Input List<T> is null");
		}

		double rslt = 0.0;

		for(Double element : array){

			rslt += ( element*element );
		}

		return rslt;
	}

	public static  Integer sum(List<Integer> array, Integer none){

		if(array == null){
			throw new NullPointerException("Input List<T> is null");
		}

		int rslt = 0;

		for(Integer element : array){

			rslt += element;
		}

		return rslt;
	}
	
	public static  Integer sumSqr(List<Integer> array, Integer none){

		if(array == null){
			throw new NullPointerException("Input List<T> is null");
		}

		int rslt = 0;

		for(Integer element : array){

			rslt += ( element*element);
		}

		return rslt;
	}
	
	public static Double max(List< Double > array, Double none){

		if(array == null){
			throw new NullPointerException("Input List<T> is null");
		}

		Double rslt = array.get(0);
		
		for(Double element : array){
			
			if(element > rslt){
					rslt = element;
			}	
		}
		
		return rslt;
	}

	public static Integer max(List< Integer > array, Integer none){

		if(array == null){
			throw new NullPointerException("Input List<T> is null");
		}

		Integer rslt = array.get(0);

		for(Integer element : array){

			if(element > rslt){
				rslt = element;
			}
		}

		return rslt;
	}


	public static Double min(List< Double > array, Double none){

		if(array == null){
			throw new NullPointerException("Input List<T> is null");
		}

		Double rslt = array.get(0);
		
		for(Double element : array){
			
			if(element < rslt){
					rslt = element;
			}	
		}
		
		return rslt;
	}

	public static Integer min(List< Integer > array, Integer none){

		if(array == null){
			throw new NullPointerException("Input List<T> is null");
		}

		Integer rslt = array.get(0);

		for(Integer element : array){

			if(element < rslt){
				rslt = element;
			}
		}

		return rslt;
	}
	
}