package examples.stats.example8;

import io.CSVFileWriter;
import org.apache.commons.math3.distribution.UniformIntegerDistribution;
import stats.Statistics;
import utils.ListUtils;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Example8 {

    public static void main(String[] args){

        String path = Paths.get("src/main/java/examples/stats/example8")
                .toAbsolutePath()
                .toString();

        System.out.println("Path is: "+path);

        final int N_SIMS = 1000;
        List<Double> avg = new ArrayList<>(N_SIMS);

        for(int i =0; i <N_SIMS; ++i){

            List<Integer> sample = ListUtils.randomSample(i+1,
                    new UniformIntegerDistribution(1,6));

            double mean = Statistics.calculate(ListUtils.toDoubleArray(sample),
                    Statistics.Metrics.MEAN);

            avg.add(mean);

            if(i==10 || i == 100 || i == 500 || i==999) {
                CSVFileWriter writer = new CSVFileWriter(new String(path + "/" + "averages" + i + ".csv"));
                writer.writeDoubleRow(avg);
            }
        }
    }
}
