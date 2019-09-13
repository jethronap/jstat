package visualizations;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.PiePlot;

/**
 * Class that plots basic Pie charts.
 */
public class PieChart {

    /**
     * Plots basic pie chart given
     * chart title, data set in Table format,
     * x-axis name, y-axis name.
     */
    public static void plotPie (
            String chartTitle, Table table, String groupColName, String numericColName) {

        Plot.show(PiePlot.create(chartTitle, table, groupColName, numericColName));

    }
}
