package examples.plotting.example5;

import dataloader.CsvDataLoader;
import tech.tablesaw.api.Table;
import visualizations.ScatterChart;

import java.io.File;
import java.io.IOException;

/**
 * Category: Plotting
 * ID: PlotABar
 * Description: Load a CSV file and plot a scatter chart with 3 numeric variables
 */
public class Example5 {

    public static void main(String[] args) throws IOException {
        File file = new File("data/humans_data.csv");
        Table data = CsvDataLoader.TableLoader.parseFile(file);

        ScatterChart plotter = new ScatterChart();
        ScatterChart.ScatterChartOptions options = plotter.new ScatterChartOptions();
        options.chartTitle = "weight by age and height";
        options.xAxisName = "Age";
        options.yAxisName = "Height";
        options.sizeColName = "Weight";

        ScatterChart.plotScatter3D(options, data);
    }
}
