package stats.utils;

import java.util.Map;
import java.util.Objects;

/**
 * This is a data structure that will encapsulate
 * all feature values for a few attributes, that are going
 * to be used in the K-means Clustering algorithm.
 */
public class Record {


    public Record(String description, Map<String, Double> features) {
        this.description = description;
        this.features = features;
    }

    /**
     * Constructor for features with an empty
     * String for a description.
     * @param features
     */
    public Record(Map<String, Double> features) {
        this("", features);
    }


    public String getDescription() {
        return description;
    }


    public Map<String, Double> getFeatures() {
        return features;
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Record)) {
            return false;
        }
        Record record = (Record) other;
        return Objects.equals(getDescription(), record.getDescription()) && Objects.equals(getFeatures(), record.getFeatures());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getDescription(), getFeatures());
    }

    @Override
    public String toString() {
        String prefix = description == null || description.trim().isEmpty() ? "Record" : description;
        return prefix + ": " + features;
    }
    /**
     * The record description.
     */
    private final String description;

    /**
     * Encapsulates all attributes and their corresponding values.
     */
    private final Map<String, Double> features;
}
