package queries;

import iterators.Apply;
import iterators.ApplyFunction;
import iterators.Filter;
import iterators.Predicate;
import java.util.Iterator;
import readers.TextFileReader;

// return all filenames that contain the word "Mars" 
public class TextQuery3 {

    public static void main(String[] args) {
        Iterator<Pair<String, String>> filenameAndContents = new TextFileReader("../sci.space");
        Iterator <Pair<String, String>> mars = new Filter(new ByWord("Mars"), filenameAndContents);
        Iterator<String> filenames = new Apply(new Search<>(), mars);

        /* finish the query using a combination of Applys and Filters */
        while (filenames.hasNext()
        
            ) {
			System.out.println(filenames.next()
        
    

);
		}}
        private static class Search<L, R> implements ApplyFunction<Pair<L, R>, L> {

        @Override
        public L apply(Pair<L, R> i) {
            return i.left;
        }
    }
        public static class ByWord implements Predicate<Pair<String, String>> {
        
        private final String ch;
        
        public ByWord(String c) {
            this.ch = c;
        }

        
        public boolean check(Pair<String,String> x) {
            return x.right.contains(ch);
	}	


	// put your classes that implement ApplyFunction and Predicate here

}
}
