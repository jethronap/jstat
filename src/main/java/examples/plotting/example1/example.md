# Example 1: Bar Plot 

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
import tech.tablesaw.api.Table;
import visualizations.BarChart;

import java.io.File;
import java.io.IOException;

```

## <a name="m_func"></a> The main function

```
public class Example1 {

    public static void main(String[] args) throws IOException {
        File file = new File("data/humans_data.csv");
        Table table = CsvDataLoader.TableLoader.parseFile(file);

        BarChart plotter = new BarChart();
        BarChart.BarChartOptions options = plotter.new BarChartOptions();
        options.chartTitle = "height by sex";
        options.groupColName = "Sex";
        options.numberColName = "Height";

        BarChart.plotHorizontalBar(options, table);
    }
}


    

```

## <a name="results"></a> Results

```

```

## <a name="source_code"></a> Source Code

<a href="Example1.java">Example1.java</a>