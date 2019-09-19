package visualizations;

import dataloader.CsvDataLoader;
import datastructs.NumericSample;
import org.junit.Test;
import tech.tablesaw.api.Table;

import java.io.File;
import java.io.IOException;

public class HistogramsTest {

    /**
     * Test Scenario: The user provides a data set
     * and a numeric column name
     * and want to plot a histogram.
     * Expected Output: The histogram is plotted correctly.
     */
    @Test
    public void testPlotHistogram() throws IOException {
        File file = new File("test_data/humans_data.csv");
        Table data = CsvDataLoader.TableLoader.parseFile(file);

        Histograms plotter = new Histograms();
        Histograms.HistogramOptions options = plotter.new HistogramOptions();
        options.chartTitle = "distribution of age";
        options.xAxisName = "Age";

        Histograms.plotHistogram(options, data);
    }

    /**
     * Test Scenario: The user provides a data set
     * and two numeric column name
     * and wants to plot a 2D histogram.
     * Expected Output: The 2D histogram is plotted correctly.
     */
    @Test
    public void testPlotHistogram2D() throws IOException {
        File file = new File("test_data/humans_data.csv");
        Table data = CsvDataLoader.TableLoader.parseFile(file);

        Histograms plotter = new Histograms();
        Histograms.HistogramOptions options = plotter.new HistogramOptions();
        options.chartTitle = "distribution of age";
        options.xAxisName = "Age";
        options.yAxisName = "Weight";

        Histograms.plotHistogram2D(options, data);
    }

    /**
     * Test Scenario: The user provides a data set
     * and one numeric column
     * and wants to plot a histogram.
     * Expected Output: The histogram is plotted correctly.
     */
    @Test
    public void testPlotHistogramNumSample() throws IOException {
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
