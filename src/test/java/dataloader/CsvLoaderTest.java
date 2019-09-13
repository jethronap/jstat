package dataloader;

import org.junit.Test;
import tech.tablesaw.api.Table;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class CsvLoaderTest {


    /**
     * Test Scenario: The application wants to read a csv dataset
     * but the String is invalid(empty String).
     * Expected Output: csvDataLoader throws an IOException.
     */

    @Test(expected = IOException.class)
    public void testCsvFileEmptyStringForTree() throws IOException {
        CsvDataLoader.MapLoader.parseFile("nonExistent.csv");
    }


    /**
     * This is the same test as above
     * but for type Table.
     */

    @Test(expected = IOException.class)
    public void testCsvFileEmptyStringForTable() throws IOException {
        CsvDataLoader.TableLoader.parseFile("nonExistent.csv");
    }


    /**
     * Test Scenario: a csv file is provided
     * Expected Output: the data set is returned
     * along with the data set size.
     */

    @Test
    public void testValidCsvFileForTree() throws IOException {

        File file = new File("test_data/dummy.csv");
        HashMap dataSet = CsvDataLoader.MapLoader.parseFile(file);
        assertNotNull(dataSet);
        assertEquals(dataSet.size(), 3);
    }


    /**
     * Test Scenario: a csv file is provided
     * Expected Output: the data set is returned
     * along with the correct column size and row count.
     */
    @Test
    public void testValidCsvFileForTable() throws IOException {
        File file = new File("test_data/dummy.csv");
        Table dataSet = CsvDataLoader.TableLoader.parseFile(file);

        assertNotNull(dataSet);
        assertEquals(dataSet.columns().size(), 3);
        assertEquals(dataSet.rowCount(), 2);
    }
}
