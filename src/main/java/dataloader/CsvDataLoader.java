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

    /**
     * Simple method that parses data set from a csv file
     * without knowing the headers of the file
     */
    public void parseFile(String csvFile, TreeMap dataSet) throws IOException {

        Reader csvData = new FileReader(csvFile);

        CSVParser parser = CSVParser.parse(csvData, CSVFormat.DEFAULT);

        for (CSVRecord record : parser) {

            for (String field : record) {
                dataSet.keySet();
                dataSet.get(field);

                System.out.println("\"" + field + "\", ");
            }
        }
    }

    /**
     * Reads from csv in file system with columns separated by commas
     * and the file has a header row.
     * Missing values are treated with an indicator.
     * A Table dataSet is returned
     */
    public Table parseFile(String csvFile, Table dataSet) throws IOException {

        CsvReadOptions options = CsvReadOptions.builder(csvFile).missingValueIndicator("-").build();
        dataSet = Table.read().usingOptions(options);
        return dataSet;
    }

    /**
     * Reads from a csv file with columns separated by commas
     * and the file has a header row.
     * Missing values are treated with an indicator.
     * A Table dataSet is returned
     */
    public Table parseFile(File csvFile, Table dataSet) throws IOException {

        CsvReadOptions options = CsvReadOptions.builder(csvFile).missingValueIndicator("-").build();
        dataSet = Table.read().usingOptions(options);
        return dataSet;
    }

}
