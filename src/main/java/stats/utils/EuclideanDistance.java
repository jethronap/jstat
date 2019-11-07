package stats.utils;

import java.util.Map;

public class EuclideanDistance implements Distance {

    /**
     * @param p1 The first point.
     * @param p2 The second point.
     * @return The distance between two points.
     */
    public double calculateDistance(final Point p1, final Point p2) {

        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

}
