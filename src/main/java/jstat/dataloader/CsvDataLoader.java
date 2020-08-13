package jstat.dataloader;

import jstat.base.Configuration;
import jstat.dataloader.utils.ParseUtils;
import jstat.datasets.DenseMatrixSet;
import jstat.datasets.VectorDouble;
import jstat.datasets.VectorInt;
import jstat.datastructs.IVector;
import jstat.datastructs.RowBuilder;
import jstat.datastructs.RowType;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;
import tech.tablesaw.io.csv.CsvReadOptions;
import jstat.utils.Pair;
import jstat.utils.PairBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class that loads data from a
 * specific csv file
 * and creates jstat.datasets in two formats
 * HashMap and Table from tablesaw.
 */

public class CsvDataLoader {


    public static class MapLoader {


        /**
         * Create a NumericsSample from the given column in the given Map
         *
         * @param dataSet The name of the data set
         * @param colName The column name
         * @return A numeric sample
         */
        public static IVector<Double> buildNumericSample(Map<String, List<String>> dataSet, String colName) {

            if (dataSet == null) {

                throw new IllegalArgumentException("Null data set given");
            }

            VectorDouble numericSample;

            if (!dataSet.containsKey(colName)) {

                if (Configuration.ENABLE_WARNINGS) {
                    Configuration.Logging.printWarning("Column " + colName + " not in dataset");
                }

                numericSample = new VectorDouble(0);

            } else {

                List<Double> data = ParseUtils.parseAsDouble(dataSet.get(colName));
                numericSample = new VectorDouble(data);
            }
            return numericSample;
        }

        /**
         * Simple method that parses data set from a csv file
         * The CSV file should NOT have the last column ending with comma.
         * The CSV column names should NOT have white space
         *
         * @param csvFile A given csv file
         * @return a data set
         * @throws IOException When invalid file is given
         */
        public static Map<String, List<String>> parseFile(File csvFile) throws IOException {

            Reader csvData = new FileReader(csvFile);
            Map<String, List<String>> dataSet = new HashMap<String, List<String>>();


            CSVParser parser = CSVParser.parse(csvData, CSVFormat.DEFAULT);

            int lineCounter = 0;

            ArrayList<String> colNames = new ArrayList<String>();

            for (CSVRecord record : parser) {

                // the first record is the header
                if (lineCounter == 0) {

                    for (String field : record) {

                        if (dataSet.containsKey(field)) {

                            if (Configuration.ENABLE_WARNINGS) {
                                Configuration.Logging.printWarning("Column: " + field + " already exists");
                            }
                        } else {
                            // add a new column
                            colNames.add(field);
                            dataSet.put(field, new ArrayList());
                        }
                    }

                    lineCounter++;
                } else {

                    int colCounter = 0;
                    for (String field : record) {

                        String colName = colNames.get(colCounter);
                        dataSet.get(colName).add(field);
                        colCounter++;

                    }
                }

            }
            return dataSet;
        }


        /**
         * Simple method that parses a
         * data set from a csv file
         * without headers
         *
         * @param csvFile A given csv file
         * @return a data set
         * @throws IOException When invalid file is given
         */
        public static Map<String, List<String>> parseFile(String csvFile) throws IOException {
            File file = new File(csvFile);
            return MapLoader.parseFile(file);
        }
    }

    /**
     * Handles the loading for tablesaw
     */
    public static class TableLoader {


        /**
         * Reads from csv in file system with columns separated by commas
         * and the file has a header row.
         * Missing values are treated with an indicator.
         * A Table dataSet is returned
         *
         * @param csvFile A given csv file
         * @return A data set
         * @throws IOException When invalid file is given
         */
        public static Table parseFile(String csvFile) throws IOException {

            File file = new File(csvFile);
            return TableLoader.parseFile(file);
        }

        /**
         * Reads from a csv file with columns separated by commas
         * and the file has a header row.
         * Missing values are treated with an indicator.
         * A Table dataSet is returned
         *
         * @param csvFile A given csv file
         * @return A data set
         * @throws IOException When invalid file is given
         */
        public static Table parseFile(File csvFile) throws IOException {

            CsvReadOptions options = CsvReadOptions.builder(csvFile).missingValueIndicator("-").build();
            Table dataSet = Table.read().usingOptions(options);
            return dataSet;
        }


        /**
         * Create a NumericsSample from the given column of the given Map
         *
         * @param dataSet A given data set in Tablesaw format
         * @param colName The name of the column
         * @return A numeric sample
         */
        public static IVector<Double> buildNumericSample(Table dataSet, String colName) {

            if (dataSet == null) {

                throw new IllegalArgumentException("Null data set given");
            }

            VectorDouble sample;
            Column col = dataSet.column(colName);

            if (col == null) {

                if (Configuration.ENABLE_WARNINGS) {
                    Configuration.Logging.printWarning("Column " + colName + " not in dataset");
                }

                sample = new VectorDouble(0);
            }
            else {

                List<Double> data = ParseUtils.parseAsDouble(col);
                sample = new VectorDouble(data);
            }

            return sample;
        }
    }


    /**
     * Load the Iris dataset
     * @return
     */
    public static Pair<DenseMatrixSet<Double>, VectorInt> loadIrisDataSet()throws IOException{

            String path= "src/main/resources/jstat.datasets/iris_dataset.csv";

            // load the data
            Table dataSetTable = CsvDataLoader.TableLoader.parseFile(new File(path));

            Column species  = dataSetTable.column("species");

            VectorInt labels = new VectorInt(species.size());

            for (int i = 0; i < species.size(); i++) {

                String label = (String) species.get(i);

                if(label.equals("Iris-setosa")){

                    labels.set(i, 0);
                }
                else if(label.equals("Iris-versicolor")){

                    labels.set(i, 1);
                }
                else if(label.equals("Iris-virginica")){
                    labels.set(i, 2);
                }
                else{
                    throw new IllegalArgumentException("Unknown class");
                }
            }

            Table reducedDataSet = dataSetTable.removeColumns("species").first(dataSetTable.rowCount());
            DenseMatrixSet dataSet = new DenseMatrixSet(RowType.Type.DOUBLE_VECTOR, new RowBuilder(),
                                                        reducedDataSet.rowCount(), reducedDataSet.columnCount(), 0.);
            dataSet.setColumn(0, reducedDataSet.doubleColumn(0));
            dataSet.setColumn(1, reducedDataSet.doubleColumn(1));
            dataSet.setColumn(2, reducedDataSet.doubleColumn(2));
            dataSet.setColumn(3, reducedDataSet.doubleColumn(3));
            return PairBuilder.makePair(dataSet, labels);



    }

}