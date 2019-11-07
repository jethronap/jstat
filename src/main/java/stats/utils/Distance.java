package stats.utils;

public interface Distance {

    /**
     * @param p1 The first point.
     * @param p2 The second point.
     * @return The distance between two points.
     */
    double calculateDistance(final Point p1, final Point p2);
}
