package dataloader;

import org.junit.Test;

import java.io.IOException;
import java.util.TreeMap;

public class CsvLoaderTest {
    /**
     * Test Scenario: The application wants to read a csv dataset
     * but the String is invalid(empty String).
     * Expected Output: csvDataLoader throws an IOException.
     */

    @Test(expected = IOException.class)
    public void testCsvFileEmptyStringForTree() throws IOException {
        CsvDataLoader loader = new CsvDataLoader();
        loader.parseFile("nonExistent.csv", new TreeMap());
    }

    /**
     * This is the same test as above
     * but for type Table.
     */

    @Test(expected = IOException.class)
    public void testCsvFileEmptyStringForTable() throws IOException {
        CsvDataLoader loader = new CsvDataLoader();
        //Table dataSet = new Table();
        loader.parseFile("nonExistent.csv");
    }
}
