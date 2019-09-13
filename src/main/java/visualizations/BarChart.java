package visualizations;


import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.HorizontalBarPlot;
import tech.tablesaw.plotly.api.VerticalBarPlot;

/**
 * Class that plots basic Bar charts.
 */
public class BarChart {

    /**
     * Plots basic bar chart given
     * chart title, data set in Table format,
     * x-axis name, y-axis name.
     */
    public static void plotBar(
            String chartTitle, Table table, String groupColName, String numberColName) {

        Plot.show(VerticalBarPlot.create(chartTitle, table, groupColName, numberColName));
    }

    /**
     * Plots horizontal bar chart given
     * chart title, data set in Table format,
     * x-axis name, y-axis name.
     */
    public static void plotHorizontalBar(
            String chartTitle, Table table, String groupColName, String numberColName) {

        Plot.show(HorizontalBarPlot.create(chartTitle, table, groupColName, numberColName));

    }

}
