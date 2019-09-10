package examples;

import datastructs.NumericSample;

/**
 *
 * Category: Data structures
 * ID: UseContinuousSampleExample
 * Illustration of basic of creating a NumericalSample and
 * getting basic statistics
 */

public class UseContinuousSampleExample
{

	public static void main(String[] args){

		// create the numerical sample
		NumericSample sample = new NumericSample("TestSample", 10);

		//...and populate it with data
		for(int i=0; i < 10; i++){
			sample.add(new Double(1.0));
		}

		// print the mean and variance
		System.out.println("Sample mean: "+sample.getMean());
		System.out.println("Sample variance: "+sample.getVar());

		// see also other statistics
		sample.printInfo();

		// calculation should be re-triggered
		sample.set(0, 100.0);

		// print the mean and variance
		System.out.println("Sample mean: "+sample.getMean());
		System.out.println("Sample variance: "+sample.getVar());

		// see also other statistics
		sample.printInfo();

	}
	
	
}