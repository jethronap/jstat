package ml.classifiers.utils;

import utils.Pair;
import utils.PairBuilder;

import java.util.*;

public class ClassificationVoter implements IVoter<Integer, Double, List<Pair<Integer, Double>> > {


    public ClassificationVoter(){
        this.values = new ArrayList<Pair<Integer, Double>>();
    }

    @Override
    public void addItem(Integer item, Double criterionType){
        this.values.add(PairBuilder.makePair(item, criterionType));
    }

    /**
     * Allow any Object cast to double to be added
     * @param item
     * @param criterionType
     */
    public void addItem(Integer item, Object criterionType){
        this.addItem(item, (Double) criterionType);
    }

    /**
     * Add a list of items T, U should be castable to Integer and Double respectively
     */
    public <T, U> void addItems(List<Pair<T, U>> items){

        for (int i = 0; i < items.size(); i++) {
            this.values.add((Pair<Integer, Double>)items.get(i));
        }
    }

    @Override
    public List<Pair<Integer, Double>> getResult(int max){

        //get the values
        Object[] valuesArr = (Object[]) values.toArray();

        Arrays.sort(valuesArr, new Comparator<Object>() {
            @Override
            public int compare(Object t, Object t1) {

                if(((Pair<Integer, Double>)t).second < ((Pair<Integer, Double>)t1).second ){
                    return -1;
                }
                else if(((Pair<Integer, Double>)t).second > ((Pair<Integer, Double>)t1).second){
                    return 1;
                }

                return 0;
            }
        });

        List<Pair<Integer, Double>> subList = new ArrayList<Pair<Integer, Double>>();

        for(int i=0; i<max; ++i){
            subList.add((Pair<Integer, Double>) valuesArr[i]);
        }


        return subList;
    }


    public void clear(){

        this.values = new ArrayList<Pair<Integer, Double>>();
    }

    private List<Pair<Integer, Double>> values;
}
