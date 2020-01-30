# Example 2: Histogram Plot 

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
import visualizations.Histograms;

import java.io.File;
import java.io.IOException;

```

## <a name="m_func"></a> The main function

```
public class Example2 {

    public static void main(String[] args) throws IOException {
        File file = new File("test_data/annual.csv");
        Table data = CsvDataLoader.TableLoader.parseFile(file);

        // Extract numeric samples from the data
        NumericSample X = CsvDataLoader.TableLoader.buildNumericSample(data, "Mean");

        Histograms plotter = new Histograms();
        Histograms.HistogramOptions options = plotter.new HistogramOptions();
        options.chartTitle = "distribution of mean temperature";
        options.xAxisName = "Mean";

        Histograms.plotHistogram(options, data);
    }
}

```

## <a name="results"></a> Results

```

```

## <a name="source_code"></a> Source Code

<a href="Example2.java">Example2.java</a>