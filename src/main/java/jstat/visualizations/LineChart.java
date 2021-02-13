package jstat.visualizations;


import org.nd4j.linalg.api.ndarray.INDArray;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.LinePlot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Line;
import tech.tablesaw.plotly.traces.ScatterTrace;

/**
 * Class that plots basic Line Charts.
 */

public class LineChart {

    public class LineChartOptions {
        public String chartTitle;
        public double smoothing;
        public String xAxisName;
        public String yAxisName;

    }

    /**
     * Plots a line given the chart options and two numeric columns
     */
    public static void plotLine(INDArray x, INDArray y, LineChartOptions options){

        DoubleColumn xCol = DoubleColumn.create(options.xAxisName, x.toDoubleVector());
        DoubleColumn yCol = DoubleColumn.create(options.yAxisName, y.toDoubleVector());
        Table table = Table.create(xCol, yCol);
        Plot.show(LinePlot.create(options.chartTitle, table, options.xAxisName, options.yAxisName));
    }

    /**
     * Plots a line given the chart options,
     * and data set in Table format.
     */
    public static void plotLine(LineChartOptions options, Table data) {
        Plot.show(LinePlot.create(options.chartTitle, data, options.xAxisName, options.yAxisName));
    }

    /**
     * Plots a smooth line given the chart options,
     * and data set in Table format.
     */

    public static void plotSmoothLine(LineChartOptions options, Table data) {

        Layout layout = Layout.builder().title(options.chartTitle).build();
        ScatterTrace trace = ScatterTrace
                .builder(data.numberColumn(options.xAxisName), data.numberColumn(options.yAxisName))
                .mode(ScatterTrace.Mode.LINE)
                .line(Line.builder().shape(Line.Shape.SPLINE).smoothing(options.smoothing).build())
                .build();
        Plot.show(new Figure(layout, trace));
    }

}
