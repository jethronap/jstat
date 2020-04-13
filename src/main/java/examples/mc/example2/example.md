# Calculate Calculate the area of a circle using Monte Carlo integration

## Contents
 * [Overview](#overview) 
 * [Import files](#include_files)
 * [The main function](#m_func)
 * [Results](#results)
 * [Source Code](#source_code)

## <a name="overview"></a> Overview

## <a name="include_files"></a> Import files

 ```
package examples.mc.example2;
import java.util.concurrent.ThreadLocalRandom;
 ```

## <a name="m_func"></a> The main function

```
public class Example2 {

    public class Circle{

        public Circle(double r, double x, double y){
            this.r = r;
            this.x = x;
            this.y = y;
        }

        public double radius(){return this.r;}
        public boolean isInside(double x, double y){

            if(Math.pow((this.x - x), 2) + Math.pow(this.y - y, 2) - r*r < 1.0e-9){
                return true;
            }

            return false;
        }
        public double area(){
            return 3.14*r*r;
        }

        double r;
        double x;
        double y;

    }

    public static void main(String[] args){
        Example2 exe = new Example2();


        final int N_ITERATIONS = 10000;

        Circle circle = exe.new Circle(2.0, 0.0, 0.0);

        final double x0 = -circle.radius();
        final double x1 = circle.radius();
        final double y0 = -circle.radius();
        final double y1 = circle.radius();
        final double RECT_AREA = (x1 - x0)*(y1 - y0);
        
        double totalArea = 0.0;
        double areaUnderCurve = 0.0;

        for(int i=0; i<N_ITERATIONS; ++i){

            double x = ThreadLocalRandom.current().nextDouble(x0, x1);
            double y = ThreadLocalRandom.current().nextDouble(y0, y1);

            totalArea += 1.0;

            if( circle.isInside(x,y)){
                areaUnderCurve += 1;
            }
        }

        System.out.println("Rectangle area: " + RECT_AREA);
        System.out.println("Total area points: " + totalArea);

        if(areaUnderCurve != 0.){

            System.out.println("Area under curve points: " + areaUnderCurve);
            System.out.println("Calculated area: " + RECT_AREA*(areaUnderCurve/totalArea));
            System.out.println("Circle area: " + circle.area());
        }
    }
}
```

## <a name="results"></a> Results

```
Rectangle area: 16.0
Total area points: 10000.0
Area under curve points: 7789.0
Calculated area: 12.4624
Circle area: 12.56
```

 ## <a name="source_code"></a> Source Code
 
 <a href="Example2.java">Example2.java</a>