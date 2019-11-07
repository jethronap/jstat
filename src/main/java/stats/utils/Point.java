package stats.utils;

public class Point {

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
