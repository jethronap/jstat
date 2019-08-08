package utils;

class ArrayOperations
{
	
	
	public static Integer sum(ArrayList<Integer> array){
		
		Integer rslt = new Integer(0);
		
		for(int i=0; i<array.size(); ++i){
			
			rslt += array.get(i);
		}
		
		return rslt;
		
	}
	
	public static Double sum(ArrayList<Double> array){
		
		Double rslt = new Double(0.0);
		
		for(int i=0; i<array.size(); ++i){
			
			rslt += array.get(i);
		}
		
		return rslt;
		
	}
	
	public static Integer max(ArrayList<Integer> array){
		
		return 0;
	}
	
	public static Integer min(ArrayList<Integer> array){
		
		return 0;
	}
	
}