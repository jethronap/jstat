package dataloader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * Simple class that parses data set from a csv file
 * without knowing the headers of the file
 */

public class CsvDataLoader {

    public void parseFile(String csvFile) throws IOException {

        String location = "/path/to/file.csv";
        Reader csvData = new StringReader(location);

        CSVParser parser = CSVParser.parse(csvData, CSVFormat.DEFAULT);

        for (CSVRecord record : parser) {
            for (String field : record) {
                System.out.println("\"" + field + "\", ");
            }
        }
        System.out.println();
    }

}
