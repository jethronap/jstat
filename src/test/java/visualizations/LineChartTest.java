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

        LineChart chart = new LineChart();
        chart.plotLine("temperature mean per year", table, "Year", "Mean");
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

        LineChart chart = new LineChart();
        chart.plotSmoothLine("temperature mean per year", table, "Year", "Mean", 1.2);
    }
}
