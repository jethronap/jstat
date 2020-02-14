# Example 2: Non Linear Regression

## Contents
* [Overview](#overview) 
    * [Non Linear Regression](#nolinear_regression)
* [Import files](#include_files)
* [The main function](#m_func)
* [Results](#results)
* [Source Code](#source_code)

## <a name="overview"></a> Overview



### <a name="nolinear_regression"></a> Non Linear Regression



## <a name="include_files"></a> Import files

```
package examples.ml.example2;

import optimization.GradientDescent;
import optimization.GDInput;
import utils.DefaultIterativeAlgorithmController;
import utils.IterativeAlgorithmResult;
import datasets.DenseMatrixSet;
import datastructs.RowBuilder;
import datasets.VectorDouble;
import datastructs.RowType;
import maths.errorfunctions.MSEVectorFunction;
import maths.functions.NonLinearVectorPolynomial;
import maths.functions.ScalarMonomial;
import ml.regression.NonLinearRegressor;
import tech.tablesaw.api.Table;
import utils.TableDataSetLoader;

import java.io.File;
import java.io.IOException;

```

## <a name="m_func"></a> The main function

```
public class Example2 {

        public static void main(String[] args)throws IOException {

            // load the data
            Table dataSet = TableDataSetLoader.loadDataSet(new File("src/main/resources/datasets/car_plant.csv"));

            Vector labels = new Vector(dataSet, "Electricity Usage");
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

            BatchGradientDescent gdSolver = new BatchGradientDescent(gdInput);

            IterativeAlgorithmResult result = (IterativeAlgorithmResult) regressor.train(denseMatrixSet, labels, gdSolver);

            System.out.println(result);
            System.out.println("Intercept: " + hypothesis.getCoeff(0) + " slope 1: " + hypothesis.getCoeff(1) + " slope 2" + hypothesis.getCoeff(2));

        }
}
    
```

## <a name="results"></a> Results

```
BatchGD: iteration: 1
	Jold: 8.224725 Jcur: 0.8990546942654579
	error |Jcur-Jold|: 7.325670305734541
	exit tolerance: 1.0E-8
BatchGD: iteration: 2
	Jold: 0.8990546942654579 Jcur: 0.22953957530367752
	error |Jcur-Jold|: 0.6695151189617804
	exit tolerance: 1.0E-8
BatchGD: iteration: 3
	Jold: 0.22953957530367752 Jcur: 0.16817958029206523
	error |Jcur-Jold|: 0.061359995011612295
	exit tolerance: 1.0E-8
BatchGD: iteration: 4
	Jold: 0.16817958029206523 Jcur: 0.16238535312357452
	error |Jcur-Jold|: 0.0057942271684907065
	exit tolerance: 1.0E-8
BatchGD: iteration: 5
	Jold: 0.16238535312357452 Jcur: 0.1616683032442727
	error |Jcur-Jold|: 7.170498793018232E-4
	exit tolerance: 1.0E-8
BatchGD: iteration: 6
	Jold: 0.1616683032442727 Jcur: 0.16141542469742223
	error |Jcur-Jold|: 2.5287854685046574E-4
	exit tolerance: 1.0E-8

...

Converged: true
Tolerance: 9.990863976405695E-9
# Threads: 1
Iterations: 6638

Intercept: 0.24271250840749334 slope 1: 0.559359716172044 slope 2-0.005371430750912633

```

## <a name="source_code"></a> Source Code

<a href="Example2.java">Example2.java</a>