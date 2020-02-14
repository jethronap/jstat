package examples.ml.example2;

import datasets.VectorDouble;
import optimization.GradientDescent;
import optimization.GDInput;
import utils.DefaultIterativeAlgorithmController;
import utils.IterativeAlgorithmResult;
import datasets.DenseMatrixSet;
import datastructs.RowBuilder;
import datastructs.RowType;
import maths.errorfunctions.MSEVectorFunction;
import maths.functions.NonLinearVectorPolynomial;
import maths.functions.ScalarMonomial;
import ml.regression.NonLinearRegressor;
import tech.tablesaw.api.Table;
import utils.TableDataSetLoader;

import java.io.File;
import java.io.IOException;

/** Category: Machine Learning
 * ID: Example7
 * Description: Non-linear Regression
 * Taken From:
 * Details:
 * TODO
 */

public class Example2 {

        public static void main(String[] args)throws IOException {

            // load the data
            Table dataSet = TableDataSetLoader.loadDataSet(new File("src/main/resources/datasets/car_plant.csv"));

            VectorDouble labels = new VectorDouble(dataSet, "Electricity Usage");
            Table reducedDataSet = dataSet.removeColumns("Electricity Usage").first(dataSet.rowCount());

            DenseMatrixSet denseMatrixSet = new DenseMatrixSet(RowType.Type.DOUBLE_VECTOR, new RowBuilder(), reducedDataSet.rowCount(), 2, 1.0);
            denseMatrixSet.setColumn(1, reducedDataSet.doubleColumn(0));
            denseMatrixSet.duplicateColumn(1);

            // assume a hypothesis of the form w0 +w1*X + w2*X^2
            // initially all weights are set o zeor
            NonLinearVectorPolynomial hypothesis = new NonLinearVectorPolynomial(new ScalarMonomial(0, 0.0),
                    new ScalarMonomial(1, 0.0),
                    new ScalarMonomial(2, 0.0));

            // the regressor
            NonLinearRegressor regressor = new NonLinearRegressor(hypothesis);


            GDInput gdInput = new GDInput();
            gdInput.showIterations = true;
            gdInput.eta = 0.001;
            gdInput.errF = new MSEVectorFunction(hypothesis);
            gdInput.iterationContorller = new DefaultIterativeAlgorithmController(10000, 1.0e-8);

            GradientDescent gdSolver = new GradientDescent(gdInput);

            IterativeAlgorithmResult result = (IterativeAlgorithmResult) regressor.train(denseMatrixSet, labels, gdSolver);

            System.out.println(result);
            System.out.println("Intercept: " + hypothesis.getCoeff(0) + " slope 1: " + hypothesis.getCoeff(1) + " slope 2" + hypothesis.getCoeff(2));

        }
}
