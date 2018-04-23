package queries;

import iterators.Apply;
import iterators.ApplyFunction;
import iterators.Limit;
import java.util.Iterator;
import readers.TextFileReader;

// return the contents of the first file
public class TextQuery1a {

    public static void main(String[] args) {
        Iterator<Pair<String, String>> filenameAndContents = new TextFileReader("../sci.space");
        Iterator<String> contents = new Apply(new TakeRight<>(), filenameAndContents);
        Iterator<String> firstFileContents = new Limit(1, contents);

        while (firstFileContents.hasNext()) {
            System.out.println(firstFileContents.next());
        }
    }

    private static class TakeRight<L, R> implements ApplyFunction<Pair<L, R>, R> {

        @Override
        public R apply(Pair<L, R> x) {
            return x.right;
        }
    }
}
