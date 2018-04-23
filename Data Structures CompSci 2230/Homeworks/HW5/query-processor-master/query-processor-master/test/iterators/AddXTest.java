
package iterators;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class AddXTest {
    
    public AddXTest() {
    }
    
		@Test
		public void emptyTest() {
			Integer[] inputValues = {};
			List<Integer> input = Arrays.asList(inputValues);
                        AddX op = new AddX(1, input.iterator());
			assertEquals(false, op.hasNext());
			assertEquals(false, op.hasNext());
		}
		
		@Test
		public void oneTest() {
			Integer[] inputValues = {100};
			List<Integer> input = Arrays.asList(inputValues);
                         AddX op = new AddX(10, input.iterator());
			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals(110, (int)op.next());
			assertEquals(false, op.hasNext());
			assertEquals(false, op.hasNext());
		}
		
		@Test
		public void moreTest() {
			Integer[] inputValues = {10,50,2,400};
			List<Integer> input = Arrays.asList(inputValues);
                        AddX op = new AddX(10, input.iterator());

			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals(20, (int)op.next());
			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals(true, op.hasNext());
			assertEquals(60, (int)op.next());
			assertEquals(12, (int)op.next());
			assertEquals(410, (int)op.next());
			assertEquals(false, op.hasNext());
		}
		
		


		
    
}
