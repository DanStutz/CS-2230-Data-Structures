package iterators;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class FlatApplyTest {
		
		public FlatApplyTest() {
		}

		@Test
		public void emptyTest() {
			Integer[] inputValues = {};
			List<Integer> input = Arrays.asList(inputValues);
			FlatApply<Integer,Integer> op = new FlatApply<>(new TwoCopies(), input.iterator());

			assertEquals(false, op.hasNext());
			assertEquals(false, op.hasNext());
			assertEquals(false, op.hasNext());
		}

		@Test
		public void oneTest() {
			Integer[] inputValues = {100};
			List<Integer> input = Arrays.asList(inputValues);
			FlatApply<Integer,Integer> op = new FlatApply<>(new TwoCopies(), input.iterator());

			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals(100, (int)op.next());
			assertEquals(true, op.hasNext());
			assertEquals(100, (int)op.next());
			assertEquals(false, op.hasNext());
			assertEquals(false, op.hasNext());
		}
		
		@Test
		public void moreTest() {
			Integer[] inputValues = {10,50,1,400};
			List<Integer> input = Arrays.asList(inputValues);
			FlatApply<Integer,Integer> op = new FlatApply<>(new TwoCopies(), input.iterator());

			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals(10, (int)op.next());
			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals(10, (int)op.next());
			assertEquals(50, (int)op.next());
			assertEquals(50, (int)op.next());
			assertEquals(true, op.hasNext());
			assertEquals(1, (int)op.next());
			assertEquals(true, op.hasNext());
			assertEquals(1, (int)op.next());
			assertEquals(true, op.hasNext());
			assertEquals(400, (int)op.next());
			assertEquals(true, op.hasNext());
			assertEquals(400, (int)op.next());
			assertEquals(false, op.hasNext());
		}

		@Test
		public void removalTest() {
			Integer[] inputValues = {100,-200,0,0,300,0,400,0};
			List<Integer> input = Arrays.asList(inputValues);
			FlatApply<Integer,String> op = new FlatApply<>(new RemoveAndConvert(), input.iterator());

			assertEquals(true, op.hasNext());
			assertEquals("100", op.next());
			assertEquals(true, op.hasNext());
			assertEquals("-200", op.next());
			assertEquals(true, op.hasNext());
			assertEquals("300", op.next());
			assertEquals(true, op.hasNext());
			assertEquals("400", op.next());
			assertEquals(false, op.hasNext());
		}

		// Returns two copies of the input
		public static class TwoCopies implements FlatApplyFunction<Integer, Integer> {

				@Override
				public List<Integer> apply(Integer x) {
					List<Integer> l = new LinkedList<>();
					l.add(x);
					l.add(x);
					return l;
				}
		}

		public static class RemoveAndConvert implements FlatApplyFunction<Integer, String> {
		    /* Returns the integer as a string if it isn't 0
            */
				@Override
				public List<String> apply(Integer x) {
					List<String> l = new LinkedList<>();
					if (x != 0) {
						l.add(x.toString());
					}
					return l;
				}
		}
}
