/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package list;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bdmyers
 */
public class ListNodeTest {
		
		public ListNodeTest() {
		}

		@Test
		public void testGet() {
			ListNode l = new ListNode(100);
			l.add(200);
			assertEquals(100, l.get(0));
			assertEquals(200, l.get(1));
			l.add(300);
			assertEquals(300, l.get(2));
			assertEquals(200, l.get(1));
			assertEquals(100, l.get(0));
		}
		
		@Test
		public void testLength() {
			ListNode l = new ListNode(100);
			assertEquals(1, l.length());
			l.add(200);
			l.add(300);
			assertEquals(3, l.length());
		}

		@Test
		public void testContains() {
			ListNode l = new ListNode(100);
			assertFalse(l.contains(200));
			l.add(200);
			assertTrue(l.contains(200));
			l.add(300);
			l.add(400);
			assertTrue(l.contains(300));
			assertFalse(l.contains(999));
		}

		@Test
		public void removeLast() {
			ListNode l = new ListNode(100);
			l.add(200);
			l.add(300);
			l.add(400);
			l.removeLast();
			assertEquals(3, l.length());
			assertEquals(300, l.get(2));
			l.removeLast();
			assertEquals(2, l.length());
			assertEquals(200, l.get(1));
			l.removeLast();
			assertEquals(1, l.length());
			assertEquals(100, l.get(0));
		}

}
