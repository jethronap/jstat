package utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ListUtils {


    /**
     * Create  pairs of consisting of one element from list1 (Pair.first) and one element from list 2 (Pair.second)
     */
    public static <T, U> List<Pair<T,U>> zip(List<T> lst1, List<U> lst2){

        int minSize = lst1.size() > lst2.size()? lst2.size():lst1.size();

        List<Pair<T, U>> result = new ArrayList<>();

        for (int i = 0; i < minSize; i++) {

            Pair<T,U> pair = PairBuilder.makePair(lst1.get(i), lst2.get(i));
            result.add(pair);

        }

        return result;
    }

    public static  <T> double[] toDoubleArray(final List<T> list){

        double[] array = new double[list.size()];

        for(int i=0; i<list.size(); ++i){
            array[i] = ((Double)list.get(i)).doubleValue();
        }

        return array;
    }

    /**
     * Swap the elements in the list
     */
    public static <T> void swap(List<T> list, int i, int j){

        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    /**
     * Partition the given List
     */
    public static <T> int partition(List<T> list, Comparator<T> comparator){

        return ListUtils.doPartition(list, comparator, 0, list.size()-1);

    }


    private static <T> int  doPartition(List<T> list, Comparator<T> comparator, int start, int end){

        /// Select the pivot randomly
        Random random = new Random();
        int pivot = random.nextInt(end - start) + start;
        ListUtils.swap(list, pivot, end);

        int small = start - 1;

        for(int i = start; i<=end; ++i) {

            if (comparator.compare(list.get(i), list.get(end)) < 0) {

                ++small;

                if (i != small) {
                    ListUtils.swap(list, small, i);
                }
            }
        }

        ++small;

        if( small != end){
            ListUtils.swap(list, small, end);
        }

        return small;
    }
}
