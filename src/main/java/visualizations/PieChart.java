package visualizations;

import tech.tablesaw.api.Table;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.PiePlot;

/**
 * Class that plots basic Pie charts.
 */
public class PieChart {

    public class PieChartOptions {
        public String chartTitle;
        public String groupColName;
        public String numericColName;
    }

    /**
     * Plots basic pie chart given
     * chart options and data set in Table format.
     */
    public static void plotPie(PieChartOptions options, Table data) {

        Plot.show(PiePlot.create(options.chartTitle, data, options.groupColName, options.numericColName));

    }
}
