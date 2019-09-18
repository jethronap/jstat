package visualizations;


import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.BubblePlot;
import tech.tablesaw.plotly.api.ScatterPlot;

/**
 * Class that plots basic Scatter Charts.
 */

public class ScatterChart {

    public class ScatterChartOptions {
        public String chartTitle;
        public String xAxisName;
        public String yAxisName;
        public String groupColName;
        public String sizeColName;
    }

    /**
     * Plots a scatter chart given the chart title,
     * data set in Table format,
     * and two numeric variables.
     */

    public static void plotScatter(ScatterChartOptions options, Table data) {

        Plot.show(ScatterPlot.create(options.chartTitle, data, options.xAxisName, options.yAxisName));
    }

    /**
     * Plots a scatter chart given the chart title,
     * data set in Table format,
     * two numeric variables and
     * one categorical.
     */

    public static void plotScatterWithCategorical(ScatterChartOptions options, Table data) {

        Plot.show(
                ScatterPlot.create(options.chartTitle,
                        data, options.xAxisName, options.yAxisName, options.groupColName));
    }

    /**
     * Plots a bubble chart given the chart title,
     * data set in Table format,
     * and three numeric variables.
     */

    public static void plotScatter3D (ScatterChartOptions options, Table data) {

        Plot.show(BubblePlot.create(options.chartTitle,
                data, options.xAxisName, options.yAxisName, options.sizeColName));
    }
}
