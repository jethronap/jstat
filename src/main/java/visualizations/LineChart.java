package visualizations;

import tech.tablesaw.api.NumericColumn;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.components.Figure;
import tech.tablesaw.plotly.components.Layout;
import tech.tablesaw.plotly.components.Line;
import tech.tablesaw.plotly.traces.ScatterTrace;

/**
 * Class that plots basic Line Charts.
 */

public class LineChart {

    /**
     * Plots a line given the chart title,
     * and two numeric columns.
     */
    public static void plotLine(
            String chartTitle, NumericColumn x, NumericColumn y) {

        Layout layout = Layout.builder().title(chartTitle).build();
        ScatterTrace trace = ScatterTrace.builder(x, y).mode(ScatterTrace.Mode.LINE).build();
        Plot.show(new Figure(layout, trace));

    }

    /**
     * Plots a line given the chart title,
     * two numeric columns and custom
     * smoothing.
     */

    public static void plotSmoothLine(
            String chartTitle, NumericColumn x, NumericColumn y, Double smoothing) {

        Layout layout = Layout.builder().title(chartTitle).build();
        ScatterTrace trace = ScatterTrace.builder(x, y)
                .mode(ScatterTrace.Mode.LINE)
                .line(Line.builder().shape(Line.Shape.SPLINE).smoothing(smoothing).build())
                .build();
        Plot.show(new Figure(layout, trace));
    }

}
