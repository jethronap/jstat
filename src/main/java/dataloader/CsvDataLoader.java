package dataloader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import tech.tablesaw.api.Table;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.TreeMap;

/**
 * Simple class that parses data set from a csv file
 * without knowing the headers of the file
 */

public class CsvDataLoader {

    public void parseFile(String csvFile, TreeMap dataSet) throws IOException {

        Reader csvData = new FileReader(csvFile);

        CSVParser parser = CSVParser.parse(csvData, CSVFormat.DEFAULT);

        for (CSVRecord record : parser) {
            for (String field : record) {
                dataSet.keySet();
                dataSet.get(field);
                System.out.println(dataSet);
                System.out.println("\"" + field + "\", ");
            }
        }
    }

    public void parseFile(Table csvData) throws IOException {
        //Table csvData = Table.read().csv("csvFile.csv");
        csvData.read().file("myFile.csv");
    }

}
