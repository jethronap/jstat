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
import java.util.HashMap;
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
         * Simple method that parses a
         * data set from a csv file
         * without headers
         */
        public static HashMap parseFile(File csvFile) throws IOException {

            Reader csvData = new FileReader(csvFile);
            HashMap<String, Map<String, String>> dataSet = new HashMap();

            CSVParser parser = CSVParser.parse(csvData, CSVFormat.DEFAULT);

            for (CSVRecord record : parser) {
                dataSet.put(record.toString(), record.toMap());
            }
            return dataSet;
        }

        /**
         * Simple method that parses a
         * data set from a csv file
         * without headers
         */
        public static HashMap parseFile(String csvFile) throws IOException {
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
    }
}
