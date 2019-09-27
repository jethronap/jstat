package examples;

import dataloader.CsvDataLoader;
import tech.tablesaw.api.Table;
import visualizations.BarChart;

import java.io.File;
import java.io.IOException;

/**
 * Category: Plotting
 * ID: PlotABar
 * Description: Load a CSV file and plot a horizontal bar chart
 */
public class PlotABar {

    public static void main(String[] args) throws IOException {
        File file = new File("test_data/humans_data.csv");
        Table table = CsvDataLoader.TableLoader.parseFile(file);

        BarChart plotter = new BarChart();
        BarChart.BarChartOptions options = plotter.new BarChartOptions();
        options.chartTitle = "height by sex";
        options.groupColName = "Sex";
        options.numberColName = "Height";

        BarChart.plotHorizontalBar(options, table);
    }
}
