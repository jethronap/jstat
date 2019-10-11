package stats.utils;

import java.util.Map;

/**
 * Defines a contract to calculate distance between two vectors.
 */
public interface Distance {

    double calculate(Map<String, Double> v1, Map<String, Double> v2);
}
