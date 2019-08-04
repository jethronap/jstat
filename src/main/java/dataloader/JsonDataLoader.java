package dataloader;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Simple Class that parses data set from a json file
 * without knowing the file's format
 * & prints out the key value pairs.
 */



public class JsonDataLoader {

    private String location = "/path/to/file.json";
    private File json = new File(location);

    JsonFactory factory = new JsonFactory();
    ObjectMapper mapper = new ObjectMapper(factory);

    public void parseFile(String jsonFile) throws IOException {
        JsonNode rootNode = mapper.readTree(json);

        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            System.out.println("Key: " + field.getKey() + "\tValue: " + field.getValue());
        }
    }
}
