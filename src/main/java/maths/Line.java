package maths;

/**
 * Models a simple line connecting two points
 * TODO: What about polynomial???
 */
public class Line {

    public Line(double a, double b){
        this.a_ = a;
        this.b_ = b;
    }

    public final double value(double x){
        return this.a_ + this.b_ * x;
    }


    public final double[] values(double[] x){

        double[] y = new double[x.length];

        for (int i = 0; i < y.length ; i++) {
            y[i] = this.value(x[i]);
        }

        return y;
    }

    double a_;
    double b_;
}
