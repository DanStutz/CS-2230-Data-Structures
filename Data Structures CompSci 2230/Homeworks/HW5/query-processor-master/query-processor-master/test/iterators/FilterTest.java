package iterators;


import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class FilterTest {
		
		public FilterTest() {
		}

		@Test
		public void emptyTest() {
			Integer[] inputValues = {};
			List<Integer> input = Arrays.asList(inputValues);
			Filter<Integer> op = new Filter<>(new IsNotZero(), input.iterator());

			assertEquals(false, op.hasNext());
			assertEquals(false, op.hasNext());
		}

		@Test
		public void oneRemovedTest() {
			Integer[] inputValues = {0};
			List<Integer> input = Arrays.asList(inputValues);
			Filter<Integer> op = new Filter<>(new IsNotZero(), input.iterator());

			assertEquals(false, op.hasNext());
			assertEquals(false, op.hasNext());
		}
		
		@Test
		public void oneKeptTest() {
			Integer[] inputValues = {200};
			List<Integer> input = Arrays.asList(inputValues);
			Filter<Integer> op = new Filter<>(new IsNotZero(), input.iterator());

			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals(200, (int)op.next());
			assertEquals(false, op.hasNext());
			assertEquals(false, op.hasNext());
		}
		
		@Test
		public void moreTest() {
			Integer[] inputValues = {0,50,1,0,400,0,0};
			List<Integer> input = Arrays.asList(inputValues);
			Filter<Integer> op = new Filter<>(new IsNotZero(), input.iterator());

			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals(50, (int)op.next());
			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals(1, (int)op.next());
			assertEquals(400, (int)op.next());
			assertEquals(false, op.hasNext());
		}

		public static class IsNotZero implements Predicate<Integer> {
                /* Return true if the input isn't 0 */
				@Override
				public boolean check(Integer data) {
						return data!=0;
				}
				
		}
		
}
