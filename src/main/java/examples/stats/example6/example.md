# Hypothesis tests on the mean of a normal distribution with known variance

## Contents
 * [Overview](#overview) 
 * [Import files](#include_files)
 * [The main function](#m_func)
 * [Results](#results)
 * [Source Code](#source_code)

## <a name="overview"></a> Overview

## <a name="include_files"></a> Import files

 ```
package examples.stats.example6;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;

import utils.ListMaths;
import utils.ListUtils;

import java.util.List;
 ```

## <a name="m_func"></a> The main function

```
public class Example6 {

    public static void main(String[] arg){

        final int SIZE = 20;
        final double MU = 1.0;
        final double STD = 0.3;
        final double ALPHA = 0.05;

        AbstractRealDistribution normalDistribution = new NormalDistribution(MU, STD);
        List<Double> values = ListUtils.randomSample(SIZE, normalDistribution);

        AbstractRealDistribution standardNormalDistribution = new NormalDistribution();

        //compute the z-score
        double xbar = ListMaths.sum(values)/(double)values.size();
        double z = (xbar - MU)/(0.3/Math.sqrt((double) SIZE));

        System.out.println("Sample mean: " + xbar);
        System.out.println("z-score computed: "+z);
        System.out.println("Significance level: "+ALPHA);

        // test the hypothesis
        // H_0: \mu = 1.0
        // H_a: \mu > 1.0

        double p_value = 1.0 - standardNormalDistribution.cumulativeProbability(z);
        
        System.out.println();
        System.out.println("Upper tail test");

        // we reject H_0 if p-value < ALPHA
        if(p_value < ALPHA){
            System.out.println("The null hypothesis is rejected " +
                    "with p-value " + p_value + " and significance level " + ALPHA);
        }
        else{
            System.out.println("With p-value " + p_value + " and significance level " + ALPHA + " cannot reject H0");
        }

        System.out.println();

        // test the hypothesis
        // H_0: \mu = 1.0
        // H_a: \mu < 1.0

        p_value = standardNormalDistribution.cumulativeProbability(z);

        System.out.println("Lower tail test");

        // we reject H_0 if p-value < ALPHA
        if(p_value < ALPHA){
            System.out.println("The null hypothesis is rejected " +
                    "with p-value " + p_value + " and significance level " + ALPHA);
        }
        else{
            System.out.println("With p-value " + p_value + " and significance level " + ALPHA + " cannot reject H0");
        }

        System.out.println();
        System.out.println("Two sided test");

        // test the hypothesis
        // H_0: \mu = 1.0
        // H_a: \mu != 1.0

        p_value = 2.0*(1.0 - standardNormalDistribution.cumulativeProbability(Math.abs(z)));

        // we reject H_0 if p-value < ALPHA
        if(p_value < ALPHA){
            System.out.println("The null hypothesis is rejected " +
                    "with p-value " + p_value + " and significance level " + ALPHA);
        }
        else{
            System.out.println("With p-value " + p_value + " and significance level " + ALPHA + " cannot reject H0");
        }
    }
}

```

## <a name="results"></a> Results

```
Sample mean: 0.9828392170518672
z-score computed: -0.2558178481276285
Significance level: 0.05

Upper tail test
With p-value 0.6009542542169531 and significance level 0.05 cannot reject H0

Lower tail test
With p-value 0.39904574578304686 and significance level 0.05 cannot reject H0

Two sided test
With p-value 0.7980914915660937 and significance level 0.05 cannot reject H0
```

 ## <a name="source_code"></a> Source Code
 
 <a href="Example6.java">Example6.java</a>