package stats.utils;

import java.util.Map;

public class EuclideanDistance implements Distance {

    @Override
    public double calculate(final Map<String, Double> v1, final Map<String, Double> v2) {

        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException("Vectors cannot be null.");
        }

        double sum = 0;

        for (String key: v1.keySet()) {
            Double s1 = v1.get(key);
            Double s2 = v2.get(key);

            if (s1 != null && s2 != null) {
                sum += Math.pow(s1 - s2, 2);
            }
        }
        return sum;
    }
}
