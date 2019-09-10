package examples;
import dataloader.CsvDataLoader;
import datastructs.NumericSample;

import java.io.IOException;
import java.util.TreeMap;


/**
 *
 * Category: Statistics, Data structures, Data loaders
 * ID: LoadCsvDataSetWithTreeMap
 * Description: Load a CSV file using the CsvDataLoader.MapLoader and create
 * a NumericSample from the data set
 */

public class LoadCsvDataSetWithTreeMap {


    public static void main(String[] args){

        try {

            TreeMap dataSet = CsvDataLoader.MapLoader.parseFile("data/robot_state.csv");

            // load it in a NumericsSample
            NumericSample sample = CsvDataLoader.MapLoader.buildSample(dataSet, "Y");

            System.out.println(sample.size());
            System.out.println(sample.getMean());
            System.out.println(sample.getVar());
            System.out.println(sample.getMedian());
            System.out.println(sample.getMax());
            System.out.println(sample.getMin());

        }
        catch(IOException e)
        {
            System.out.println("Something went wrong while running example: "+e.toString());
        }
    }
}
