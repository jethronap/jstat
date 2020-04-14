package examples.stats.example7;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;

public class Example7 {

    public static void main(String[] args){
        final double ALPHA = 0.05;
        final double SIGMA = 2.0;
        int n = 25;
        final double z_ALPHA_HALF = 1.96;
        final double DELTA = 1.0;

        AbstractRealDistribution normalDistribution = new NormalDistribution();
        double beta = normalDistribution.cumulativeProbability(z_ALPHA_HALF - Math.sqrt(n)/SIGMA)-
                normalDistribution.cumulativeProbability(-z_ALPHA_HALF - Math.sqrt(n)/SIGMA);

        System.out.println("beta is "+beta);
        System.out.println("Power of test: " + (1.0- beta));
    }
}
