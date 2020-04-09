# Calculate Descriptive Statistics Metrics

## Contents
 * [Overview](#overview) 
 * [Import files](#include_files)
 * [The main function](#m_func)
 * [Results](#results)
 * [Source Code](#source_code)

## <a name="overview"></a> Overview

## <a name="include_files"></a> Import files

 ```
package examples.stats.example5;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import utils.ListUtils;
import java.util.List;
 ```

## <a name="m_func"></a> The main function

```
public class Example5 {

    public static void main(String[] args){

        final int SIZE = 20;
        final double MU = 1.0;
        final double STD = 0.3;

        /// populate the list using the NormalDistribution
        AbstractRealDistribution normalDistribution = new NormalDistribution(MU, STD);

        List<Double> values = ListUtils.randomSample(SIZE, normalDistribution);

        DescriptiveStatistics stats = new DescriptiveStatistics(ListUtils.toDoubleArray(values));

        System.out.println("Mean is: "+ stats.getMean());
        System.out.println("Median is: "+stats.getPercentile(50));
        System.out.println("Min is: "+stats.getMin());
        System.out.println("Max is: "+stats.getMax());
    }
}

```

## <a name="results"></a> Results

```
Mean is: 1.0282365100654927
Median is: 1.016467404051775
Min is: 0.520894109764956
Max is: 1.6848924957278655
```

 ## <a name="source_code"></a> Source Code
 
 <a href="Example5.java">Example5.java</a>