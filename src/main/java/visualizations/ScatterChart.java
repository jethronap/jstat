package visualizations;


import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.ScatterPlot;

/**
 * Class that plots basic Scatter Charts.
 */

public class ScatterChart {

    public class ScatterChartOption {
        public String chartTitle;
        public String xAxisName;
        public String yAxisName;
        public String groupColName;
    }

    /**
     * Plots a scatter chart given the chart title,
     * data set in Table format,
     * and two numeric variables.
     */

    public static void plotScatter(ScatterChartOption options, Table data) {

        Plot.show(ScatterPlot.create(options.chartTitle, data, options.xAxisName, options.yAxisName));
    }

    /**
     * Plots a scatter chart given the chart title,
     * data set in Table format,
     * two numeric variables and
     * one categorical.
     */

    public static void plotScatterWithCategorical(ScatterChartOption options, Table data) {

        Plot.show(
                ScatterPlot.create(options.chartTitle,
                        data, options.xAxisName, options.yAxisName, options.groupColName));
    }

}
