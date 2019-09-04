package dataloader;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.json.JsonReadOptions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
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

    public static class MapLoader {
        /**
         * Method that parses data set from a json file
         * without knowing the file's format
         * & gets the key value pairs.
         * Doesn't accept json in array format.
         */
        public static HashMap parseFile(File jsonFile) throws IOException {

            Reader json = new FileReader(jsonFile);
            HashMap<String, Object> dataSet = new HashMap<>();

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
         * Method that parses data set from a json file
         * without knowing the file's format
         * & gets the key value pairs.
         * Doesn't accept json in array format.
         */
        public static HashMap parseFile(String jsonFile) throws IOException {
            File file = new File(jsonFile);
            return MapLoader.parseFile(file);
        }
    }


    /**
     * Handles the loading for Tablesaw
     */
    public static class TableLoader {


        /**
         * Reads from a json file.
         * Accepts json format as arrays,
         * with or without headers, array of objects.
         * A dataSet in tablesaw format is returned.
         */
        public static Table parseFile(String jsonFile) throws IOException {
            File file = new File(jsonFile);
            return TableLoader.parseFile(file);
        }

        /**
         * Reads from json a file.
         * Accepts json format as arrays,
         * with or without headers, array of objects.
         * A dataSet in tablesaw format is returned.
         */
        public static Table parseFile(File jsonFile) throws IOException {
            JsonReadOptions options = JsonReadOptions.builder(jsonFile).build();
            Table dataSet = Table.read().usingOptions(options);

            return dataSet;
        }
    }
}
