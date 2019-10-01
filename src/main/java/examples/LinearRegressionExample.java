package examples;

import dataloader.CsvDataLoader;
import stats.LinearRegression;
import tech.tablesaw.api.Table;
import visualizations.ScatterChart;

import java.io.File;
import java.io.IOException;

public class LinearRegressionExample {



    public static  void main(String[] args ) throws IOException {

        File file = new File("data/car_plant.csv");
        Table table = CsvDataLoader.TableLoader.parseFile(file);

        // let's plot the data
        ScatterChart plotter = new ScatterChart();
        ScatterChart.ScatterChartOptions options = plotter.new ScatterChartOptions();
        options.chartTitle = "Production vs Electricity Usage";
        options.xAxisName = "Production";
        options.yAxisName = "Electricity Usage";


        //ScatterChart.plotScatter(options, table);

        LinearRegression regression = new LinearRegression(2);
        String[] xCols = new String[1];
        xCols[0] = "Production";
        regression.fit(table, xCols, "Electricity Usage");
    }
}
