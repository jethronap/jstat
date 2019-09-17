package visualizations;

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

    static class LineChartOptions {
        String chartTitle;
        String nameXCol;
        String nameYCol;
        Double smoothing;

    }

    /**
     * Plots a line given the chart title,
     * data set in Table format,
     * and two numeric columns.
     */
    public static void plotLine(LineChartOptions options, Table data) {

        Plot.show(LinePlot.create(options.chartTitle, data, options.nameXCol, options.nameYCol));

    }

    /**
     * Plots a line given the chart title,
     * data set in Table format,
     * two numeric columns and custom
     * smoothing.
     */

    public static void plotSmoothLine(LineChartOptions options, Table data) {

        Layout layout = Layout.builder().title(options.chartTitle).build();
        ScatterTrace trace = ScatterTrace
                .builder(data.numberColumn(options.nameXCol), data.numberColumn(options.nameYCol))
                .mode(ScatterTrace.Mode.LINE)
                .line(Line.builder().shape(Line.Shape.SPLINE).smoothing(options.smoothing).build())
                .build();
        Plot.show(new Figure(layout, trace));
    }

}
