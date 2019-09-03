package dataloader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.TreeMap;


/**
 * Class that takes a String as path file
 * to csv files
 * and creates datasets in two formats
 * TreeMap & Table from tablesaw.
 */

public class CsvDataLoader {


    public static class MapLoader
    {

        /**
         * Simple method that parses data set from a csv file
         * without knowing the headers of the file
         */
         public static  TreeMap parseFile(File csvFile) throws IOException {

                Reader csvData = new FileReader(csvFile);
                TreeMap dataSet = new TreeMap();

                CSVParser parser = CSVParser.parse(csvData, CSVFormat.DEFAULT);

                for (CSVRecord record : parser) {

                    for (String field : record) {
                        dataSet.keySet();
                        dataSet.get(field);

                        System.out.println("\"" + field + "\", ");
                }
            }

            return dataSet;
         }


        /**
         * Simple method that parses data set from a csv file
         * without knowing the headers of the file
         */
          public static  TreeMap parseFile(String csvFile) throws IOException {

              File file = new File(csvFile);
              return MapLoader.parseFile(file);
          }
    }


    /**
     * Handles the loading for Tablesaw
     */
    public static class TableLoader
    {

        /**
         * Reads from csv in file system with columns separated by commas
         * and the file has a header row.
         * Missing values are treated with an indicator.
         */
        public static Table parseFile(String csvFile) throws IOException {

            File file = new File(csvFile);
            return TableLoader.parseFile(file);
        }


        /**
         * Reads from csv in file system with columns separated by commas
         * and the file has a header row.
         * Missing values are treated with an indicator.
         */
        public static Table parseFile(File csvFile) throws IOException {

            CsvReadOptions options = CsvReadOptions.builder(csvFile).missingValueIndicator("-").build();
            Table dataSet = Table.read().usingOptions(options);
            return dataSet;
        }

    }

}
