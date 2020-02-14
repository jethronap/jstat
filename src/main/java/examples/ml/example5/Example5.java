package examples.ml.example5;

import utils.DefaultIterativeAlgorithmController;
import base.CommonConstants;
import datastructs.IVector;
import datasets.DenseMatrixSet;
import datastructs.RowBuilder;
import datastructs.RowType;
import maths.functions.distances.DistanceCalculator;
import maths.functions.distances.EuclideanVectorCalculator;
import maths.functions.generators.IRandomGenerator;
import maths.functions.generators.UniformRandomGenerator;
import ml.clustering.KMeans;
import ml.clustering.KMeansInput;

/** Category: Machine Learning
 * ID: Example11
 * Description: Clustering with ```KMeans```
 * Taken From:
 * Taken From:
 * Details:
 * TODO
 */

public class Example5 {

    public static void main(String[] args){

        //some synthetic data
        DenseMatrixSet<Double> matrix = new DenseMatrixSet(RowType.Type.DOUBLE_VECTOR, new RowBuilder(), 6, 2, 0.0);
        matrix.set(0, 1.0, 2.0);
        matrix.set(1, 1.0, 4.0);
        matrix.set(2, 1.0, 0.0);
        matrix.set(3, 10.0, 2.0);
        matrix.set(4, 10.0, 2.0);
        matrix.set(5, 10.0, 0.0);

        KMeansInput input = new KMeansInput();
        input.k = 2;
        input.iterationContorller = new DefaultIterativeAlgorithmController(10, CommonConstants.getTol());

        KMeans<Double, Double> kmeans = new KMeans<>(input);

        DistanceCalculator<IVector<Double>, Double> similarity = new EuclideanVectorCalculator<Double>();
        IRandomGenerator randomGenerator = new UniformRandomGenerator();

        kmeans.cluster(matrix, similarity, randomGenerator);


    }
}
