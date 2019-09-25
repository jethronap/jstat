package examples;

import dataloader.CsvDataLoader;
import datastructs.NumericSample;
import tech.tablesaw.api.Table;
import visualizations.LineChart;

import java.io.File;
import java.io.IOException;


/**
 * Category: Plotting
 * ID: PlotALine
 * Description: Load a CSV file and plot two columns against each other
 */

public class PlotALine {

    public static void main(String[] args) throws IOException {


        File file = new File("data/annual.csv");
        Table table = CsvDataLoader.TableLoader.parseFile(file);

        // extract numeric samples from the data
        NumericSample X = CsvDataLoader.TableLoader.buildNumericSample(table, "Year");
        NumericSample Y = CsvDataLoader.TableLoader.buildNumericSample(table, "Mean");

        LineChart plotter = new LineChart();
        LineChart.LineChartOptions options = plotter.new LineChartOptions();
        options.chartTitle = "Per Year Mean";
        options.xAxisName = "Year";
        options.yAxisName = "Mean";
        LineChart.plotLine(X, Y, options);

    }

}