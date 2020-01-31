package utils;
import parallel.tasks.SumSqrDiffTask;
import parallel.tasks.SumSqrTask;
import parallel.tasks.SumTask;
import tech.tablesaw.api.DoubleColumn;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

/**
 * Utilities for all classes that implement the List interface
 */
public class ListMaths {


    /**
     * Compute the max of the List elements
     */
    public static double max(final List<Double> data){

        double rslt =  data.get(0);

        for(int i=1; i<data.size(); ++i){

            if(rslt < data.get(i)){
                rslt = data.get(i);
            }
        }

        return rslt;
    }


    /**
     * Compute the min of the List elements
     */
    public static double min(final List<Double> data){

        double rslt =  data.get(0);

        for(int i=1; i<data.size(); ++i){

            if(rslt >  data.get(i)){
                rslt =  data.get(i);
            }
        }

        return rslt;
    }


    /**
     * Compute the sum of the  List elements
     */
    public static double sum(final List<Double> data){

        double rslt = 0.0;

        for(int i=0; i<data.size(); ++i){
                rslt +=  data.get(i);
        }

        return rslt;
    }

    /**
     * Compute the square sum
     */
    public static double sqrSum(final List<Double> data){

        double rslt = 0.0;

        for(int i=0; i<data.size(); ++i){
            rslt +=  data.get(i)*data.get(i);
        }

        return rslt;
    }

    /**
     * Compute \Sum_ x^2 - (1/N)(\Sum_ x)^2
     * If the weight factor should be 1/(N-1) use the reduced version of this function
     */
    public static double sxx(final List<Double> data){

        CyclicBarrier barrier = new CyclicBarrier(2);

        SumTask<List<Double>> sumTask = new SumTask<>(data, barrier);
        Thread sum = new Thread(sumTask);
        sum.start();

        SumSqrTask<List<Double>> sumSqrTask = new SumSqrTask(data, barrier);
        Thread sumSqr = new Thread(sumSqrTask);
        sumSqr.start();

        while(!sumTask.isFinished() || !sumSqrTask.isFinished()){
            //spin the main thread
        }

        return sumSqrTask.getResult() - (1./data.size())*(sumTask.getResult()*sumTask.getResult());
    }


    /**
     * Sum Squared Regression or SSR = \Sum (data[i] - \bar(data))^2
     *
     * if data is an approximation coming from a model then this is the
     * SSR or Sum Squared Regression. If data is an observation then this
     * will be  the SST or Sum Squared Total
     */
    public static double sse(final List<Double> data){

        double mean = ListMaths.sum(data)/data.size();

        SumSqrDiffTask<List<Double>> task = new SumSqrDiffTask<>(data, mean);
        task.run();

        return task.getResult();
    }

    /**
     * Compute the sum of the absolute values of the List elements
     */
    public static double absSum(final List<Double> data){

        double rslt = 0.0;

        for (int i=0; i<data.size(); ++i){

            rslt += StrictMath.abs(data.get(i));
        }

        return rslt;
    }


    /**
     * Normalize the data in the List.
     * See: https://www.statisticshowto.datasciencecentral.com/normalized/
     */
    public static void normalize(List<Double> data){

       double min = ListMaths.min(data);
       double max = ListMaths.max(data);

       for(int i=0; i<data.size(); ++i){
           double x = data.get(i);
           data.set(i, (x-min)/(max - min));
       }
    }

    /**
     * Normalize the data in the List.
     * See: https://www.statisticshowto.datasciencecentral.com/normalized/
     */
    public static void normalize(DoubleColumn data){

        double min = ListMaths.min(data.asList());
        double max = ListMaths.max(data.asList());

        for(int i=0; i<data.size(); ++i){
            double x = data.get(i);
            data.set(i, (x-min)/(max - min));
        }
    }




}
