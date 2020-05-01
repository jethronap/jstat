package examples.mc.example3;

import java.util.concurrent.ThreadLocalRandom;

public class Example3 {

    static public void main(String[] args){



        final  int N_ITERATIONS = 10000;
        final double DELTA = 0.1;
        double x = 1.0;
        double y = 1.0;
        double area_under_curve = 0.0;

        for(int itr=0; itr < N_ITERATIONS; ++itr){

            double del_x = ThreadLocalRandom.current().nextDouble(-DELTA, DELTA);
            double del_y = ThreadLocalRandom.current().nextDouble(-DELTA, DELTA);

            if(Math.abs(x + del_x) < 1.0 && Math.abs(y + del_y) < 1.0){
                x += del_x;
                y += del_y;
            }

            if(x*x + y*y < 1.0){
                area_under_curve += 1;
            }
        }

        System.out.println("Pi is: " + 4.0* area_under_curve/(double)N_ITERATIONS);

    }
}
