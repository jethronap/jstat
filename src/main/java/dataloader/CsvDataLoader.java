package dataloader;

import base.Configuration;
import dataloader.utils.ParseUtils;
import datastructs.NumericSample;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Class that loads data from a
 * specific csv file
 * and creates datasets in two formats
 * HashMap & Table from tablesaw.
 */

public class CsvDataLoader {


    public static class MapLoader {


        /**
         * Create a NumericsSample from the given column of the given Map
         */
        public static NumericSample buildSample(Map<String, List<String>> dataSet, String colName){

            if(dataSet == null){

                throw new IllegalArgumentException("Null data set given");
            }

            NumericSample sample;

            if(!dataSet.containsKey(colName)){

                if(Configuration.ENABLE_WARNINGS) {
                    Configuration.Logging.printWarning("Column " + colName + " not in dataset");
                }

                sample =  new NumericSample(colName, 0);
            }
            else{

                List<Double> data = ParseUtils.parseAsDouble( dataSet.get(colName) );
                sample = new NumericSample(colName, data, false);
            }

            return sample;
        }


        /**
         * Simple method that parses data set from a csv file
         * The CSV file should NOT have the last column ending with comma
         */
        public static Map<String, List<String>> parseFile(File csvFile) throws IOException {

            Reader csvData = new FileReader(csvFile);
            Map<String, List<String>> dataSet = new HashMap<String, List<String>>();


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

                    int colCounter = 0;
                    for (String field : record){

                        String colName = colNames.get(colCounter);
                        dataSet.get(colName).add(field);
                        colCounter++;

                    }
                }

            }
            return dataSet;
        }


        /**
         * Simple method that parses a
         * data set from a csv file
         * without headers
         */
        public static Map<String, List<String>> parseFile(String csvFile) throws IOException {
            File file = new File(csvFile);
            return MapLoader.parseFile(file);
        }
    }

    /**
     * Handles the loading for tablesaw
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


        /**
         * Create a NumericsSample from the given column of the given Map
         */
        public static NumericSample buildNumericSample(Table dataSet, String colName){

            if(dataSet == null){

                throw new IllegalArgumentException("Null data set given");
            }

            NumericSample sample;
            Column col =  dataSet.column(colName);

            if(col == null){

                if(Configuration.ENABLE_WARNINGS) {
                    Configuration.Logging.printWarning("Column " + colName + " not in dataset");
                }

                sample =  new NumericSample(colName, 0);
            }
            else{

                List<Double> data = ParseUtils.parseAsDouble( col );
                sample = new NumericSample(colName, data, false);
            }

            return sample;
        }
    }
}