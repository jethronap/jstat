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
    public void testCsvFileEmptyString() throws IOException {
        CsvDataLoader loader = new CsvDataLoader();
        loader.parseFile("nonExistent.csv", new TreeMap());
    }
}
