package visualizations;

import dataloader.CsvDataLoader;
import org.junit.Test;
import tech.tablesaw.api.Table;

import java.io.File;
import java.io.IOException;

public class PieChartTest {


    /**
     *  Test Scenario: The user provides a data set
     *  and wants to plot a pie chart.
     *  Expected Output: The chart is plotted correctly.
     * */
    @Test
    public void testPlotPie() throws IOException {

        File file = new File("test_data/humans_data.csv");
        Table table = CsvDataLoader.TableLoader.parseFile(file);

        PieChart plotter = new PieChart();
        PieChart.PieChartOptions options = plotter.new PieChartOptions();
        options.chartTitle = "height by sex";
        options.groupColName = "Sex";
        options.numericColName = "Height";

        PieChart.plotPie(options, table);
    }
}
