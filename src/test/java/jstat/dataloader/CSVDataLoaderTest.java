package jstat.dataloader;


import jstat.dataloader.CSVDataLoader;
import jstat.utils.Pair;
import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;


import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class CSVDataLoaderTest {

    /**
     * Test Scenario: Application predicts the same classes as the actual classes
     * Expected Output: True positives then should be the same the actual classes
     */
    @Test
    public void testloadCarPlant() throws IOException {

        Pair<INDArray, INDArray> dataSet = CSVDataLoader.loadCarPlant();
        int actualNRows = 12;
        assertEquals(dataSet.first.columns(), actualNRows);

    }
}
