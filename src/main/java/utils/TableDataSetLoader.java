package utils;

import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.File;
import java.io.IOException;

public class TableDataSetLoader {

    public static Table loadDataSet(File csvFile) throws IOException {

        CsvReadOptions options = CsvReadOptions.builder(csvFile).missingValueIndicator("null").build();
        Table dataSet = Table.read().usingOptions(options);
        return dataSet;
    }
}
