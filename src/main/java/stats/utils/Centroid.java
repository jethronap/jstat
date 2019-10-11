package stats.utils;

import java.util.Map;
import java.util.Objects;

/**
 * Encapsulates all coordinates for a particular cluster centroid.
 */
public class Centroid {


    public Centroid(Map<String, Double> coordinates) {
        this.coordinates = coordinates;
    }


    public Map<String, Double> getCoordinates() {
        return coordinates;
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Centroid)) {
            return false;
        }
        Centroid centroid = (Centroid) other;
        return Objects.equals(getCoordinates(), centroid.getCoordinates());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getCoordinates());
    }


    @Override
    public String toString() {
        return "Centroid" + coordinates;
    }


    /**
     * The centroid coordinates.
     */
    private final Map<String, Double> coordinates;
}
