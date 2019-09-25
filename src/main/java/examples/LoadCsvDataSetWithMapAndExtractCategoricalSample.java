package examples;

import dataloader.CsvDataLoader;
import datastructs.CategoricalSample;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * Category: Statistics, Data structures, Data loaders
 * ID: LoadCsvDataSetWithMapAndExtractCategoricalSample
 * Description: Load a CSV file using the CsvDataLoader.MapLoader and create
 * a CategoricalSample from the data set
 */

public class LoadCsvDataSetWithMapAndExtractCategoricalSample {

    public static void main(String[] args){

        try {

            Map<String, List<String>> dataSet = CsvDataLoader.MapLoader.parseFile("data/humans_data.csv");


            // load it in a CategoricalSample
            CategoricalSample sample = CsvDataLoader.MapLoader.buildCategoricalSample(dataSet, "HairColor");

            sample.printInfo();
        }
        catch(IOException e)
        {
            System.out.println("Something went wrong while running example: "+e.toString());
        }
    }
}
