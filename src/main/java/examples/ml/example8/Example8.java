package examples.ml.example8;

import dataloader.CsvDataLoader;
import datasets.DenseMatrixSet;
import datasets.VectorInt;
import maths.ConfusionMatrix;
import maths.functions.distances.EuclideanVectorCalculator;
import ml.classifiers.KNNClassifier;
import ml.classifiers.utils.ClassificationVoter;
import utils.Pair;

import java.io.IOException;
import java.util.List;

public class Example8 {

    public static void main(String[] args){

        try {

            // load the dataset
            Pair<DenseMatrixSet<Double>, VectorInt> irisDataSet = CsvDataLoader.loadIrisDataSet();

            KNNClassifier<Double, DenseMatrixSet<Double>,
                    EuclideanVectorCalculator<Double>,
                    ClassificationVoter> classifier = new KNNClassifier<Double, DenseMatrixSet<Double>,
                                        EuclideanVectorCalculator<Double>, ClassificationVoter>(2, false);

            classifier.setDistanceCalculator(new EuclideanVectorCalculator<Double>());
            classifier.setMajorityVoter(new ClassificationVoter());
            classifier.train(irisDataSet.first, irisDataSet.second.getRawData());

            // we want to get a classification over all points in
            // the data set
            List<Integer> predictions = classifier.predict(irisDataSet.first);
            ConfusionMatrix confusionMatrix = new ConfusionMatrix(irisDataSet.second.getRawData(), predictions, 3);

            // let's compute some metrics

        }
        catch(IOException e){

        }
    }


}
