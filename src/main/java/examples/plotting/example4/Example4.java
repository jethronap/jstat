package examples.plotting.example4;

import dataloader.CsvDataLoader;
import tech.tablesaw.api.Table;
import visualizations.PieChart;

import java.io.File;
import java.io.IOException;

/**
 * Category: Plotting
 * ID: PlotAPie
 * Description: Load a CSV file and plot a pie chart
 */
public class Example4 {

    public static void main(String[] args) throws IOException {

        File file = new File("data/humans_data.csv");
        Table table = CsvDataLoader.TableLoader.parseFile(file);

        PieChart plotter = new PieChart();
        PieChart.PieChartOptions options = plotter.new PieChartOptions();
        options.chartTitle = "height by sex";
        options.groupColName = "Sex";
        options.numericColName = "Height";

        PieChart.plotPie(options, table);
    }
}
