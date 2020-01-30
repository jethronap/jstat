# Example 3: Line Plot

## Contents
* [Overview](#overview) 
* [Import files](#include_files)
* [The main function](#m_func)
* [Results](#results)
* [Source Code](#source_code)

## <a name="overview"></a> Overview

## <a name="include_files"></a> Import files

```
import dataloader.CsvDataLoader;
import datastructs.NumericSample;
import tech.tablesaw.api.Table;
import visualizations.LineChart;

import java.io.File;
import java.io.IOException;

```

## <a name="m_func"></a> The main function

```
public class Example3 {

    public static void main(String[] args) throws IOException {


        File file = new File("data/annual.csv");
        Table table = CsvDataLoader.TableLoader.parseFile(file);

        // extract numeric samples from the data
        NumericSample X = CsvDataLoader.TableLoader.buildNumericSample(table, "Year");
        NumericSample Y = CsvDataLoader.TableLoader.buildNumericSample(table, "Mean");

        LineChart plotter = new LineChart();
        LineChart.LineChartOptions options = plotter.new LineChartOptions();
        options.chartTitle = "Per Year Mean";
        options.xAxisName = "Year";
        options.yAxisName = "Mean";
        LineChart.plotLine(X, Y, options);

    }

}

```

## <a name="results"></a> Results

```

```

## <a name="source_code"></a> Source Code

<a href="Example3.java">Example3.java</a>