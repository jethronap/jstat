package examples;

import datastructs.ContinuousSample;

public class UseContinuousSample
{


	public static void main(String[] args){

		ContinuousSample sample = new ContinuousSample("TestSample", 10);

		for(int i=0; i < 10; i++){
			sample.add(new Double(i));
		}

		sample.printInfo();
		
	}
	
	
}