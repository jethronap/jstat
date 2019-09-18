package visualizations;

import dataloader.CsvDataLoader;
import org.junit.Test;
import tech.tablesaw.api.Table;

import java.io.File;
import java.io.IOException;

public class ScatterChartTest {


    /**
     * Test Scenario: The user provides a data set
     * and wants to plot a scatter chart.
     * Expected Output: The chart is plotted correctly.
     */
    @Test
    public void testPlotScatter () throws IOException {
        File file = new File("test_data/annual.csv");
        Table data = CsvDataLoader.TableLoader.parseFile(file);

        ScatterChart plotter = new ScatterChart();
        ScatterChart.ScatterChartOptions options = plotter.new ScatterChartOptions();
        options.chartTitle = "temperature mean per year";
        options.xAxisName = "Year";
        options.yAxisName = "Mean";

        ScatterChart.plotScatter(options, data);
    }

    /** Test Scenario: The user provides a data set
     * and wants to plot a scatter with two numeric
     * and o categorical variable.
     * Expected Output: The chart is plotted correctly.
     */
    @Test
    public void testPlotScatterWithCategorical() throws IOException {
        File file = new File("test_data/annual.csv");
        Table data = CsvDataLoader.TableLoader.parseFile(file);

        ScatterChart plotter = new ScatterChart();
        ScatterChart.ScatterChartOptions options = plotter.new ScatterChartOptions();
        options.chartTitle = "temperature mean per year per source";
        options.xAxisName = "Year";
        options.yAxisName = "Mean";
        options.groupColName = "Source";

        ScatterChart.plotScatterWithCategorical(options, data);
    }

}
