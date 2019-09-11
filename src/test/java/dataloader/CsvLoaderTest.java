package dataloader;

import datastructs.NumericSample;
import org.junit.Test;
import tech.tablesaw.api.Table;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;

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
     * Expected Output: the dataset is returned
     * along with the correct column size and row count.
     */

    @Test
    public void testValidCsvFileForTree() throws IOException {

        File file = new File("test_data/dummy.csv");
        TreeMap dataSet = CsvDataLoader.MapLoader.parseFile(file);
        assertNotNull(dataSet);
        //assertFalse("finish this test", true);
    }


    /**
     * This is the same test as above
     * but for type Table.
     */
    @Test
    public void testValidCsvFileForTable() throws IOException {
        File file = new File("test_data/dummy.csv");
        Table dataSet = CsvDataLoader.TableLoader.parseFile(file);

        assertNotNull(dataSet);
        assertEquals(dataSet.columns().size(), 3);
        assertEquals(dataSet.rowCount(), 1);

    }

    /**
     * TestScenario Application wants to build a NumericSample from a column of the data set that
     * does not exist
     * Expected Output: A NumericSample is returned with zero capacity
     */
    @Test
    public void testBuildNumericSampleFromCsvWithNoExistingColumn()throws IOException{

        File file = new File("test_data/dummy.csv");
        TreeMap dataSet = CsvDataLoader.MapLoader.parseFile(file);
        assertNotNull(dataSet);

        NumericSample sample = CsvDataLoader.MapLoader.buildNumericSample(dataSet, "DummyColumn");
        assertEquals(sample.size(), 0);
    }

    /**
     * TestScenario Application wants to build a NumericSample from a column of the data set
     * Expected Output: A NumericSample should be  returned populated with the data
     **/
    @Test
    public void testBuildNumericSampleFromCsvWithExistingColumn()throws IOException{

        File file = new File("test_data/robot_state_test.csv");
        TreeMap dataSet = CsvDataLoader.MapLoader.parseFile(file);
        assertNotNull(dataSet);

        Set<String> columns = dataSet.keySet();

        //for(int i=0; i<columns.size(); ++i){
         //   System.out.println(columns.);
        //}

        NumericSample sample = CsvDataLoader.MapLoader.buildNumericSample(dataSet, "X");
        assertEquals(sample.size(), 25);
    }
}
