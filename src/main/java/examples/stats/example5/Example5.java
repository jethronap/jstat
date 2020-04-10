package examples.stats.example5;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import utils.ListUtils;
import java.util.List;

public class Example5 {

    public static void main(String[] args){

        final int SIZE = 20;
        final double MU = 1.0;
        final double STD = 0.3;

        AbstractRealDistribution normalDistribution = new NormalDistribution(MU, STD);
        List<Double> values = ListUtils.randomSample(SIZE, normalDistribution);
        DescriptiveStatistics stats = new DescriptiveStatistics(ListUtils.toDoubleArray(values));

        System.out.println("Mean is: "+ stats.getMean());
        System.out.println("Median is: "+stats.getPercentile(50));
        System.out.println("Min is: "+stats.getMin());
        System.out.println("Max is: "+stats.getMax());
    }
}
