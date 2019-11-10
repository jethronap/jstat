package stats.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Point {


    /**
     *
     * Creates a List of random Points.
     */
    public static List<Point> createRandomPoints(int min, int max, int number) {

        List<Point> points = new ArrayList(number);
        for (int i = 0; i < number; i++) {
            points.add(createRandomPoint(min, max));
        }
        return points;
    }


    /**
     * Creates a random Point.
     * @param min minimum coordinates.
     * @param max maximum coordinates.
     * @return A random Point.
     */
    public static Point createRandomPoint(int min, int max) {

        Random random = new Random();
        double x = min + (max - min) * random.nextDouble();
        double y = min + (max - min) * random.nextDouble();

        return new Point(x, y);
    }


    /**
     * Calculates the Euclidean distance between two Points.
     *
     * @param p        The first Point.
     * @param centroid The second Point.
     * @return The distance.
     */
    public static double distance(Point p, Point centroid) {
        return new EuclideanDistance().calculateDistance(p, centroid);
    }


    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public double getX() {
        return x;
    }


    public void setX(double x) {
        this.x = x;
    }


    public double getY() {
        return y;
    }


    public void setY(double y) {
        this.y = y;
    }


    public int getClusterNumber() {
        return clusterNumber;
    }

    public void setClusterNumber(int clusterNumber) {
        this.clusterNumber = clusterNumber;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Point)) {
            return false;
        }
        else {
            return this.getX() == ((Point) obj).getX() && this.getY() == ((Point) obj).getY();
        }
    }


    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


    /**
     * the x-coordinate of the Point.
     */
    private double x = 0;
    /**
     * the y-coordinate of the Point.
     */
    private double y = 0;
    /**
     * cluster where Point belongs.
     */
    private int clusterNumber = 0;
}
