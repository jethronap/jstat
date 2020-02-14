package examples.optimization.example2;

import datasets.VectorDouble;
import optimization.GradientDescent;
import utils.DefaultIterativeAlgorithmController;
import utils.IterativeAlgorithmResult;
import optimization.GDInput;
import datasets.DenseMatrixSet;
import datastructs.RowBuilder;
import datastructs.RowType;
import maths.functions.LinearVectorPolynomial;
import maths.errorfunctions.MSEVectorFunction;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;
import utils.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

/** Category: Machine Learning
 * ID: Example6
 * Description: Batch Gradient Descent with two features
 * Taken From:
 * Details:
 * TODO
 */

public class Example2 {

        public static Pair<DenseMatrixSet, VectorDouble> loadNormalizedDataSet(File file)throws IOException{

            // load the data
            Table dataSet = TableDataSetLoader.loadDataSet(file);

            DoubleColumn y = dataSet.doubleColumn("Electricity Usage");
            ListMaths.normalize(y);
            VectorDouble labels = new VectorDouble(y);

            Table reducedDataSet = dataSet.removeColumns("Electricity Usage").first(dataSet.rowCount());
            ListMaths.normalize(reducedDataSet.doubleColumn(0));
            List<Double> coolingCol = ParseUtils.parseAsDouble(reducedDataSet.column(1));
            ListMaths.normalize(coolingCol);

            DenseMatrixSet<Double> denseMatrixSet = new DenseMatrixSet(RowType.Type.DOUBLE_VECTOR, new RowBuilder(), reducedDataSet.rowCount(), 3, 1.0);
            denseMatrixSet.setColumn(1, reducedDataSet.doubleColumn(0));
            denseMatrixSet.setColumn(2, coolingCol);

            return PairBuilder.makePair(denseMatrixSet, labels);

        }

        public static void apacheOLS(DenseMatrixSet denseMatrixSet, VectorDouble labels)throws IOException{

            // the object that will do the fitting for us
            OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();

            Double[][] x = new Double[denseMatrixSet.m()][2];
            denseMatrixSet.getSubMatrix(x, 2, 1, 2);
            regression.newSampleData(ListUtils.toDoubleArray(labels.getRawData()), ArrayUtils.toArray(x));
            double[] coeffs = regression.estimateRegressionParameters();
            System.out.println("Apache OLS: ");
            System.out.println("Intercept: "+coeffs[0]+" slope1: "+coeffs[1]+" slope2: "+coeffs[2]);

        }

        public static void main(String[] args)throws IOException {

            Pair<DenseMatrixSet, VectorDouble> dataSet = Example2.loadNormalizedDataSet(new File("src/main/resources/datasets/car_plant_multi.csv"));

            System.out.println(" ");
            // compute with Apache OLS for reference
            Example2.apacheOLS(dataSet.first, dataSet.second);

            LinearVectorPolynomial hypothesis = new LinearVectorPolynomial(2);
            GDInput gdInput = new GDInput();
            gdInput.showIterations = false;
            gdInput.eta=0.01;
            gdInput.errF = new MSEVectorFunction(hypothesis);
            gdInput.iterationContorller = new DefaultIterativeAlgorithmController(10000,1.0e-8);

            GradientDescent gdSolver = new GradientDescent(gdInput);
            IterativeAlgorithmResult result = (IterativeAlgorithmResult) gdSolver.optimize(dataSet.first, dataSet.second, hypothesis);

            System.out.println(" ");
            System.out.println(result);
            System.out.println("Intercept: "+hypothesis.getCoeff(0)+" slope1: "+hypothesis.getCoeff(1)+" slope2: "+hypothesis.getCoeff(2));

        }
}

