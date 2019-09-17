package visualizations;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.PiePlot;

/**
 * Class that plots basic Pie charts.
 */
public class PieChart {

    static class PieChartOptions {
        String chartTitle;
        String groupColName;
        String numericColName;
    }

    /**
     * Plots basic pie chart given
     * chart title, data set in Table format,
     * group column name, numeric column name.
     */
    public static void plotPie (PieChartOptions options, Table data) {

        Plot.show(PiePlot.create(options.chartTitle, data, options.groupColName, options.numericColName));

    }
}