# Example 6: Logistic Classification
 
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
package examples.ml.example6;

import optimization.GradientDescent;
import optimization.GDInput;
import utils.DefaultIterativeAlgorithmController;
import utils.IterativeAlgorithmResult;
import datasets.DenseMatrixSet;
import datastructs.RowBuilder;
import datasets.VectorDouble;
import datastructs.RowType;
import maths.errorfunctions.LogisticMSEVectorFunction;
import maths.functions.LinearVectorPolynomial;
import maths.functions.SigmoidFunction;
import ml.classifiers.LogisticRegressionClassifier;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;
import utils.Pair;
import utils.PairBuilder;
import utils.TableDataSetLoader;

import java.io.File;
import java.io.IOException;

 
 ```
 
 ## <a name="m_func"></a> The main function
 
 ```
 public class Example6 {
 
     public static Pair<DenseMatrixSet, Vector> createDataSet() throws IOException, IllegalArgumentException {
 
         // load the data
         Table dataSetTable = TableDataSetLoader.loadDataSet(new File("src/main/resources/datasets/iris_dataset_reduced.csv"));
 
         Column species  = dataSetTable.column("species");
 
         Vector labels = new Vector(species.size());
 
         for (int i = 0; i < species.size(); i++) {
 
             String label = (String) species.get(i);
 
             if(label.equals("Iris-setosa")){
 
                 labels.set(i, 0.0);
             }
             else if(label.equals("Iris-versicolor")){
 
                 labels.set(i, 1);
             }
             else{
                 throw new IllegalArgumentException("Unknown class");
             }
         }
 
         Table reducedDataSet = dataSetTable.removeColumns("species").first(dataSetTable.rowCount());
         DenseMatrixSet dataSet = new DenseMatrixSet(RowType.Type.DOUBLE_VECTOR, new RowBuilder(), reducedDataSet.rowCount(), reducedDataSet.columnCount() + 1, 1.0);
         dataSet.setColumn(1, reducedDataSet.doubleColumn(0));
         dataSet.setColumn(2, reducedDataSet.doubleColumn(1));
         dataSet.setColumn(3, reducedDataSet.doubleColumn(2));
         dataSet.setColumn(4, reducedDataSet.doubleColumn(3));
         return PairBuilder.makePair(dataSet, labels);
     }
 
     public static void main(String[] args) throws IOException, IllegalArgumentException{
 
         Pair<DenseMatrixSet, Vector> data = Example6.createDataSet();
 
         System.out.println("Number of rows: "+data.first.m());
         System.out.println("Number of labels: "+data.second.size());
 
         SigmoidFunction hypothesis = new SigmoidFunction(new LinearVectorPolynomial(4));
 
         GDInput gdInput = new GDInput();
         gdInput.showIterations = true;
         gdInput.eta = 0.01;
         gdInput.errF = new LogisticMSEVectorFunction(hypothesis);
         gdInput.iterationContorller = new DefaultIterativeAlgorithmController(100000,1.0e-8);
 
         // the optimizer
         BatchGradientDescent gdSolver = new BatchGradientDescent(gdInput);
 
         // the classifier
         LogisticRegressionClassifier<DenseMatrixSet<Double>, LinearVectorPolynomial> classifier = new LogisticRegressionClassifier(hypothesis, gdSolver );
 
         // train the model
         IterativeAlgorithmResult result = (IterativeAlgorithmResult) classifier.train(data.first, data.second);
 
         System.out.println(" ");
         System.out.println(result);
         System.out.println("Intercept: "+hypothesis.getCoeff(0)+
                 " slope1: "+hypothesis.getCoeff(1) +
                 " slope2: "+hypothesis.getCoeff(2) +
                 " slope3: "+hypothesis.getCoeff(3));
 
         // use 1.0 to account for the intercept term
         Vector point = new Vector(1.0, 5.7,2.8,4.1,1.3);
         Integer classIdx = classifier.predict(point);
 
         System.out.println("Point "+ point +" has class index "+ classIdx);
     }
 }

     
 ```
 
 ## <a name="results"></a> Results
 
 ```
...

BatchGD: iteration: 62769
	Jold: 0.0010943436826512905 Jcur: 0.0010943336813655545
	error |Jcur-Jold|: 1.0001285735998164E-8
	exit tolerance: 1.0E-8
BatchGD: iteration: 62770
	Jold: 0.0010943336813655545 Jcur: 0.0010943236803309794
	error |Jcur-Jold|: 1.0001034575143897E-8
	exit tolerance: 1.0E-8
BatchGD: iteration: 62771
	Jold: 0.0010943236803309794 Jcur: 0.0010943136795475577
	error |Jcur-Jold|: 1.0000783421662204E-8
	exit tolerance: 1.0E-8
BatchGD: iteration: 62772
	Jold: 0.0010943136795475577 Jcur: 0.0010943036790152583
	error |Jcur-Jold|: 1.0000532299405535E-8
	exit tolerance: 1.0E-8
BatchGD: iteration: 62773
	Jold: 0.0010943036790152583 Jcur: 0.0010942936787340855
	error |Jcur-Jold|: 1.0000281172812056E-8
	exit tolerance: 1.0E-8
BatchGD: iteration: 62774
	Jold: 0.0010942936787340855 Jcur: 0.0010942836787040298
	error |Jcur-Jold|: 1.0000030055759557E-8
	exit tolerance: 1.0E-8
BatchGD: iteration: 62775
	Jold: 0.0010942836787040298 Jcur: 0.0010942736789250564
	error |Jcur-Jold|: 9.999778973401527E-9
	exit tolerance: 1.0E-8
 
Converged: true
Tolerance: 9.999778973401527E-9
# Threads: 1
Iterations: 62776

Intercept: -0.5245243583997479 slope1: -0.8737637329493022 slope2: -2.8232639955834893 slope3: 4.586584930820463
Point  has class index 1
 
 ```
 
 ## <a name="source_code"></a> Source Code
 
 <a href="Example6.java">Example6.java</a>