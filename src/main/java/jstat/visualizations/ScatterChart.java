package jstat.visualizations;


import jstat.datastructs.IVector;
import tech.tablesaw.api.DoubleColumn;
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
     * Plots a scatter chart given the chart options
     * and two numeric columns
     * created from a numeric sample
     * of the data set.
     */

    public static void plotScatter(ScatterChartOptions options, IVector<Double> x, IVector<Double> y){

        DoubleColumn xCol = DoubleColumn.create(options.xAxisName, x.toArray());
        DoubleColumn yCol = DoubleColumn.create(options.yAxisName, y.toArray());
        Table table = Table.create(xCol, yCol);
        Plot.show(ScatterPlot.create(options.chartTitle, table, options.xAxisName, options.yAxisName));
    }

    /**
     * Plots a scatter chart given the chart options,
     * and a data set in Table format,
     */

    public static void plotScatter(ScatterChartOptions options, Table data) {

        Plot.show(ScatterPlot.create(options.chartTitle, data, options.xAxisName, options.yAxisName));
    }

    /**
     * Plots a scatter chart given the chart options,
     * and data set in Table format,
     */

    public static void plotScatterWithCategorical(ScatterChartOptions options, Table data) {

        Plot.show(
                ScatterPlot.create(options.chartTitle,
                        data, options.xAxisName, options.yAxisName, options.groupColName));
    }

    /**
     * Plots a bubble chart given the chart options,
     * and a data set in Table format,
     */

    public static void plotScatter3D (ScatterChartOptions options, Table data) {

        Plot.show(BubblePlot.create(options.chartTitle,
                data, options.xAxisName, options.yAxisName, options.sizeColName));
    }
}
