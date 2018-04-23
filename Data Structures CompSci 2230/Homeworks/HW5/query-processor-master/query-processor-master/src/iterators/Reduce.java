package iterators;

import java.util.Iterator;

// Iterator that returns a single element that is the result of
// combining all the input elements
public class Reduce<InT, OutT> implements Iterator<OutT> {
    
    OutT soFar = null;

    public Reduce(ReduceFunction<InT, OutT> f, Iterator<InT> input) {
        soFar = f.initialValue();
        
        while (input.hasNext()){
            soFar = f.combine(soFar, input.next());
        }
        
    }

    @Override
    public boolean hasNext() {
        return (soFar != null);
    }

    @Override
    public OutT next() {
        OutT temp = soFar;
        soFar = null;
        return temp;
    }
}
