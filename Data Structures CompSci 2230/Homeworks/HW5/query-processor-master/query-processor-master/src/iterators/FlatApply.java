package iterators;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// Iterator that may produce 0 or more output elements for every input element
public class FlatApply<InT, OutT> implements Iterator<OutT> {

    private final FlatApplyFunction<InT, OutT> f;
    private final Iterator<InT> input;
    public LinkedList<OutT> queue;

    public FlatApply(FlatApplyFunction<InT, OutT> f, Iterator<InT> input) {
        this.f = f;
        this.input = input;
        queue = new LinkedList<OutT>();
    }

    @Override
    public boolean hasNext() {
        if (!queue.isEmpty()) {
            return true;
        } else {
            while (queue.isEmpty() && input.hasNext()) {
                List<OutT> temp = f.apply(input.next());
                for (OutT e : temp) {
                    queue.add(e);
                }

            }
        }
        return !queue.isEmpty();
    }

    @Override
    public OutT next() {
        if (!hasNext()) {
            throw new IllegalStateException();
        }
        return queue.remove(0);
    }

    // feel free to create private methods if helpful
}
