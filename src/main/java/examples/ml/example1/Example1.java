package examples.ml.example1;

import dataloader.CsvDataLoader;


import tech.tablesaw.api.Table;
import visualizations.ScatterChart;

import java.io.File;
import java.io.IOException;

public class Example1 {


    public static  void main(String[] args ) throws IOException {

        /*File file = new File("data/car_plant.csv");
        Table table = CsvDataLoader.TableLoader.parseFile(file);

        // let's plot the data
        ScatterChart plotter = new ScatterChart();
        ScatterChart.ScatterChartOptions options = plotter.new ScatterChartOptions();
        options.chartTitle = "Production vs Electricity Usage";
        options.xAxisName = "Production";
        options.yAxisName = "Electricity Usage";

        ScatterChart.plotScatter(options, table);

        Simple1DLinearRegression regression = new Simple1DLinearRegression();
        regression.fit(table, "Production", "Electricity Usage");

        double[] coeffs = regression.getCoeffs();
        double intercept = regression.getIntercept();
        System.out.println("Regression coefficients. Intercept: "+intercept+" Slope: "+coeffs[0]);


        //TODO embed the regression line into the Scatter plot somehow*/
    }
}
