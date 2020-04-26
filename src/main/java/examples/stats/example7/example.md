# Type II error and the sample size

## Contents
 * [Overview](#overview) 
 * [Import files](#include_files)
 * [The main function](#m_func)
 * [Results](#results)
 * [Source Code](#source_code)

## <a name="overview"></a> Overview

## <a name="include_files"></a> Import files

 ```
package examples.stats.example7;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
 ```

## <a name="m_func"></a> The main function

```
public class Example7 {

    public static void main(String[] args){
        final double ALPHA = 0.05;
        final double SIGMA = 2.0;
        int n = 25;
        final double z_ALPHA_HALF = 1.96;
        final double DELTA = 1.0;

        AbstractRealDistribution normalDistribution = new NormalDistribution();
        double beta = normalDistribution.cumulativeProbability(z_ALPHA_HALF - Math.sqrt(n)/SIGMA)-
                normalDistribution.cumulativeProbability(-z_ALPHA_HALF - Math.sqrt(n)/SIGMA);

        System.out.println("beta is "+beta);
        System.out.println("Power of test: " + (1.0- beta));
    }
}
```

## <a name="results"></a> Results

```
beta is 0.29459441823305144
Power of test: 0.7054055817669486
```

 ## <a name="source_code"></a> Source Code
 
 <a href="Example7.java">Example7.java</a>
