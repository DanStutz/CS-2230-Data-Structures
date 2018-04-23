package iterators;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class ApplyTest {
		
		public ApplyTest() {
		}

		@Test
		public void emptyTest() {
			Integer[] inputValues = {};
			List<Integer> input = Arrays.asList(inputValues);
			Apply<Integer,Integer> op = new Apply<>(new TimesTwo(), input.iterator());

			assertEquals(false, op.hasNext());
			assertEquals(false, op.hasNext());
		}
		
		@Test
		public void oneTest() {
			Integer[] inputValues = {100};
			List<Integer> input = Arrays.asList(inputValues);
			Apply<Integer,Integer> op = new Apply<>(new TimesTwo(), input.iterator());

			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals(200, (int)op.next());
			assertEquals(false, op.hasNext());
			assertEquals(false, op.hasNext());
		}
		
		@Test
		public void moreTest() {
			Integer[] inputValues = {10,50,1,400};
			List<Integer> input = Arrays.asList(inputValues);
			Apply<Integer,Integer> op = new Apply<>(new TimesTwo(), input.iterator());

			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals(20, (int)op.next());
			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals(100, (int)op.next());
			assertEquals(2, (int)op.next());
			assertEquals(800, (int)op.next());
			assertEquals(false, op.hasNext());
		}
		
		@Test
		public void stringIntegerTest() {
			String[] inputValues = {"", "abcd", "ef", "ghijkl"};
			List<String> input = Arrays.asList(inputValues);
			Apply<String,Integer> op = new Apply<>(new NumChars(), input.iterator());

			assertEquals(true, op.hasNext());
			assertEquals(0, (int)op.next());
			assertEquals(true, op.hasNext());
			assertEquals(4, (int)op.next());
			assertEquals(true, op.hasNext());
			assertEquals(2, (int)op.next());
			assertEquals(true, op.hasNext());
			assertEquals(6, (int)op.next());
			assertEquals(false, op.hasNext());
		}

		@Test
		public void integerStringTest() {
			Integer[] inputValues = {0, 0, 5, 1, 4};
			List<Integer> input = Arrays.asList(inputValues);
			Apply<Integer,String> op = new Apply<>(new RepeatChar(), input.iterator());

			assertEquals(true, op.hasNext());
			assertEquals("", op.next());
			assertEquals(true, op.hasNext());
			assertEquals("", op.next());
			assertEquals(true, op.hasNext());
			assertEquals("AAAAA", op.next());
			assertEquals(true, op.hasNext());
			assertEquals("A", op.next());
			assertEquals(true, op.hasNext());
			assertEquals("AAAA", op.next());
			assertEquals(false, op.hasNext());
		}

		@Test
		public void chainTest() {
			Integer[] inputValues = {0, 0, 5, 1, 4};
			List<Integer> input = Arrays.asList(inputValues);
			Apply<Integer,Integer> op = new Apply<>(new TimesTwo(), input.iterator());
			Apply<Integer,String> op2 = new Apply<>(new RepeatChar(), op);

			assertEquals(true, op2.hasNext());
			assertEquals("", op2.next());
			assertEquals(true, op2.hasNext());
			assertEquals("", op2.next());
			assertEquals(true, op2.hasNext());
			assertEquals("AAAAAAAAAA", op2.next());
			assertEquals(true, op2.hasNext());
			assertEquals("AA", op2.next());
			assertEquals(true, op2.hasNext());
			assertEquals("AAAAAAAA", op2.next());
			assertEquals(false, op2.hasNext());
		}


		private class TimesTwo implements ApplyFunction<Integer, Integer> {
                /* Multiply input by 2 */
				@Override
				public Integer apply(Integer x) {
					return x*2;
				}
		}

		private class NumChars implements ApplyFunction<String, Integer> {
                /* Return the number of characters in the string */
				@Override
				public Integer apply(String x) {
					return x.length();
				}
		}
		
		private class RepeatChar implements ApplyFunction<Integer, String> {
                /* Return a string that is the given number of As
                    for example apply(2) returns "AA"
                 */
				@Override
				public String apply(Integer x) {
					String s = "";
					for (int i=0; i<x; i++) {
						s += "A";
					}
					return s;
				}
		}
}
