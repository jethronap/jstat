package visualizations;

import dataloader.CsvDataLoader;
import org.junit.Test;
import tech.tablesaw.api.Table;

import java.io.File;
import java.io.IOException;

public class LineChartTest {

    /**
     * Test Scenario: The user provides a data set
     * and wants to plot a line chart.
     * Expected Output: The chart is plotted correctly.
     */
    @Test
    public void testPlotLine() throws IOException {
        File file = new File("test_data/annual.csv");
        Table table = CsvDataLoader.TableLoader.parseFile(file);

        LineChart.LineChartOptions options = new LineChart.LineChartOptions();
        options.chartTitle = "temperature mean per year";
        options.nameXCol = "Year";
        options.nameYCol = "Mean";

        LineChart.plotLine(options, table);
    }

    /**
     * Test Scenario: The user provides a data set
     * and wants to plot a smooth line chart.
     * The user provides the desired parameters along
     * with custom smoothing.
     * Expected Output: The chart is plotted correctly.
     */
    @Test
    public void testPlotSmoothLine() throws IOException {
        File file = new File("test_data/annual.csv");
        Table table = CsvDataLoader.TableLoader.parseFile(file);

        LineChart.LineChartOptions options = new LineChart.LineChartOptions();
        options.chartTitle = "temperature mean per year";
        options.nameXCol = "Year";
        options.nameYCol = "Mean";
        options.smoothing = 1.2;

        LineChart.plotSmoothLine(options, table);
    }
}
