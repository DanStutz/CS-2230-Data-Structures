package iterators;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReduceTest {
		
		public ReduceTest() {
		}

		@Test
		public void emptyTest() {
			Integer[] inputValues = {};
			List<Integer> input = Arrays.asList(inputValues);
			Reduce<Integer,String> op = new Reduce<>(new MaxAsString(), input.iterator());

			assertEquals(true, op.hasNext());
			assertEquals(new MaxAsString().initialValue(), op.next());
			assertEquals(false, op.hasNext());
			assertEquals(false, op.hasNext());
		}

		@Test
		public void oneTest() {
			Integer[] inputValues = {100};
			List<Integer> input = Arrays.asList(inputValues);
			Reduce<Integer,String> op = new Reduce<>(new MaxAsString(), input.iterator());

			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals("100", op.next());
			assertEquals(false, op.hasNext());
			assertEquals(false, op.hasNext());
		}
		
		@Test
		public void moreTest() {
			Integer[] inputValues = {100, 10, 200, 300, 200, 40, 30, Integer.MIN_VALUE};
			List<Integer> input = Arrays.asList(inputValues);
			Reduce<Integer,String> op = new Reduce<>(new MaxAsString(), input.iterator());

			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals("300", op.next());
			assertEquals(false, op.hasNext());
			assertEquals(false, op.hasNext());
		}

        // Used to find the max integer, but output is the integer as a String
		private static class MaxAsString implements ReduceFunction<Integer, String> {

				@Override
				public String combine(String soFar, Integer x) {
					if (Integer.parseInt(soFar) < x) {
						return x.toString();
					} else {
						return soFar;
					}
				}

				@Override
				public String initialValue() {
					return String.valueOf(Integer.MIN_VALUE);
				}

		}
}
