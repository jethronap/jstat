package examples;

import datastructs.NumericSample;

public class UseContinuousSample
{


	public static void main(String[] args){

		NumericSample sample = new NumericSample("TestSample", 10);

		for(int i=0; i < 10; i++){
			sample.add(new Double(i));
		}

		sample.getMean();
		sample.getVar();
		sample.printInfo();

	}
	
	
}