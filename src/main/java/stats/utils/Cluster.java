package stats.utils;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

    /**
     * Constructor for new Cluster.
     * @param id
     */
    public Cluster(int id) {
        this.id = id;
        this.points = new ArrayList();
        this.centroid = null;
    }


    /**
     * Clears the Cluster.
     */
    public void clear() {
        points.clear();
    }


    /**
     * Adds points to Cluster.
     */
    public void addPoint(Point point) {
        points.add(point);
    }


    public List getPoints() {
        return points;
    }


    public void setPoints(List points) {
        this.points = points;
    }


    public Point getCentroid() {
        return centroid;
    }


    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    /**
     * List of Points in Cluster.
     */
    private List points;


    /**
     * The central Point.
     */
    private Point centroid;


    /**
     * Cluster's id.
     */
    private int id;
}
