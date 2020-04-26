package examples.stats.example8;

import io.CSVFileWriter;
import org.apache.commons.math3.distribution.UniformIntegerDistribution;
import stats.Statistics;
import utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class Example8 {

    public static void main(String[] args){

        final int N_SIMS = 1000;
        List<Double> avg = new ArrayList<>(N_SIMS);

        for(int i =0; i <N_SIMS; ++i){

            List<Integer> sample = ListUtils.randomSample(i+1, new UniformIntegerDistribution(1,6));
            double mean = Statistics.calculate(ListUtils.toDoubleArray(sample), Statistics.Metrics.MEAN);
            avg.add(mean);
        }


        CSVFileWriter writer = new CSVFileWriter(new String("averages.csv"));
        writer.writeColumnNames("Column1", "Column2");


    }
}
