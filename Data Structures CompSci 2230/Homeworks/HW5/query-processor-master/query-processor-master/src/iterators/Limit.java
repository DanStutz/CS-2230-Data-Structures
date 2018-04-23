package iterators;

import java.util.Iterator;

// Iterator that only returns the first K elements from
// its input
public class Limit<T> implements Iterator<T> {

    private final int K;
    private int soFar;
    private final Iterator<T> input;

    public Limit(int howMany, Iterator<T> input) {
        this.K = howMany;
        this.soFar = 0;
        this.input = input;
    }

    @Override
    public boolean hasNext() {
        return soFar < K && input.hasNext();
    }

    @Override
    public T next() {
        // next() should never be called if hasNext() would return false.
        // Here we purposely crash the program if this case comes up
        if (!hasNext()) {
            throw new IllegalStateException();
        }

        T result = input.next();
        soFar++;
        return result;
    }

}
