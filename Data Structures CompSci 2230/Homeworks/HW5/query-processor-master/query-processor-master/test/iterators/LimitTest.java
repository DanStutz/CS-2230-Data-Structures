package iterators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class LimitTest {
		
		public LimitTest() {
		}

		@Test
		public void emptyTest() {
			Integer[] inputValues = {};
			List<Integer> input = Arrays.asList(inputValues);
			Iterator<Integer> op = new Limit<>(2, input.iterator());

			assertEquals(false, op.hasNext());
			assertEquals(false, op.hasNext());
		}
		
		@Test
		public void oneTest() {
			Integer[] inputValues = {100};
			List<Integer> input = Arrays.asList(inputValues);
			Iterator<Integer> op = new Limit<>(2, input.iterator());

			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals(100, (int)op.next());
			assertEquals(false, op.hasNext());
			assertEquals(false, op.hasNext());
		}
		
		@Test
		public void moreTest() {
			Integer[] inputValues = {10,50,1,400};
			List<Integer> input = Arrays.asList(inputValues);
			Iterator<Integer> op = new Limit<>(2, input.iterator());

			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals(10, (int)op.next());
			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals(50, (int)op.next());
			assertEquals(false, op.hasNext());
			assertEquals(false, op.hasNext());
		}
}
