package examples;

import datastructs.ContinuousSampleView;

public class UseSampleView
{


	public static void main(String[] args){

		ContinuousSampleView sample = new ContinuousSampleView(10);

		for(int i=0; i < 10; i++){
			sample.add(new Double(i));
		}

		sample.printInfo();
		
	}
	
	
}