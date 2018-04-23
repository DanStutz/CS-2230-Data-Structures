package binaryTree;

import iterators.Predicate;
import iterators.ReduceFunction;
import java.util.*;

public class BinaryTree<T> {

    // the root of the tree
    private TreeNode<T> root;

    // the queue of TreeNodes in the tree that have 0 or 1 children
    private final List<TreeNode<T>> nodesThatNeedChildren;

    // number of TreeNodes in the tree
    public int size;

    public BinaryTree() {
        root = null;
        nodesThatNeedChildren = new LinkedList<>();
        size = 0;
    }

    /*
	Insert the element d into the BinaryTree at the
	"next available location" starting with the left
     */
    public void insertNode(T d) {
        //PART 1 HERE
        TreeNode<T> newNode = new TreeNode(d);
        nodesThatNeedChildren.add(newNode);
        size++;

        if (root == null) {
            root = newNode;
            return;
        }

        TreeNode current = (TreeNode) nodesThatNeedChildren.get(0);

        if (current.left == null) {
            current.left = newNode;
            return;
        }
        if (current.right == null) {
            current.right = newNode;
            nodesThatNeedChildren.remove(0);
        }
    }

    /* Takes a depth n and a ReduceFunction f
    and returns the "combined value" of all elements in the tree at depth n,
    where depth=0 is the root.

    "combined" means the result of starting with f.initialValue
    and "concatentation or combination" each element using f.combine

    Requirement: must use a loop

	Note on Java syntax: The <OutT> in front of the method introduces a generic
	type name OutT for use only by this method. This is helpful when the generic
	type doesn't hold much meaning for the whole class but is meaningful for a
	single method.
     */
    public <OutT> OutT reduceAtDepth(int depth, ReduceFunction<T, OutT> f) {
        //PART 2 HERE
        Queue<TreeNode<T>> queue = new LinkedList<>();
        TreeNode<T> node = root;
        int x = 0;
        queue.add(node);

        while (x != depth) {
            int nodesAtLevel = queue.size();

            while (nodesAtLevel > 0) {
                node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                nodesAtLevel--;
            }

            x++;
        }

        OutT sum = f.initialValue();
        for (TreeNode<T> currNode : queue) {
            sum = f.combine(sum, currNode.data);
        }
        return sum;
    }

    /*Takes a depth n and a ReduceFunction f
    and returns the "combined value" of all elements in the tree at depth n,
    where depth=0 is the root.

    "combined" means the result of starting with f.initialValue
    and "concatentation or combination" each element using f.combine

    NOTE that the "in" generic type and "out" generic type are the same type.
    This makes the recurision a easier.
    Requirement: must use a recursive.
     */
    public T reduceAtDepthRecursive(int n, ReduceFunction<T, T> f) {
        return reduceAtDepthHelper(n, root, f, f.initialValue());
    }

    private T reduceAtDepthHelper(int depth, TreeNode<T> node, ReduceFunction<T, T> f, T sum) {
        //PART 2 HERE
        if (node == null) {
            return sum;
        } else if (depth == 0) {
            return f.combine(sum, node.data);
        } else {
            return f.combine(reduceAtDepthHelper(depth - 1, node.left, f, sum), reduceAtDepthHelper(depth - 1, node.right, f, sum));
        }
    }

    /* Takes a Predicate p and returns the list of all elements
    for which p.check is true. The elements must be returned in
    "in order" traversal order.

    Requirement: must use a loop
     */
    public List<T> selectIterative(Predicate<T> p) {
        if (root == null) {
            return null;
        }
        LinkedList<TreeNode<T>> stack = new LinkedList<>();
        stack.add(root);
        List<T> temp = new LinkedList<>();
        TreeNode<T> node = root;
        
        while(!stack.isEmpty()){
            node = stack.pop();
            if(p.check(node.data)){
                temp.add(node.data);
            }
            if (node.right != null){
                stack.addFirst(node.right);
            }
            if (node.left != null){
                stack.addFirst(node.left);
            }
        }
        return temp;
    }

    /* Takes a Predicate p and returns the list of all elements
    for which p.check is true. The elements must be returned in
    "in order" traversal order.

    Requirement: must be recursive
     */
 /*
    Requirement: must be recursive
     */
    public List<T> selectRecursive(Predicate<T> p) {
        return selectRecursiveHelper(p, root);
    }

    private List<T> selectRecursiveHelper(Predicate<T> p, TreeNode<T> node) {
        if (node == null) {
            return null;
        }

        List<T> res = new LinkedList();
        if (p.check(node.data)) {
            res.add(node.data);
        }
        if (node.left != null) {
            List<T> wantedNodes = (selectRecursiveHelper(p, node.left));
            for (int i = 0; i < wantedNodes.size(); i++) {
                res.add(wantedNodes.get(i));
            }
        }
        if (node.right != null) {

            List<T> wantedNodes = (selectRecursiveHelper(p, node.right));
            for (int i = 0; i < wantedNodes.size(); i++) {
                res.add(wantedNodes.get(i));
            }
        }
        return res;
    }

    //////////////// Dont edit after here //////////////////////
    public void printTree() {
        Object[] nodeArray = this.toArray();
        for (int i = 0; i < nodeArray.length; i++) {
            System.out.println(nodeArray[i]);
        }
    }

    public void displayTree() {
        Stack globalStack = new Stack<TreeNode>();
        globalStack.push(root);
        int emptyLeaf = 32;
        boolean isRowEmpty = false;
        System.out.println("****..................................................................................................................................****");
        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < emptyLeaf; j++) {
                System.out.print("  ");
            }

            while (globalStack.isEmpty() == false) {
                TreeNode temp = (TreeNode) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.data);
                    localStack.push(temp.left);
                    localStack.push(temp.right);
                    if (temp.left != null || temp.right != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }

                for (int j = 0; j < emptyLeaf * 2 - 2; j++) {
                    System.out.print("  ");
                }
            }
            System.out.println();
            emptyLeaf /= 2;
            while (localStack.isEmpty() == false) {
                globalStack.push(localStack.pop());
            }

        }

        System.out.println("****..................................................................................................................................****");
    }

    public Object[] toArray() {
        Object[] r = new Object[size];
        if (root == null) {
            return r;
        }

        // traverse the tree to visit all nodes,
        // adding them to r
        List<TreeNode> frontier = new LinkedList<>();
        frontier.add(root);
        int soFar = 0;

        while (frontier.size() > 0) {
            TreeNode v = (TreeNode) frontier.get(0);
            r[soFar] = v.data;
            soFar++;

            if (v.left != null) {
                frontier.add(v.left);
            }
            if (v.right != null) {
                frontier.add(v.right);
            }

            frontier.remove(0);
        }
        return r;
    }

    private static class TreeNode<T> {

        public T data;
        public TreeNode<T> left;
        public TreeNode<T> right;

        public TreeNode(T d) {
            data = d;
            left = null;
            right = null;
        }
    }
}
