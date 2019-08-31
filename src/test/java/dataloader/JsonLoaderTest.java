package dataloader;

import org.junit.Test;
import tech.tablesaw.api.Table;

import java.io.IOException;
import java.util.HashMap;

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

    @Test(expected = IOException.class)
    public void testEmptyStringForTable() throws IOException {
        JsonDataLoader loader = new JsonDataLoader();
        Table dataSet = null;
        loader.parseFile("nonExistent.json", dataSet);
    }
}
