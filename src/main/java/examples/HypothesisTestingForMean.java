package examples;

import dataloader.CsvDataLoader;
import datastructs.NumericSample;
import org.apache.commons.math3.stat.inference.TestUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * Category: Statistics, Hypothesis Testing
 * ID: HypothesisTestingForMean
 * Description: Load a CSV file using the CsvDataLoader.MapLoader and create
 * a NumericSample from the data set. Use the sample for hypothesis testing
 */

public class HypothesisTestingForMean {


    public static void main(String[] args){

        try {
            // load data set
            Map<String, List<String>> dataSet = CsvDataLoader.MapLoader.parseFile(new File("data/robot_state.csv"));
            NumericSample sample = CsvDataLoader.MapLoader.buildNumericSample(dataSet, "X");

            // the mean value we assume
            double mu = 2.85;
            double level = 0.05;
            boolean rejectH0 = TestUtils.tTest(mu, sample.asArray(), level );
            double pLevel = TestUtils.tTest(mu, sample.asArray());

            System.out.println("p-level is: "+pLevel);
            if(rejectH0){
                System.out.println("H0 hypothesis: mu="+mu+" can be rejected with confidence: "+(1.0-level));
            }
            else{
                System.out.println("H0 hypothesis: mu="+mu+" can not be rejected");
            }

            // now we should not reject
            mu = sample.getMean();

            rejectH0 = TestUtils.tTest(mu, sample.asArray(), level );
            pLevel = TestUtils.tTest(mu, sample.asArray());

            System.out.println("p-level is: "+pLevel);
            if(rejectH0){
                System.out.println("H0 hypothesis: mu="+mu+" can be rejected with confidence: "+(1.0-level));
            }
            else{
                System.out.println("H0 hypothesis: mu="+mu+" can not be rejected");
            }
        }
        catch(IOException exception)
        {
            System.out.println(exception.toString());
        }


    }
}
