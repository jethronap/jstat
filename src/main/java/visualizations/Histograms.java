package visualizations;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.Histogram;
import tech.tablesaw.plotly.api.Histogram2D;

/**
 * Class that plots basic Histograms.
 */

public class Histograms {

    public class HistogramOptions {
        public String chartTitle;
        public String xAxisName;
        public String yAxisName;
    }

    /**
     * Plots a histogram given the chart title,
     * data set in Table format,
     * and a column name.
     */

    public static void plotHistogram(HistogramOptions options, Table data) {

        Plot.show(Histogram.create(options.chartTitle, data, options.xAxisName));
    }

    /**
     * Plots a histogram given the chart title,
     * data set in Table format,
     * and two column names.
     */

    public static void plotHistogram2D(HistogramOptions options, Table data) {
        Plot.show(Histogram2D.create(options.chartTitle, data, options.xAxisName, options.yAxisName));
    }
}
