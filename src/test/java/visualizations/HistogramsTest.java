package visualizations;

import dataloader.CsvDataLoader;
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
}
