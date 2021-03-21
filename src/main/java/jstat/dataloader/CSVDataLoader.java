package jstat.dataloader;

import jstat.base.Configuration;
import jstat.utils.Pair;
import jstat.utils.PairBuilder;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CSVDataLoader {

    public static Pair<INDArray, INDArray> loadCarPlant() throws IOException {

        File file = new File(Configuration.dataDirectory + "car_plant.csv");

        CsvReadOptions options = CsvReadOptions.builder(file).missingValueIndicator("null").build();
        Table dataSet = Table.read().usingOptions(options);

        INDArray x = Nd4j.zeros(dataSet.rowCount(), 1);
        INDArray y = Nd4j.zeros(dataSet.rowCount());

        for(int i=0; i<dataSet.rowCount(); ++i){
            x.putScalar(i, dataSet.doubleColumn(0).get(i));
            y.putScalar(i, dataSet.doubleColumn(1).get(i));
        }

        return PairBuilder.makePair(x, y);
    }

    public static Pair<INDArray, INDArray> loadCarPlantWithIntercept() throws IOException {
        return CSVDataLoader.loadCarPlantWithIntercept(0);
    }

    public static Pair<INDArray, INDArray> loadCarPlantWithIntercept(int extraCols) throws IOException {

        File file = new File(Configuration.dataDirectory + "car_plant.csv");

        CsvReadOptions options = CsvReadOptions.builder(file).missingValueIndicator("null").build();
        Table dataSet = Table.read().usingOptions(options);

        int cols = 2 + extraCols;

        INDArray x = Nd4j.zeros(dataSet.rowCount(), cols);
        INDArray y = Nd4j.zeros(dataSet.rowCount());

        for(int i=0; i<dataSet.rowCount(); ++i){
            x.putScalar(i, 0, 1.0);
            x.putScalar(i, 1, dataSet.doubleColumn(0).get(i));

            if(cols > 2){
                for(int j=2; j<x.size(1); ++j){
                    x.putScalar(i, j, dataSet.doubleColumn(0).get(i));
                }
            }

            y.putScalar(i, dataSet.doubleColumn(1).get(i));
        }

        return PairBuilder.makePair(x, y);
    }

    public static Pair<INDArray, INDArray> loadIrisData() throws IOException{

        File file = new File(Configuration.dataDirectory + "iris_data.csv");

        CsvReadOptions options = CsvReadOptions.builder(file).missingValueIndicator("null").build();
        Table dataSet = Table.read().usingOptions(options);


        INDArray x = Nd4j.zeros(dataSet.rowCount(), dataSet.columnCount()-1);
        INDArray y = Nd4j.zeros(dataSet.rowCount());

        Column species  = dataSet.column("species");

        for (int i = 0; i < species.size(); i++) {

            String label = (String) species.get(i);

            if(label.equals("Iris-setosa")){

                y.putScalar(i, 0);
            }
            else if(label.equals("Iris-versicolor")){

                y.putScalar(i, 1);
            }
            else if(label.equals("Iris-virginica")){

                y.putScalar(i, 2);
            }
            else{
                throw new IllegalArgumentException("Unknown class");
            }
        }

        Table reducedDataSet = dataSet.removeColumns("species").first(dataSet.rowCount());

        for(int i=0; i<reducedDataSet.rowCount(); ++i) {
            x.putScalar(i, 0, reducedDataSet.doubleColumn(0).get(i));
            x.putScalar(i, 1, reducedDataSet.doubleColumn(1).get(i));
            x.putScalar(i, 2, reducedDataSet.doubleColumn(2).get(i));
            x.putScalar(i, 3, reducedDataSet.doubleColumn(3).get(i));
        }

        return PairBuilder.makePair(x, y);
    }
}
