package stats.utils;

import java.util.Map;

/**
 * Defines a contract to calculate distance between two sets of properties.
 */
public interface Distance {

    /**
     * @param v1 The first set.
     * @param v2 The second set.
     * @return The calculated distance.
     * @throws IllegalArgumentException If the given set is invalid.
     */
    double calculate(Map<String, Double> v1, Map<String, Double> v2);
}
