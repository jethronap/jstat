# Example 7: Linear regression with regularization ```LassoRegularizer``` and ```RidgeRegularizer```
 
 ## Contents
 * [Overview](#overview) 
     * [Logistic classification](#logistic_classification)
 * [Import files](#include_files)
 * [The main function](#m_func)
 * [Results](#results)
 * [Source Code](#source_code)
 
 ## <a name="overview"></a> Overview
 
 ### <a name="kmeans_algorithm"></a> Logistic classification
 
 
 
 ## <a name="include_files"></a> Import files
 
 ```
package examples.ml.example7;

import optimization.GradientDescent;
import optimization.GDInput;
import utils.DefaultIterativeAlgorithmController;
import utils.IterativeAlgorithmResult;
import datasets.DenseMatrixSet;
import datastructs.RowBuilder;
import datasets.VectorDouble;
import datastructs.RowType;
import maths.errorfunctions.MSEVectorFunction;
import maths.functions.LinearVectorPolynomial;

import maths.functions.regularizers.LassoRegularizer;
import maths.functions.regularizers.RidgeRegularizer;

import ml.regression.LinearRegressor;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

import utils.Pair;
import utils.PairBuilder;
import utils.TableDataSetLoader;

import java.io.File;
import java.io.IOException;


 
 ```
 
 ## <a name="m_func"></a> The main function
 
 ```
 public class Example7 {
 
     public static Pair<DenseMatrixSet, Vector> createDataSet() throws IOException, IllegalArgumentException {
 
         // load the data
         Table dataSetTable = TableDataSetLoader.loadDataSet(new File("src/main/resources/datasets/X_Y_Sinusoid_Data.csv"));
 
         DoubleColumn y  = dataSetTable.doubleColumn("y");
 
         Vector labels = new Vector(y);
 
         Table reducedDataSet = dataSetTable.removeColumns("y").first(dataSetTable.rowCount());
         DenseMatrixSet dataSet = new DenseMatrixSet(RowType.Type.DOUBLE_VECTOR, new RowBuilder(), reducedDataSet.rowCount(), reducedDataSet.columnCount() + 1, 1.0);
         dataSet.setColumn(1, reducedDataSet.doubleColumn(0));
         return PairBuilder.makePair(dataSet, labels);
     }
 
     public static void linearRegression(DenseMatrixSet data, Vector labels){
 
         System.out.println("Doing LinearRegression");
 
         LinearVectorPolynomial hypothesis = new LinearVectorPolynomial(1);
 
         GDInput gdInput = new GDInput();
         gdInput.showIterations = false;
         gdInput.eta = 0.01;
         gdInput.errF = new MSEVectorFunction(hypothesis);
         gdInput.iterationContorller = new DefaultIterativeAlgorithmController(100000,1.0e-8);
 
         // the optimizer
         BatchGradientDescent gdSolver = new BatchGradientDescent(gdInput);
 
         // the classifier
         LinearRegressor<DenseMatrixSet<Double>> regressor = new LinearRegressor(hypothesis);
 
         // train the model
         IterativeAlgorithmResult result = (IterativeAlgorithmResult) regressor.train(data, labels, gdSolver);
 
         System.out.println(" ");
         System.out.println(result);
         System.out.println("Intercept: "+hypothesis.getCoeff(0)+
                 " slope1: "+hypothesis.getCoeff(1));
 
     }
 
     public static void ridgeRegression(DenseMatrixSet data, Vector labels){
 
         System.out.println("Doing Ridge LinearRegression");
         LinearVectorPolynomial hypothesis = new LinearVectorPolynomial(1);
         RidgeRegularizer ridgeRegularizer = new RidgeRegularizer(0.001, 1, hypothesis);
 
         GDInput gdInput = new GDInput();
         gdInput.showIterations = false;
         gdInput.eta = 0.01;
         gdInput.errF = new MSEVectorFunction(hypothesis, ridgeRegularizer);
         gdInput.iterationContorller = new DefaultIterativeAlgorithmController(100000,1.0e-8);
 
         // the optimizer
         BatchGradientDescent gdSolver = new BatchGradientDescent(gdInput);
 
         // the classifier
         LinearRegressor<DenseMatrixSet<Double>> regressor = new LinearRegressor(hypothesis);
 
         // train the model
         IterativeAlgorithmResult result = (IterativeAlgorithmResult) regressor.train(data, labels, gdSolver);
 
         System.out.println(" ");
         System.out.println(result);
         System.out.println("Intercept: "+hypothesis.getCoeff(0)+
                 " slope1: "+hypothesis.getCoeff(1));
 
     }
 
     public static void lassoRegression(DenseMatrixSet data, Vector labels){
 
         System.out.println("Doing Lasso LinearRegression");
         LinearVectorPolynomial hypothesis = new LinearVectorPolynomial(1);
         LassoRegularizer lassoRegularizer = new LassoRegularizer(0.0001, 1, hypothesis);
 
         GDInput gdInput = new GDInput();
         gdInput.showIterations = false;
         gdInput.eta = 0.01;
         gdInput.errF = new MSEVectorFunction(hypothesis, lassoRegularizer);
         gdInput.iterationContorller = new DefaultIterativeAlgorithmController(100000,1.0e-8);
 
         // the optimizer
         BatchGradientDescent gdSolver = new BatchGradientDescent(gdInput);
 
         // the classifier
         LinearRegressor<DenseMatrixSet<Double>> regressor = new LinearRegressor(hypothesis);
 
         // train the model
         IterativeAlgorithmResult result = (IterativeAlgorithmResult) regressor.train(data, labels, gdSolver);
 
         System.out.println(" ");
         System.out.println(result);
         System.out.println("Intercept: "+hypothesis.getCoeff(0)+
                 " slope1: "+hypothesis.getCoeff(1));
 
     }
 
 
     public static void main(String[] args) throws IOException, IllegalArgumentException{
 
         Pair<DenseMatrixSet, Vector> data = Example7.createDataSet();
 
         System.out.println("Number of rows: "+data.first.m());
         System.out.println("Number of labels: "+data.second.size());
 
         Example7.linearRegression(data.first, data.second);
         Example7.ridgeRegression(data.first, data.second);
         Example7.lassoRegression(data.first, data.second);
 
 
     }
 
 }  
 ```
 
 ## <a name="results"></a> Results
 
 ```
Number of rows: 20
Number of labels: 20
Doing LinearRegression
 
Converged: true
Tolerance: 9.993163019306195E-9
# Threads: 1
Iterations: 5133

Intercept: 1.1707821773747917 slope1: -2.162486001652405
Doing Ridge LinearRegression
 
Converged: true
Tolerance: 9.873171780139245E-9
# Threads: 1
Iterations: 3850

Intercept: 1.1580261130223417 slope1: -2.1376028466785955
Doing Lasso LinearRegression
 
Converged: true
Tolerance: 9.997267735872839E-9
# Threads: 1
Iterations: 5092

Intercept: 1.170591014094624 slope1: -2.162113100936771
 
 ```
 
 ## <a name="source_code"></a> Source Code
 
 <a href="Example7.java">Example7.java</a>