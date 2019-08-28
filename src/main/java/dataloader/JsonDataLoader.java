package dataloader;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.json.JsonReadOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Simple Class that parses data set from a json file
 * without knowing the file's format
 * & prints out the key value pairs.
 */


public class JsonDataLoader {

    public void parseFile(String jsonFile, HashMap<String, Object> dataSet) throws IOException {

        File json = new File(jsonFile);
        try {
            JsonFactory factory = new JsonFactory();
            ObjectMapper mapper = new ObjectMapper(factory);
            JsonNode rootNode = mapper.readTree(json);

            Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
            while (fieldsIterator.hasNext()) {
                Map.Entry<String, JsonNode> field = fieldsIterator.next();
                dataSet.put(field.getKey(), field.getValue());

                System.out.println(dataSet);
                System.out.println("Key: " + field.getKey() + "\tValue: " + field.getValue());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

    }

    public void parseFile(Table jsonFile) throws IOException {
        //jsonFile.read().file("myFile.json");
        jsonFile.read().usingOptions(JsonReadOptions.builder("myFile.json"));
    }
}
