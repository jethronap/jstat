package examples;

import dataloader.CsvDataLoader;
import ml.clustering.KMeans;
import ml.clustering.KMeansInput;
import stats.Simple1DLinearRegression;
import tech.tablesaw.api.Table;
import visualizations.ScatterChart;

import java.io.File;
import java.io.IOException;

/**
 *
 * Category: Machine Learning, Clustering
 * ID: Example13
 * Description: Cluster the data
 */
public class Example13 {

    public static  void main(String[] args ) throws IOException {

        File file = new File("data/car_plant.csv");
        Table table = CsvDataLoader.TableLoader.parseFile(file);

        // set up Kmeans
        KMeansInput in = new KMeansInput();
        KMeans<?> kmeans = new KMeans<>(in);
        kmeans.cluster(table, SomeSimilarityMetric, SomeCentroidInitializer);


    }
}
