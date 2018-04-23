package hw3;

import org.junit.Test;
import static org.junit.Assert.*;

public class LinkedListTest {
		
		public LinkedListTest() {
		}

		@Test
		public void testRemove1() {
			List ll = new LinkedList();
			ll.add(100);
			assertEquals(100, ll.remove(0));
		}

		@Test
		public void testRemoveBigger() {
			List ll = new LinkedList();
			ll.add(100);
			ll.add(200);
			ll.add(300);
			ll.add(400);
			assertEquals(300, ll.remove(2));
			assertEquals(200, ll.remove(1));
			assertEquals(400, ll.remove(1));
		}
		
		@Test
		public void testRemoveAndGet() {
			List ll = new LinkedList();
			ll.add(100);
			ll.add(200);
			ll.add(300);
			ll.add(400);
			assertEquals(300, ll.remove(2));
			assertEquals(200, ll.get(1));
			assertEquals(400, ll.get(2));
		}
		
		@Test
		public void testRemoveGetAppend() {
			List ll = new LinkedList();
			ll.add(100);
			ll.add(200);
			ll.add(300);
			ll.add(400);
			assertEquals(300, ll.remove(2));
			assertEquals(400, ll.get(2));
			ll.add(500);
			ll.add(600);
			assertEquals(400, ll.remove(2));
			assertEquals(500, ll.get(2));
			assertEquals(600, ll.remove(3));
		}

		/**
		 * Test of get method, of class LinkedList.
		 */
		@Test
		public void testGet1() {
			List ll = new LinkedList();
			ll.add(100);
			assertEquals(100, ll.get(0));
		}
		
		/**
		 * Test of get method, of class LinkedList.
		 */
		@Test
		public void testGetBigger() {
			List ll = new LinkedList();
			ll.add(100);
			ll.add(200);
			ll.add(300);
			ll.add(400);
			assertEquals(100, ll.get(0));
			assertEquals(200, ll.get(1));
			assertEquals(300, ll.get(2));
			assertEquals(400, ll.get(3));
		}

				@Test
				public void testReplace1() {
                    List ll = new LinkedList();
                    ll.add(100);
					assertEquals(100, ll.replace(0, 500));
					assertEquals(500, ll.get(0));
				}
                /**
                 * Test of replace method, of class LinkedList.
                 */
                @Test
                public void testReplace(){
                    List ll = new LinkedList();
                    ll.add(100);
                    ll.add(200);
                    assertEquals(200, ll.get(1));
                    assertEquals(200,ll.replace(1, 300));
                    assertEquals(300, ll.get(1));
                    ll.remove(0);
                    assertEquals(300, ll.replace(0, 400));
                    ll.add(100);
                    assertEquals(400, ll.get(0));
                    assertEquals(100, ll.get(1));  
                }
				@Test
				public void testReplaceMore() {
                    List ll = new LinkedList();
                    ll.add(100);
                    ll.add(200);
                    ll.add(300);
                    ll.add(400);
                    ll.add(500);
                    ll.add(600);
                    assertEquals(600, ll.replace(5, 66));
					assertEquals(400, ll.replace(3, 44));
					assertEquals(100, ll.replace(0, 11));
					assertEquals(11, ll.get(0));
					assertEquals(200, ll.get(1));
					assertEquals(300, ll.get(2));
					assertEquals(44, ll.get(3));
					assertEquals(500, ll.get(4));
					assertEquals(66, ll.get(5));
				}

				@Test
				public void testReplaceSize() {
                    List ll = new LinkedList();
                    ll.add(100);
					ll.replace(0, 11);
					assertEquals(1, ll.size());
                    ll.add(400);
                    ll.add(600);
					assertEquals(3, ll.size());
					ll.replace(2, 66);
					assertEquals(3, ll.size());
				}
                
                /**
                 * Test of size method, of class LinkedList.
                 */
                @Test
                public void testSizeRemove(){
                    List ll = new LinkedList();
                    assertEquals(0, ll.size());
                    ll.add(100);
                    assertEquals(1, ll.size());
                    ll.add(200);
                    ll.add(300);
                    assertEquals(3, ll.size());
                    ll.remove(2);
                    ll.add(400);
                    ll.remove(2);
                    assertEquals(2, ll.size());
                    ll.remove(0);
                    assertEquals(1, ll.size());
                    ll.remove(0);
                    assertEquals(0, ll.size());
                }
                
		/**
		 * Test of move method, of class LinkedList.
		 */
		@Test
		public void testMove2Backward() {
			List ll = new LinkedList();
			ll.add(100);
			ll.add(200);
			ll.move(0, 1);
			assertEquals(200, ll.get(0));
			assertEquals(100, ll.get(1));
		}
		
