package iterators;

import java.util.Iterator;

public class IntApply implements Iterator {
    // The function that will be applied to each input element to make an output element

    private final IntApplyFunction f;

    // The Iterator that this Apply object will get its input from
    private final Iterator<Integer> input;

    public IntApply(IntApplyFunction f, Iterator<Integer> input) {
        this.input = input;
        this.f = f;
    }

    @Override
    public boolean hasNext() {
        return input.hasNext();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new IllegalStateException();
        }
        Integer result = f.apply(input.next());
        return result;
    }
}
