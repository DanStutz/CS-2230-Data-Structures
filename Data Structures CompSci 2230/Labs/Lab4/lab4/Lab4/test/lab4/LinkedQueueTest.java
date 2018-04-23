package lab4;

import org.junit.Test;
import static org.junit.Assert.*;

public class LinkedQueueTest {
		@Test
		public void testIsEmpty() {
			Queue s = new LinkedQueue();
			assertTrue(s.isEmpty());
		}
		
		@Test
		public void testNotEmpty() {
			Queue s = new LinkedQueue();
			s.add(10);
			assertFalse(s.isEmpty());
		}

		@Test
		public void testAddPop() {
			Queue s = new LinkedQueue();
			s.add(10);
			assertEquals(10, s.pop());
			assertTrue(s.isEmpty());
		}
		
		@Test
		public void testEmptyTwice() {
			Queue s = new LinkedQueue();
			s.add(10);
			assertEquals(10, s.pop());
			assertTrue(s.isEmpty());
			s.add(40);
			assertEquals(40, s.pop());
			assertTrue(s.isEmpty());
		}

		@Test
		public void testAddPopMore() {
			Queue s = new LinkedQueue();
			s.add(10);
			s.add(20);
			s.add(30);
			assertEquals(10, s.pop());
			assertEquals(20, s.pop());
			assertEquals(30, s.pop());
			assertTrue(s.isEmpty());
		}
		@Test
		public void testAddPopMixed() {
			Queue s = new LinkedQueue();
			s.add(10);
			s.add(20);
			assertEquals(10, s.pop());
			s.add(30);
			s.add(40);
			s.add(50);
			assertEquals(20, s.pop());
			assertEquals(30, s.pop());
			assertEquals(40, s.pop());
			assertTrue(!s.isEmpty());
		}
}
