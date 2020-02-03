package utils;

public class PairBuilder {

    public static final <T, U> Pair<T, U> makePair(T t, U u){
        Pair<T, U> p = new Pair();
        p.first = t;
        p.second =u;
        return p;
    }
}