		@Test
		public void testMove2Forward() {
			List ll = new LinkedList();
			ll.add(100);
			ll.add(200);
			ll.move(1, 0);
			assertEquals(200, ll.get(0));
			assertEquals(100, ll.get(1));
		}
		@Test
		public void testMoveSize1() {
			List ll = new LinkedList();
			ll.add(100);
			ll.add(200);
			assertEquals(2, ll.size());
			ll.move(0, 1);
			assertEquals(2, ll.size());
		}
		
		@Test
		public void testMoveSize2() {
			List ll = new LinkedList();
			ll.add(100);
			ll.add(200);
			assertEquals(2, ll.size());
			ll.move(1, 0);
			assertEquals(2, ll.size());
		}

		@Test
		public void testMoveMoreForward() {
			List ll = new LinkedList();
			ll.add(100);
			ll.add(200);
			ll.add(300);
			ll.add(400);
			ll.add(500);
			ll.move(4, 0);
			assertEquals(500, ll.get(0));
			assertEquals(100, ll.get(1));
			assertEquals(200, ll.get(2));
			assertEquals(300, ll.get(3));
			assertEquals(400, ll.get(4));
			ll.move(3, 1);
			assertEquals(500, ll.get(0));
			assertEquals(300, ll.get(1));
			assertEquals(100, ll.get(2));
			assertEquals(200, ll.get(3));
			assertEquals(400, ll.get(4));
		}
		
		@Test
		public void testMoveMoreBackward() {
			List ll = new LinkedList();
			ll.add(100);
			ll.add(200);
			ll.add(300);
			ll.add(400);
			ll.add(500);
			ll.move(0, 4);
			assertEquals(200, ll.get(0));
			assertEquals(300, ll.get(1));
			assertEquals(400, ll.get(2));
			assertEquals(500, ll.get(3));
			assertEquals(100, ll.get(4));
			ll.move(1, 3);
			assertEquals(200, ll.get(0));
			assertEquals(400, ll.get(1));
			assertEquals(500, ll.get(2));
			assertEquals(300, ll.get(3));
			assertEquals(100, ll.get(4));
		}
		
		@Test
		public void testMoveBackAndForth() {
			List ll = new LinkedList();
			ll.add(100);
			ll.add(200);
			ll.add(300);
			ll.add(400);
			ll.add(500);
			ll.move(1, 3);
			ll.move(4, 3);
			ll.move(0, 2);
			assertEquals(300, ll.get(0));
			assertEquals(400, ll.get(1));
			assertEquals(100, ll.get(2));
			assertEquals(500, ll.get(3));
			assertEquals(200, ll.get(4));
		}
		
		@Test
		public void testAll() {
			List ll = new LinkedList();
			ll.add(100);
			ll.add(200);
			ll.add(300);
			ll.add(400);
			ll.add(500);
			ll.move(1, 3);
			assertEquals(200, ll.remove(3));
			ll.add(600);
			ll.move(4, 1);
			assertEquals(100, ll.remove(0));
			assertEquals(400, ll.remove(2));
			ll.move(2, 0);
			assertEquals(600, ll.replace(1, 66));
			assertEquals(500, ll.get(0));
			assertEquals(66, ll.get(1));
			assertEquals(300, ll.get(2));
		}

		@Test
		public void testToArrayEmpty() {
			List ll = new LinkedList();
			Object[] actual = ll.toArray();
			assertArrayEquals(new Object[0], actual);
		}
		
		@Test
		public void testToArray1() {
			List ll = new LinkedList();
			ll.add(100);
			Object[] actual = ll.toArray();
			Object[] expected = { 100 };
			assertArrayEquals(expected, actual);
		}
		
		@Test
		public void testToArrayMore() {
			List ll = new LinkedList();
			ll.add(100);
			ll.add(200);
			ll.add(300);
			ll.add(400);
			ll.add(500);
			Object[] actual = ll.toArray();
			Object[] expected = { 100, 200, 300, 400, 500 };
			assertArrayEquals(expected, actual);
		}
		@Test
		public void testSizeEmpty() {
			List ll = new LinkedList();
			assertEquals(0, ll.size());
			assertEquals(0, ll.size());
		}

		@Test
		public void testAddSize() {
			List ll = new LinkedList();
			assertEquals(0, ll.size());
			ll.add(100);
			ll.add(200);
			assertEquals(2, ll.size());
			assertEquals(2, ll.size()); 
			ll.add(300);
			ll.add(400);
			ll.add(500);
			assertEquals(5, ll.size());
		}
		
		@Test
		public void testAllAndToArray() {
			List ll = new LinkedList();
			ll.add(100);
			ll.add(200);
			ll.add(300);
			ll.add(400);
			ll.add(500);
			ll.move(1, 3);
			ll.remove(3);
			ll.add(600);
			ll.move(4, 1);
			ll.remove(0);
			ll.remove(2);
			ll.move(2, 0);
			Object[] actual = ll.toArray();
			Object[] expected = { 500, 600, 300 };
			assertArrayEquals(actual, expected);
		}
}
