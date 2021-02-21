package jstat.dataloader;

import jstat.base.Configuration;
import jstat.utils.Pair;
import jstat.utils.PairBuilder;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.File;
import java.io.IOException;

public class CSVDataLoader {

    public static Pair<INDArray, INDArray> loadCarPlant() throws IOException {

        File file = Configuration.dataDirectory;

        CsvReadOptions options = CsvReadOptions.builder(file).missingValueIndicator("null").build();
        Table dataSet = Table.read().usingOptions(options);

        INDArray x = Nd4j.zeros(dataSet.rowCount());
        INDArray y = Nd4j.zeros(dataSet.rowCount());

        for(int i=0; i<dataSet.rowCount(); ++i){
            x.putScalar(i, dataSet.doubleColumn(0).get(i));
            y.putScalar(i, dataSet.doubleColumn(1).get(i));
        }

        return PairBuilder.makePair(x, y);
    }
}
