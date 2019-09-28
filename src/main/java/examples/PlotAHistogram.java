package examples;

import dataloader.CsvDataLoader;
import datastructs.NumericSample;
import tech.tablesaw.api.Table;
import visualizations.Histograms;

import java.io.File;
import java.io.IOException;


/**
 * Category: Plotting
 * ID: PlotAHistogram
 * Description: Load a CSV file and plot the distribution of a numeric sample
 */

public class PlotAHistogram {

    public static void main(String[] args) throws IOException {
        File file = new File("test_data/annual.csv");
        Table data = CsvDataLoader.TableLoader.parseFile(file);

        // Extract numeric samples from the data
        NumericSample X = CsvDataLoader.TableLoader.buildNumericSample(data, "Mean");

        Histograms plotter = new Histograms();
        Histograms.HistogramOptions options = plotter.new HistogramOptions();
        options.chartTitle = "distribution of mean temperature";
        options.xAxisName = "Mean";

        Histograms.plotHistogram(options, data);
    }
}
