# Example 4: KNN Classification With Multiple Threads

## Contents
* [Overview](#overview) 
    * [KNN classification with many threads](#knn_classification)
* [Import files](#include_files)
* [The main function](#m_func)
* [Results](#results)
* [Source Code](#source_code)

## <a name="overview"></a> Overview



### <a name="knn_classification"></a> KNN classification with many threads



## <a name="include_files"></a> Import files

```
package examples.ml.example4;

import datasets.DenseMatrixSet;
import datastructs.RowBuilder;
import datasets.VectorDouble;
import datastructs.RowType;
import maths.functions.distances.EuclideanVectorCalculator;
import ml.classifiers.ThreadedKNNClassifier;
import parallel.partitioners.MatrixRowPartitionPolicy;
import parallel.partitioners.RangePartitioner;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;
import ml.classifiers.utils.ClassificationVoter;
import utils.Pair;
import utils.PairBuilder;
import utils.TableDataSetLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

```

## <a name="m_func"></a> The main function

```
public class Example4 {

    public static Pair<DenseMatrixSet<Double>, List<Integer>> createDataSet() throws IOException, IllegalArgumentException {

        // load the data
        Table dataSetTable = TableDataSetLoader.loadDataSet(new File("src/main/resources/datasets/iris_data.csv"));

        List<Integer> labels = new ArrayList<>();

        Column species  = dataSetTable.column("species");

        for (int i = 0; i < species.size(); i++) {

            String label = (String) species.get(i);

            if(label.equals("Iris-setosa")){

                labels.add(0);
            }
            else if(label.equals("Iris-versicolor")){

                labels.add(1);
            }
            else if(label.equals("Iris-virginica")){

                labels.add(2);
            }
            else{
                throw new IllegalArgumentException("Unknown class");
            }
        }

        Table reducedDataSet = dataSetTable.removeColumns("species").first(dataSetTable.rowCount());
        DenseMatrixSet<Double> dataSet = new DenseMatrixSet(RowType.Type.DOUBLE_VECTOR, new RowBuilder());
        dataSet.initializeFrom(reducedDataSet);

        // partition the data set
        List<List<Integer>> partitions = RangePartitioner.partition(0, dataSet.m(), 4);

        MatrixRowPartitionPolicy partitionPolicy = new MatrixRowPartitionPolicy(partitions);
        dataSet.setPartitionPolicy(partitionPolicy);

        return PairBuilder.makePair(dataSet, labels);
    }

    public static void main(String[] args) throws IOException, IllegalArgumentException{

        Pair<DenseMatrixSet<Double>, List<Integer>> data = Example4.createDataSet();
        ExecutorService executorService = newFixedThreadPool(4);

        System.out.println("Number of rows: "+data.first.m());
        System.out.println("Number of labels: "+data.second.size());


        ThreadedKNNClassifier<Double, DenseMatrixSet<Double>, EuclideanVectorCalculator<Double>,
                ClassificationVoter> classifier = new ThreadedKNNClassifier<>(3, false, executorService);

        classifier.setDistanceCalculator(new EuclideanVectorCalculator<Double>());
        classifier.setMajorityVoter(new ClassificationVoter());

        classifier.train(data.first, data.second);
        Vector point = new Vector(5.9,3.0,5.1,1.8);
        Integer classIdx = classifier.predict(point);

        System.out.println("Point "+ point +" has class index "+ classIdx);
        executorService.shutdown();

    }
}
    
```

## <a name="results"></a> Results

```

```

## <a name="source_code"></a> Source Code

<a href="Example4.java">Example4.java</a>