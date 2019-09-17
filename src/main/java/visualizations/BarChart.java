package visualizations;


import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.HorizontalBarPlot;
import tech.tablesaw.plotly.api.VerticalBarPlot;

/**
 * Class that plots basic Bar charts.
 */
public class BarChart {

    static class BarChartOptions {
        String chartTitle;
        String groupColName;
        String numberColName;

    }
    /**
     * Plots basic bar chart given
     * chart title, data set in Table format,
     * x-axis name, y-axis name.
     */
    public static void plotBar(BarChartOptions options, Table data) {

        Plot.show(VerticalBarPlot.create(options.chartTitle, data, options.groupColName, options.numberColName));
    }

    /**
     * Plots horizontal bar chart given
     * chart title, data set in Table format,
     * x-axis name, y-axis name.
     */
    public static void plotHorizontalBar(BarChartOptions options, Table data) {

        Plot.show(HorizontalBarPlot.create(options.chartTitle, data, options.groupColName, options.numberColName));

    }

}
