package dataloader;

import org.junit.Test;
import tech.tablesaw.api.Table;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class JsonLoaderTest {

    /**
     * Test Scenario: The application wants to read a json dataset
     * but the String is invalid(empty String).
     * Expected Output: JsonDataLoader throws an IOException.
     */

    @Test(expected = IOException.class)
    public void testEmptyStringForHashMap() throws IOException {
        JsonDataLoader loader = new JsonDataLoader();
        loader.parseFile("nonExistent.json", new HashMap<String, Object>());
    }

    /**
     * This is the same test as above
     * but for type Table.
     */

    @Test(expected = IOException.class)
    public void testEmptyStringForTable() throws IOException {
        JsonDataLoader loader = new JsonDataLoader();
        Table dataSet = null;
        loader.parseFile("nonExistent.json", dataSet);
    }

    /**
     * Test Scenario: a json file is provided
     * Expected Output: the dataset is returned
     * along with the correct column size and row count.
     */

    @Test
    public void testJsonFileForTable() throws IOException {
        JsonDataLoader loader = new JsonDataLoader();
        Table dataSet = null;
        File file = new File("test_data/dummy.json");
        dataSet = loader.parseFile(file, dataSet);

        assertNotNull(dataSet);
        assertEquals(dataSet.columns().size(), 3);
        assertEquals(dataSet.rowCount(), 1);
    }
}
