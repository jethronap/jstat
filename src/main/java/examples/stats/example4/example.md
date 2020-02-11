# Example 4: Simulate the standard error for the mean
 
 ## Contents
 * [Overview](#overview) 
     * [Standard error for the mean](#standard_error_for_mean)
 * [Import files](#include_files)
 * [The main function](#m_func)
 * [Results](#results)
 * [Source Code](#source_code)
 
 ## <a name="overview"></a> Overview
 
 ### <a name="standard_error_for_mean"></a> Standard error for the mean
 

 ## <a name="include_files"></a> Import files
 
 ```
package examples.stats.example4;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import utils.ListMaths;
import utils.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
 ```
 
 ## <a name="m_func"></a> The main function
 
 ```
 public class Example4 {
 
     public static List<Double> getNormalSample(double mu, double sd, int n){
 
         List<Double> sample = new ArrayList<>(n);
 
         Random rnd = new Random();
         for(int i=0; i<n; ++i){
             double value = rnd.nextGaussian()*sd + mu;
             sample.add(value);
         }
 
         return sample;
     }
 
     public static void main(String[] args){
 
 
         final double N_SIM = 1000;
         final int N = 10;
         final  double MU = 0.0;
         final double SIGMA = 1.0;
 
         List<Double> means = new ArrayList<Double>();
 
         for( int itr=0; itr < N_SIM; ++itr){
             List<Double> sample = Example4.getNormalSample(MU, SIGMA, N);
 
             double mean = ListMaths.sum(sample)/((double)sample.size());
             //System.out.println(mean);
             means.add(mean);
         }
 
         double[] vals = ListUtils.toDoubleArray(means);
         DescriptiveStatistics stats = new DescriptiveStatistics(vals );
 
         System.out.println("Standard deviation  of means is: "+stats.getStandardDeviation());
         System.out.println("sigma/sqrt(N) is: " + SIGMA/Math.sqrt(N));
     }
 }
 ```
 
 ## <a name="results"></a> Results
 
 ```
Standard deviation  of means is: 0.3081356365887988
sigma/sqrt(N) is: 0.31622776601683794
 
 ```
 
 ## <a name="source_code"></a> Source Code
 
 <a href="Example4.java">Example4.java</a>