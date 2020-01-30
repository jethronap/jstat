# Example 4: Pie Plot

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
import visualizations.PieChart;

import java.io.File;
import java.io.IOException;

```

## <a name="m_func"></a> The main function

```
public class Example4 {

    public static void main(String[] args) throws IOException {

        File file = new File("data/humans_data.csv");
        Table table = CsvDataLoader.TableLoader.parseFile(file);

        PieChart plotter = new PieChart();
        PieChart.PieChartOptions options = plotter.new PieChartOptions();
        options.chartTitle = "height by sex";
        options.groupColName = "Sex";
        options.numericColName = "Height";

        PieChart.plotPie(options, table);
    }
}


```

## <a name="results"></a> Results

```

```

## <a name="source_code"></a> Source Code

<a href="Example4.java">Example4.java</a>