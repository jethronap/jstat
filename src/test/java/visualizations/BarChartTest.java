package visualizations;

import dataloader.CsvDataLoader;
import org.junit.Test;
import tech.tablesaw.api.Table;

import java.io.File;
import java.io.IOException;

public class BarChartTest {


    /**
     * Test Scenario: The user provides a data set
     * and wants to plot a bar chart.
     * Expected Output: The chart is plotted correctly.
     */
    @Test
    public void testPlotBar() throws IOException {
        File file = new File("test_data/humans_data.csv");
        Table table = CsvDataLoader.TableLoader.parseFile(file);

        BarChart chart = new BarChart();
        chart.plotBar(
                "height by sex", table,
                "Sex", "Height");
    }

    /**
     * Test Scenario: The user provides a data set
     * and wants to plot a horizontal bar chart.
     * Expected Output: The chart is plotted correctly.
     */
    @Test
    public void testPlotHorizontalBar() throws IOException {
        File file = new File("test_data/humans_data.csv");
        Table table = CsvDataLoader.TableLoader.parseFile(file);

        BarChart chart = new BarChart();
        chart.plotHorizontalBar(
                "height by sex", table,
                "Sex", "Height");
    }
}
