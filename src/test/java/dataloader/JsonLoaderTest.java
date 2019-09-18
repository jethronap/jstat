package dataloader;

import org.junit.Test;
import tech.tablesaw.api.Table;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JsonLoaderTest {

    /**
     * Test Scenario: The application wants to read a json dataset
     * but the String is invalid(empty String).
     * Expected Output: JsonDataLoader throws an IOException.
     */

    @Test(expected = IOException.class)
    public void testEmptyStringForHashMap() throws IOException {
        JsonDataLoader.MapLoader.parseFile("nonExistent.csv");
    }

    /**
     * This is the same test as above
     * but for type Table.
     */

    @Test(expected = IOException.class)
    public void testEmptyStringForTable() throws IOException {
        JsonDataLoader.TableLoader.parseFile("nonExistent.csv");
    }

    /**
     * Test Scenario: a json file is provided
     * Expected Output: the dataset is returned
     * along with the correct column size and row count.
     */

    @Test
    public void testValidJsonFileForTable() throws IOException {

        File file = new File("test_data/dummy_array.json");
        Table dataSet = JsonDataLoader.TableLoader.parseFile(file);

        assertNotNull(dataSet);
        assertEquals(dataSet.columns().size(), 3);
        assertEquals(dataSet.rowCount(), 1);
    }

    /**
     * Test Scenario: The user provides a json file that is not
     * in array format.
     * Expected Output: The application read the json file and
     * return a data set with the correct key-value pairs.
     * */
    @Test
    public void testValidJsonFileForHashMap() throws IOException {

        File file = new File("test_data/dummy.json");
        HashMap dataSet = JsonDataLoader.MapLoader.parseFile(file);

        assertEquals(dataSet.get("id"), 2);
        assertNotNull(dataSet);

    }
}
