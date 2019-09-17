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
        Table data = CsvDataLoader.TableLoader.parseFile(file);

        BarChart.BarChartOptions options = new BarChart.BarChartOptions();
        options.chartTitle = "height by sex";
        options.groupColName = "Sex";
        options.numberColName = "Height";

        BarChart.plotBar(options, data);
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

        BarChart.BarChartOptions options = new BarChart.BarChartOptions();
        options.chartTitle = "height by sex";
        options.groupColName = "Sex";
        options.numberColName = "Height";

        BarChart.plotHorizontalBar(options, table);
    }
}
