package dataloader;

import base.Configuration;
import dataloader.utils.ParseUtils;
import datastructs.NumericSample;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;


/**
 * Class that loads data from a
 * specific csv file
 * and creates datasets in two formats
 * TreeMap & Table from tablesaw.
 */

public class CsvDataLoader {


    public static class MapLoader {


        /**
         * Create a NumericsSample from the given column of the
         * in the TreeMap
         */
        public static NumericSample buildSample(TreeMap<String, List> dataSet, String colName){

            if(dataSet == null){

                throw new IllegalArgumentException("Null data set given");
            }

            NumericSample sample = null;

            if(!dataSet.containsKey(colName)){

                if(Configuration.ENABLE_WARNINGS) {
                    System.out.println("WARNING: Column " + colName + " not in dataset");
                }

                return new NumericSample(colName, 0);
            }

            List<Double> data = ParseUtils.parseAsDouble( dataSet.get(colName) );
            sample = new NumericSample(colName, data, false);
            return sample;
        }

        /**
         * Simple method that parses data set from a csv file
         * The CSV file should NOT have the last column ending with comma
         */
        public static TreeMap<String, List> parseFile(File csvFile) throws IOException {

            Reader csvData = new FileReader(csvFile);
            TreeMap<String, List> dataSet = new TreeMap<String, List>();

            CSVParser parser = CSVParser.parse(csvData, CSVFormat.DEFAULT);

            int lineCounter = 0;

            ArrayList<String> colNames = new ArrayList<String>();

            for (CSVRecord record : parser) {

                // the first record is the header
                if(lineCounter==0){

                    for (String field : record){

                        if(dataSet.containsKey(field)){

                            if(Configuration.ENABLE_WARNINGS){
                                Configuration.Logging.printWarning("Column: "+field+" already exists");
                            }
                        }
                        else{
                            // add a new column
                            colNames.add(field);
                            dataSet.put(field, new ArrayList());
                        }
                    }

                    lineCounter++;
                }
                else{

                    // this is not the header we walk the header and add
                    // values
                    Set<String> columns = dataSet.keySet();

                    int colCounter = 0;
                    for (String field : record){

                        String colName = colNames.get(colCounter);
                        dataSet.get(colName).add(field);
                        colCounter++;

                    }
                }

                /*for (String field : record) {
                    dataSet.keySet();
                    dataSet.get(field);

                }*/
            }
            return dataSet;
        }

        /**
         * Simple method that parses data set from a csv file
         * without knowing the headers of the file
         */
        public static TreeMap<String, List> parseFile(String csvFile) throws IOException {
            File file = new File(csvFile);
            return MapLoader.parseFile(file);
        }
    }

    /**
     * Handles the loading for Tablesaw
     */
    public static class TableLoader {


        /**
         * Reads from csv in file system with columns separated by commas
         * and the file has a header row.
         * Missing values are treated with an indicator.
         * A Table dataSet is returned
         */
        public static Table parseFile(String csvFile) throws IOException {

            File file = new File(csvFile);
            return TableLoader.parseFile(file);
        }

        /**
         * Reads from a csv file with columns separated by commas
         * and the file has a header row.
         * Missing values are treated with an indicator.
         * A Table dataSet is returned
         */
        public static Table parseFile(File csvFile) throws IOException {

            CsvReadOptions options = CsvReadOptions.builder(csvFile).missingValueIndicator("-").build();
            Table dataSet = Table.read().usingOptions(options);
            return dataSet;
        }
    }
}
