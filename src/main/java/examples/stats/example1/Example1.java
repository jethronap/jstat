package examples.stats.example1;
import datasets.VectorDouble;
import datastructs.IVector;
import org.apache.commons.math3.stat.StatUtils;
import stats.utils.Resample;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 *
 * Category: Statistics
 * ID: MeanBootstrap
 * Illustration of basic bootstrap method for the mean
 * see also: https://machinelearningmastery.com/a-gentle-introduction-to-the-bootstrap-method/
 */


public class Example1 {


    public static void main(String[] args){


        // the size of the sample
        final int SIZE = 100;
        final int RESAMPLE_SIZE = 20;

        // how many bootstrap iterations to perform
        final int BOOST_ITRS = 100;

        // parameters for normal distribution
        final double MU = 0.8;
        final double SD = 0.1;

        // create a sample
        VectorDouble sample = new VectorDouble(SIZE);

        // normal distribution:
        // see https://commons.apache.org/proper/commons-math/javadocs/api-3.5/org/apache/commons/math3/distribution/NormalDistribution.html
        NormalDistribution dist = new NormalDistribution(MU, SD);

        for(int i=0; i<SIZE; ++i){
            sample.add(i, dist.sample());
        }

        System.out.println("Sample mean: "+sample.getMean());
        System.out.println("Sample variance: "+sample.getVar());
        System.out.println("Mean variance: "+sample.getVar()/sample.size());

        double[] means = new double[BOOST_ITRS];

        // Iterate
        for(int itr=0; itr<BOOST_ITRS; ++itr){

            IVector<Double> resample = Resample.resample(sample, RESAMPLE_SIZE, 3);
            means[itr] = ((VectorDouble)resample).getMean();
        }

        // compute the mean of means
        double mean = StatUtils.mean(means);
        System.out.println("Mean of means: "+mean);

    }

}



