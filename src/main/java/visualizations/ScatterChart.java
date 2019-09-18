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
    }

    /**
     * Plots a scatter chart given the chart title,
     * data set in Table format,
     * and two numeric columns.
     */

    public static void plotScatter(ScatterChartOption options, Table data) {

        Plot.show(ScatterPlot.create(options.chartTitle, data, options.xAxisName, options.yAxisName));
    }
}
