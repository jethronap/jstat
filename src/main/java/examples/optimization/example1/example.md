# Example 1: Gradient Descent Optimization

 
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
package examples.optimization.example1;

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
import tech.tablesaw.api.Table;
import utils.TableDataSetLoader;

import java.io.File;
import java.io.IOException;

```

## <a name="m_func"></a> The main function

```
public class Example1 {

    public static void main(String[] args)throws IOException {
    
            // load the data
            Table dataSet = TableDataSetLoader.loadDataSet(new File("src/main/resources/datasets/car_plant.csv"));
    
            Vector labels = new Vector(dataSet, "Electricity Usage");
            Table reducedDataSet = dataSet.removeColumns("Electricity Usage").first(dataSet.rowCount());
    
            DenseMatrixSet<Double> denseMatrixSet = new DenseMatrixSet(RowType.Type.DOUBLE_VECTOR, new RowBuilder(), reducedDataSet.rowCount(), 2, 1.0);
            denseMatrixSet.setColumn(1, reducedDataSet.doubleColumn(0));
    
            LinearVectorPolynomial hypothesis = new LinearVectorPolynomial(1);
    
            GDInput gdInput = new GDInput();
            gdInput.showIterations = true;
            gdInput.eta=0.01;
            gdInput.errF = new MSEVectorFunction(hypothesis);
            gdInput.iterationContorller = new DefaultIterativeAlgorithmController(10000,1.0e-8);
    
            BatchGradientDescent gdSolver = new BatchGradientDescent(gdInput);
            IterativeAlgorithmResult result = gdSolver.optimize(denseMatrixSet, labels, hypothesis);
    
            System.out.println(result);
            System.out.println("Intercept: "+hypothesis.getCoeff(0)+" slope: "+hypothesis.getCoeff(1));
    
        }
}

```

## <a name="results"></a> Results

```
BatchGD: iteration: 1
	Jold: 8.224725 Jcur: 2.0346766011662285
	error |Jcur-Jold|: 6.1900483988337704
	exit tolerance: 1.0E-8
BatchGD: iteration: 2
	Jold: 2.0346766011662285 Jcur: 0.5183480014814251
	error |Jcur-Jold|: 1.5163285996848033
	exit tolerance: 1.0E-8
BatchGD: iteration: 3
	Jold: 0.5183480014814251 Jcur: 0.14690409557648973
	error |Jcur-Jold|: 0.37144390590493537
	exit tolerance: 1.0E-8
BatchGD: iteration: 4
	Jold: 0.14690409557648973 Jcur: 0.055913665703255976
	error |Jcur-Jold|: 0.09099042987323375
	exit tolerance: 1.0E-8
BatchGD: iteration: 5
	Jold: 0.055913665703255976 Jcur: 0.03362373732305984
	error |Jcur-Jold|: 0.02228992838019614
	exit tolerance: 1.0E-8
BatchGD: iteration: 6
	Jold: 0.03362373732305984 Jcur: 0.028162835522061502
	error |Jcur-Jold|: 0.005460901800998334
	exit tolerance: 1.0E-8
BatchGD: iteration: 7
	Jold: 0.028162835522061502 Jcur: 0.026824409586410466
	error |Jcur-Jold|: 0.0013384259356510365
	exit tolerance: 1.0E-8
BatchGD: iteration: 8
	Jold: 0.026824409586410466 Jcur: 0.026495834873692763
	error |Jcur-Jold|: 3.2857471271770244E-4
	exit tolerance: 1.0E-8
...

Converged: true
Tolerance: 9.995007266283551E-9
# Threads: 1
Iterations: 7076

Intercept: 0.37857734128519877 slope: 0.5049674670001678

```

## <a name="source_code"></a> Source Code

<a href="Example1.java">Example1.java</a>