package dataloader;

import org.junit.Test;
import tech.tablesaw.api.Table;

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

    @Test(expected = IOException.class)
    public void testCsvFileEmptyStringForTable() throws IOException {
        CsvDataLoader loader = new CsvDataLoader();
        Table dataSet = null;
        loader.parseFile("nonExistent.csv", dataSet);
    }
}
