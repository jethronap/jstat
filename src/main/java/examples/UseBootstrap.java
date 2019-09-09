package examples;
import datastructs.NumericSample;
import stats.utils.Resample;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 *
 * Statistics Example 3
 * Illustration of basic bootstrap method for the mean
 * see also: https://machinelearningmastery.com/a-gentle-introduction-to-the-bootstrap-method/
 */


public class UseBootstrap {


    public static void main(String[] args){


        //the size of the sample
        final int SIZE = 100;
        final int RESAMPLE_SIZE = 20;

        //how many bootstrap iterations to perform
        final int BOOST_ITRS = 100;

        final double mu = 0.8;
        final double sigma = 0.1;

        // create a sample
        NumericSample sample = new NumericSample("Sample", SIZE);

        // normal distribution
        NormalDistribution dist = new NormalDistribution(mu, sigma);

        for(int i=0; i<SIZE; ++i){

            sample.add(dist.sample());
        }

        System.out.println("Sample mean: "+sample.getMean());
        System.out.println("Sample variance: "+sample.getVar());
        System.out.println("Mean variance: "+sample.getVar()/sample.size());

        double[] means = new double[BOOST_ITRS];

        // Iterate
        for(int itr=0; itr<BOOST_ITRS; ++itr){

            NumericSample resample = Resample.resample(sample, RESAMPLE_SIZE, 3);
            means[itr] = resample.getMean();
        }

        // compute the mean of means

    }

}



