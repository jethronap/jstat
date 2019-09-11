package examples;

import base.Configuration;
import dataloader.CsvDataLoader;
import datastructs.CategoricalSample;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * Category: Statistics, Data structures, Data loaders
 * ID: LoadCsvDataSetWithTreeMapAndExtractCategoricalSample
 * Description: Load a CSV file using the CsvDataLoader.MapLoader and create
 * a CategoricalSample from the data set
 */

public class LoadCsvDataSetWithTreeMapAndExtractCategoricalSample {

    public static void main(String[] args){

        try {

            TreeMap<String, List> dataSet = CsvDataLoader.MapLoader.parseFile("data/humans_data.csv");

            if(!dataSet.containsKey("Age")){
                Configuration.Logging.printWarning("Column " + "Age" + " not in dataset");
            }

            // load it in a CategoricalSample
            CategoricalSample sample = CsvDataLoader.MapLoader.buildCategoricalSample(dataSet, "HairColor");

            if(!dataSet.keySet().contains((String)sample.name())){
                Configuration.Logging.printWarning("Column " + sample.name() + " not in dataset");
            }

            sample.printInfo();
        }
        catch(IOException e)
        {
            System.out.println("Something went wrong while running example: "+e.toString());
        }
    }
}
