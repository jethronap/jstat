# Example 2: Gradient Descent Optimization With Two Features

 
## Contents
* [Overview](#overview) 
* [Gradient Descent](#gradient_descent) 
* [Import files](#import_files)
* [The main function](#m_func)
* [Results](#results)
* [Source Code](#source_code)

## <a name="overview"></a> Overview

## <a name="gradient_descent"></a> Gradient Descent

## <a name="import_files"></a> Import files

```
package examples.optimization.example2;

import utils.DefaultIterativeAlgorithmController;
import utils.IterativeAlgorithmResult;
import optimization.GradientDescent;
import optimization.GDInput;
import datasets.DenseMatrixSet;
import datastructs.RowBuilder;
import datasets.VectorDouble;
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


```

## <a name="m_func"></a> The main function

```
public class Example2 {

        public static Pair<DenseMatrixSet, Vector> loadNormalizedDataSet(File file)throws IOException{

            // load the data
            Table dataSet = TableDataSetLoader.loadDataSet(file);

            DoubleColumn y = dataSet.doubleColumn("Electricity Usage");
            ListMaths.normalize(y);
            Vector labels = new Vector(y);

            Table reducedDataSet = dataSet.removeColumns("Electricity Usage").first(dataSet.rowCount());
            ListMaths.normalize(reducedDataSet.doubleColumn(0));
            List<Double> coolingCol = ParseUtils.parseAsDouble(reducedDataSet.column(1));
            ListMaths.normalize(coolingCol);

            DenseMatrixSet<Double> denseMatrixSet = new DenseMatrixSet(RowType.Type.DOUBLE_VECTOR, new RowBuilder(), reducedDataSet.rowCount(), 3, 1.0);
            denseMatrixSet.setColumn(1, reducedDataSet.doubleColumn(0));
            denseMatrixSet.setColumn(2, coolingCol);

            return PairBuilder.makePair(denseMatrixSet, labels);

        }

        public static void apacheOLS(DenseMatrixSet denseMatrixSet, Vector labels)throws IOException{

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

            Pair<DenseMatrixSet, Vector> dataSet = Example2.loadNormalizedDataSet(new File("src/main/resources/datasets/car_plant_multi.csv"));

            System.out.println(" ");
            // compute with Apache OLS for reference
            Example2.apacheOLS(dataSet.first, dataSet.second);

            LinearVectorPolynomial hypothesis = new LinearVectorPolynomial(2);
            GDInput gdInput = new GDInput();
            gdInput.showIterations = false;
            gdInput.eta=0.01;
            gdInput.errF = new MSEVectorFunction(hypothesis);
            gdInput.iterationContorller = new DefaultIterativeAlgorithmController(10000,1.0e-8);

            BatchGradientDescent gdSolver = new BatchGradientDescent(gdInput);
            IterativeAlgorithmResult result = (IterativeAlgorithmResult) gdSolver.optimize(dataSet.first, dataSet.second, hypothesis);

            System.out.println(" ");
            System.out.println(result);
            System.out.println("Intercept: "+hypothesis.getCoeff(0)+" slope1: "+hypothesis.getCoeff(1)+" slope2: "+hypothesis.getCoeff(2));

        }
}

```

## <a name="results"></a> Results

```
Apache OLS: 
Intercept: -0.014061513598774061 slope1: 0.6550463939406858 slope2: 0.3541277607139755
 
Converged: true
Tolerance: 9.99538353734647E-9
# Threads: 1
Iterations: 4642

Intercept: -0.009501183829303421 slope1: 0.643411046969411 slope2: 0.3609262733434958

```

## <a name="source_code"></a> Source Code

<a href="Example2.java">Example2.java</a>