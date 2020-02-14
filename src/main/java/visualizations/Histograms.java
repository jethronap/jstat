package visualizations;

import datastructs.IVector;
import tech.tablesaw.api.DoubleColumn;
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
     * Plots a scatter chart given the chart title,
     * data set in Table format,
     * and one numeric column
     * created from a numeric sample
     * of the data set.
     */

    public static void plotHistogram(IVector<Double> x, HistogramOptions options) {

        DoubleColumn xCol = DoubleColumn.create(options.xAxisName, x.toArray());

        Table table = Table.create(xCol);
        Plot.show(Histogram.create(options.chartTitle, table, options.xAxisName));
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
