package jstat.utils;
import java.util.List;


public class ArrayOperations
{

	/**
	 * Returns an array of doubles
	 */
	public static double[] toArray(final List<Double> array){

		if(array == null){
			throw new NullPointerException("Input List<T> is null");
		}

		double[] data = new double[array.size()];

		for (int i = 0; i < array.size(); i++) {
			data[i] = array.get(i);
		}

		return data;

	}

	/**
	  * Returns the sum of the array elements
	  */
	public static  Double sum(List< Double > array){

		if(array == null){
			throw new NullPointerException("Input List<T> is null");
		}

		double result = 0.0;

		for(Double element : array){
			result += element;
		}

		return result;
	}


	/**
	  * Returns the squared sum of the arraty elements
	  */
	public static  Double sumSqr(List< Double > array ){

		if(array == null){
			throw new NullPointerException("Input List<T> is null");
		}

		double result = 0.0;

		for(Double element : array){

            result += ( element*element );
		}

		return result;
	}


    /**
      * Returns the maximum element in the List
     */
	public static Double max(List< Double > array){

		if(array == null){
			throw new NullPointerException("Input List<T> is null");
		}

		Double result = array.get(0);
		
		for(Double element : array){
			
			if(element > result){
                result = element;
			}	
		}
		
		return result;
	}


    /**
     * Returns the minimum element in the array
     */
	public static Double min(List< Double > array ){

		if(array == null){
			throw new NullPointerException("Input List<T> is null");
		}

		Double result = array.get(0);
		
		for(Double element : array){
			
			if(element < result){
                result = element;
			}	
		}
		
		return result;
	}
	
}