
import iterators.Predicate;
import binaryTree.BinaryTree;
import iterators.ReduceFunction;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class BinaryTreeTest {

		public BinaryTreeTest() {
		}

		@Test
		public void testInsertionAndtoArray() {
				BinaryTree<Integer> bt = new BinaryTree<>();
				bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
				Object[] actual = bt.toArray();
				Object[] expected = {50, 2, 34, 19, 6, 22};
				assertArrayEquals(expected, actual);
		}

		@Test
		public void reduceAtDepthTest() {
				BinaryTree<Integer> bt = new BinaryTree<>();
				bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
				int sum = bt.reduceAtDepth(2, new IntegerSum());
				assertEquals(47, sum);
		}
		
		@Test
		public void reduceAtDepthTest2() {
				BinaryTree<Integer> bt = new BinaryTree<>();
				bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
				int sum = bt.reduceAtDepthRecursive(2, new IntegerSum());
				assertEquals(47, sum);
		}

		@Test
		public void selectIterativeTest() {
				BinaryTree<Integer> bt = new BinaryTree<>();
				bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
				Integer[] expected = {50,2,6,34,22};
				List<Integer> answers = bt.selectIterative(new IsEven());
				assertArrayEquals(expected, answers.toArray());
		}

		// Example implementation of ReduceFunction used by the given test
		private static class IntegerSum implements ReduceFunction<Integer, Integer> {
				@Override
				public Integer combine(Integer soFar, Integer x) {
						return soFar+x;
				}

				@Override
				public Integer initialValue() {
						return 0;
				}
		}

		// Example implementation of IsEven used by the given test
		private static class IsEven implements Predicate<Integer> {
				@Override
				public boolean check(Integer data) {
						return data % 2 == 0;
				}
		}

		/* The staff will run your code on several additional JUnit tests of our own.
		   You must write additional tests below to provide more evidence that your
		   method implementations are correct. 
		
		   This test code is part of the assignment, just like the other code.

		   If you write a new test and it fails, GREAT! That means you are making
		   progress. Either the test is wrong and you just need to fix it, or it means you found
		   a bug in your BinaryTree code and now you can go fix it. Don't remove good tests just
		   because they fail.
		 */

        // write your new tests below here, using the @Test methods above as an example.
		// PART 2: testing

		// PART 4: testing
}
