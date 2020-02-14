package examples.plotting.example3;

import dataloader.CsvDataLoader;
import datastructs.IVector;
import tech.tablesaw.api.Table;
import visualizations.LineChart;

import java.io.File;
import java.io.IOException;


/**
 * Category: Plotting
 * ID: PlotALine
 * Description: Load a CSV file and plot two columns against each other
 */

public class Example3 {

    public static void main(String[] args) throws IOException {


        File file = new File("data/annual.csv");
        Table table = CsvDataLoader.TableLoader.parseFile(file);

        // extract numeric samples from the data
        IVector<Double> X = CsvDataLoader.TableLoader.buildNumericSample(table, "Year");
        IVector<Double> Y = CsvDataLoader.TableLoader.buildNumericSample(table, "Mean");

        LineChart plotter = new LineChart();
        LineChart.LineChartOptions options = plotter.new LineChartOptions();
        options.chartTitle = "Per Year Mean";
        options.xAxisName = "Year";
        options.yAxisName = "Mean";
        LineChart.plotLine(X, Y, options);

    }

}