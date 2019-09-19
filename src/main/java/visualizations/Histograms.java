package visualizations;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.Histogram;

/**
 * Class that plots basic Histograms.
 */

public class Histograms {

    public class HistogramOptions {
        public String chartTitle;
        public String columnName;
    }

    /**
     * Plots a histogram given the chart title,
     * data set in Table format,
     * and a column name.
     */

    public static void plotHistogram(HistogramOptions options, Table data) {

        Plot.show(Histogram.create(options.chartTitle, data, options.columnName));
    }
}
