package visualizations;

import dataloader.CsvDataLoader;
import org.junit.Test;
import tech.tablesaw.api.Table;

import java.io.File;
import java.io.IOException;

public class ScatterChartTest {


    /**
     * Test Scenario: The user provides a data set
     * and wants to plot a scatter chart
     * with two numeric variables.
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
     * and one categorical variable.
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

    /**
     * Test Scenario: The user provides a data set
     * and wants to plot a scatter chart
     * with three numeric variables.
     * Expected Output: The chart is plotted correctly.
     */
    @Test
    public void testPlotScatter3D () throws IOException {
        File file = new File("test_data/humans_data.csv");
        Table data = CsvDataLoader.TableLoader.parseFile(file);

        ScatterChart plotter = new ScatterChart();
        ScatterChart.ScatterChartOptions options = plotter.new ScatterChartOptions();
        options.chartTitle = "weight by age and height";
        options.xAxisName = "Age";
        options.yAxisName = "Height";
        options.sizeColName = "Weight";

        ScatterChart.plotScatter3D(options, data);
    }

}
