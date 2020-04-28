package examples.mc.example4;

import base.CommonConstants;
import io.CSVFileWriter;
import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.ArrayList;
import java.util.List;

public class Example4 {


    public static double target(double x){

        if(x < 0.){
            return 0.0;
        }

        return Math.exp(-x);
    }

    public static void main(String[] args){

        final int N_ITERATIONS = 10000;
        List<Double> pos = new ArrayList<>(N_ITERATIONS);
        for(int i=0; i<N_ITERATIONS; ++i){
            pos.add(0.0);
        }

        CSVFileWriter writer = new CSVFileWriter(CommonConstants.mcExamplesPath()+"/example4/positions.csv");
        writer.writeColumnNames("ITERATION", "POSITION");
        pos.set(0, 2.0);

        writer.writeRow(0, pos.get(0));

        AbstractRealDistribution normal = new NormalDistribution(0.0, 1.0);

        for(int i=1; i<N_ITERATIONS; ++i){
            double xk = pos.get(i-1);
            double xkPlusOne = xk + normal.sample();

            double A = Example4.target(xkPlusOne/xk);

            if(A < 1.0 ){
                pos.set(i, xkPlusOne);
            }
            else{
                pos.set(i, xk);
            }

            writer.writeRow(i, pos.get(i));
        }
    }
}
