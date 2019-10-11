package examples;

import dataloader.CsvDataLoader;
import datastructs.NumericSample;
import stats.utils.Record;
import tech.tablesaw.api.Table;
import tech.tablesaw.columns.Column;
import visualizations.ScatterChart;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Category: Statistics, K-Means Clustering.
 * ID: KMeansExample
 */
public class KMeansExample {

    public static void main(String[] args) throws IOException {
        File file = new File("data/clustering.csv");
        Table dataSet = CsvDataLoader.TableLoader.parseFile(file);

        ScatterChart plotter = new ScatterChart();
        ScatterChart.ScatterChartOptions options = plotter.new ScatterChartOptions();
        options.chartTitle = "debt amount by income";
        options.xAxisName = "ApplicantIncome";
        options.yAxisName = "LoanAmount";

        ScatterChart.plotScatter(options, dataSet);


//        List<String> clients = Collections.singletonList(dataSet.column(0).toString());
//        List<Column<?>> loanStatus = dataSet.columns();
//        List<Record> records = dataSet;
//        records.add((Record) clients);
//        records.add((Record) loanStatus);


    }
}
