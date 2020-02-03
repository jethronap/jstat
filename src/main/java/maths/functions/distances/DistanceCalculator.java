package maths.functions.distances;

import java.util.Comparator;

/**
  *  Some path finding algorithms like A* use heuristic functions
  *  in order to work. Often this heuristic may simply be the distance
  *  between two positions. This interface provides a common contract
  *  for distance claculation between two Points
  *
 */
public interface DistanceCalculator<PointType, ResultType> {

    /**
     * Returns the distance between the two points
     * @param p1 the first  point
     * @param p2 the second point
     */
    ResultType calculate(final PointType p1, final PointType p2);

    /**
     * Initialize the min distance
     */
    ResultType minValue();

    /**
     * Initialize the maximum distance
     */
    ResultType maxValue();

    /**
     * Compare the two results
     */
    ResultType compareMin(ResultType r1, ResultType r2);

    /**
     * Returns
     * -1 if r1 < r2
     * 0  if r1 == r2
     * 1 if  r1 > r2
     */
    int compare(ResultType r1, ResultType r2);
}
