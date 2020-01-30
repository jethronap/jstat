# Example 5: Scatter Plot 

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
import visualizations.ScatterChart;

import java.io.File;
import java.io.IOException;

```

## <a name="m_func"></a> The main function

```
public class Example5 {

    public static void main(String[] args) throws IOException {
        File file = new File("data/humans_data.csv");
        Table data = CsvDataLoader.TableLoader.parseFile(file);

        ScatterChart plotter = new ScatterChart();
        ScatterChart.ScatterChartOptions options = plotter.new ScatterChartOptions();
        options.chartTitle = "weight by age and height";
        options.xAxisName = "Age";
        options.yAxisName = "Height";
        options.sizeColName = "Weight";

        ScatterChart.plotScatter3D(options, data);
    }
}

```

## <a name="results"></a> Results

```

```

## <a name="source_code"></a> Source Code

<a href="Example5.java">Example5.java</a>