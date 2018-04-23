package queries;

import iterators.Apply;
import iterators.ApplyFunction;
import iterators.Filter;
import iterators.FlatApply;
import iterators.FlatApplyFunction;
import iterators.Reduce;
import iterators.Predicate;
import iterators.ReduceFunction;
import readers.TextFileReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// Return the number total occurences of the word "Mars"
public class TextQuery4 {

    public static void main(String[] args) {
        Iterator<Pair<String, String>> filenameAndContents = new TextFileReader("../sci.space");
        Iterator<String> contents = new Apply(new TakeRight<>(), filenameAndContents);
        Iterator<String> words = new FlatApply(new SplitBy(" "), contents);
        Iterator<Integer> occurences = new Reduce(new CountWord("Mars"), words);

        /* finish the query */
        while (occurences.hasNext()
        
            ) {
			System.out.println(occurences.next()
    );
		}
	}	

    /* Define the additional classes you need here */ 


	
	public static class TakeRight<L, R> implements ApplyFunction<Pair<L, R>, R> {

        @Override
        public R apply(Pair<L, R> x) {
            return x.right;
        }
    }

    public static class SplitBy implements FlatApplyFunction<String, String> {

        private final String ch;

        public SplitBy(String c) {
            this.ch = c;
        }

        @Override
        public List<String> apply(String x) {
            return Arrays.asList(x.split(ch));
        }
    }
    private static class CountWord implements ReduceFunction<String, Integer> {
        String input;
        Integer soFar;
        
        public CountWord(String ch){
            this.input = ch;
            this.soFar = initialValue();
            
        }
        
        @Override
        public final Integer initialValue(){return soFar = 0;}
        
        
        @Override
        public Integer combine (Integer soFar, String x){
            if(input.toLowerCase().equals(x.toLowerCase())){
                soFar ++;     
            }
            return soFar;
        }
    }
}
