package iterators;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// Iterator that uses a Predicate to filter out elements from the input
public class Filter<T> extends FlatApply<T, T> {

    public Filter(Predicate<T> p, Iterator<T> input) {
        // you DO NOT need to modify the constructor
        super(new FilteringFlatApplyFunction<>(p), input);
    }

    // uses a Predicate to decide whether the input element is output or not
    private static class FilteringFlatApplyFunction<T> implements FlatApplyFunction<T, T> {

        private final Predicate temp;

        public FilteringFlatApplyFunction(Predicate<T> p) {
            this.temp = p;
        }

        @Override
        public List<T> apply(T x) {
            LinkedList<T> list = new LinkedList<T>();
            boolean result = temp.check(x);
            if (result) {
                list.add(x);
            }
            return list;
        }
    }

    // You DO NOT need to define hasNext() and next(). FlatApply provides them already.
}
