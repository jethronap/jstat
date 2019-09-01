package dataloader;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.json.JsonReadOptions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class JsonDataLoader {

    /**
     * Method that parses data set from a json file
     * without knowing the file's format
     * & prints out the key value pairs.
     */
    public void parseFile(String jsonFile, HashMap<String, Object> dataSet) throws IOException {

        File json = new File(jsonFile);

        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = mapper.readTree(json);

        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            dataSet.put(field.getKey(), field.getValue());

            System.out.println("Key: " + field.getKey() + "\tValue: " + field.getValue());
        }

    }

    /**
     * Method that accepts json format as arrays,
     * with or without headers and
     * array of objects.
     */
    public void parseFile(String jsonFile, Table dataSet) throws IOException {
        //jsonData.read().file("myFile.json");
        dataSet.read().usingOptions(JsonReadOptions.builder(jsonFile));
    }
}
