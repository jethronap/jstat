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

/**
 * Class that loads data from a
 * specific json file
 * and creates datasets in two formats
 * HashMap & Table from tablesaw.
 */

public class JsonDataLoader {

    /**
     * Method that parses data set from a json file
     * without knowing the file's format
     * & gets the key value pairs.
     * Doesn't accept json in array format.
     */
    public HashMap parseFile(String jsonFile, HashMap<String, Object> dataSet) throws IOException {

        File json = new File(jsonFile);

        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        JsonNode rootNode = mapper.readTree(json);

        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
        while (fieldsIterator.hasNext()) {
            Map.Entry<String, JsonNode> field = fieldsIterator.next();
            dataSet.put(field.getKey(), field.getValue());
        }
        return dataSet;
    }

    /**
     * Reads from json in file system.
     * Accepts json format as arrays,
     * with or without headers, array of objects.
     * A Table dataSet is returned.
     */
    public Table parseFile(String jsonFile, Table dataSet) throws IOException {
        JsonReadOptions options = JsonReadOptions.builder(jsonFile).build();
        dataSet = Table.read().usingOptions(options);

        return dataSet;
    }

    /**
     * Reads from json a file.
     * Accepts json format as arrays,
     * with or without headers, array of objects.
     * A Table dataSet is returned.
     */
    public Table parseFile(File jsonFile, Table dataSet) throws IOException {
        JsonReadOptions options = JsonReadOptions.builder(jsonFile).build();
        dataSet = Table.read().usingOptions(options);

        return dataSet;
    }

}
