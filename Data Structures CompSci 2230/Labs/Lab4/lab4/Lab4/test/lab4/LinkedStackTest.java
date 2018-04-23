package lab4;
import org.junit.Test;
import static org.junit.Assert.*;

public class LinkedStackTest {
		
		@Test
		public void testIsEmpty() {
			Stack s = new LinkedStack();
			assertTrue(s.isEmpty());
		}
		
		@Test
		public void testNotEmpty() {
			Stack s = new LinkedStack();
			s.push(10);
			assertFalse(s.isEmpty());
		}

		@Test
		public void testPushPop() {
			Stack s = new LinkedStack();
			s.push(10);
			assertEquals(10, s.pop());
			assertTrue(s.isEmpty());
		}

		@Test
		public void testEmptyTwice() {
			Stack s = new LinkedStack();
			s.push(10);
			assertEquals(10, s.pop());
			assertTrue(s.isEmpty());
			s.push(40);
			assertEquals(40, s.pop());
			assertTrue(s.isEmpty());
		}

		@Test
		public void testPushPopMore() {
			Stack s = new LinkedStack();
			s.push(10);
			s.push(20);
			s.push(30);
			assertEquals(30, s.pop());
			assertEquals(20, s.pop());
			assertEquals(10, s.pop());
			assertTrue(s.isEmpty());
		}
		@Test
		public void testPushPopMixed() {
			Stack s = new LinkedStack();
			s.push(10);
			s.push(20);
			assertEquals(20, s.pop());
			s.push(30);
			s.push(40);
			s.push(50);
			assertEquals(50, s.pop());
			assertEquals(40, s.pop());
			assertEquals(30, s.pop());
			assertTrue(!s.isEmpty());
		}
}
