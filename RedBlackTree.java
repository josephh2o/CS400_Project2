import java.util.LinkedList;
import java.util.Stack;
import java.util.ArrayList;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm.
 */
public class RedBlackTree<T extends Comparable<T>> implements RedBlackTreeInterface<T> {

    /**
     * This class represents a node holding a single value within a binary tree.
     */
    protected static class Node<T> {
        public T data;
        public int blackHeight;
        // The context array stores the context of the node in the tree:
        // - context[0] is the parent reference of the node,
        // - context[1] is the left child reference of the node,
        // - context[2] is the right child reference of the node.
        // The @SupressWarning("unchecked") annotation is used to supress an unchecked
        // cast warning. Java only allows us to instantiate arrays without generic
        // type parameters, so we use this cast here to avoid future casts of the
        // node type's data field.
        @SuppressWarnings("unchecked")
        public Node<T>[] context = (Node<T>[])new Node[3];
        public Node(T data) { 
            this.data = data;
            this.blackHeight = 0;
        }
        
        /**
         * @return true when this node has a parent and is the right child of
         * that parent, otherwise return false
         */
        public boolean isRightChild() {
            return context[0] != null && context[0].context[2] == this;
        }

    }

    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After  
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when data is already contained in the tree
     */
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if (this.root == null) {
            // add first node to an empty tree
            root = newNode; size++;
            enforceRBTreePropertiesAfterInsert(newNode);
            return true;
        } else {
            // insert into subtree
            Node<T> current = this.root;
            while (true) {
                int compare = newNode.data.compareTo(current.data);
                if (compare == 0) {
                    throw new IllegalArgumentException("This RedBlackTree already contains value " + data.toString());
                } else if (compare < 0) {
                    // insert in left subtree
                    if (current.context[1] == null) {
                        // empty space to insert into
                        current.context[1] = newNode;
                        newNode.context[0] = current;
                        this.size++;
                        enforceRBTreePropertiesAfterInsert(newNode);
                        return true;
                    } else {
                        // no empty space, keep moving down the tree
                        current = current.context[1];
                    }
                } else {
                    // insert in right subtree
                    if (current.context[2] == null) {
                        // empty space to insert into
                        current.context[2] = newNode;
                        newNode.context[0] = current;
                        this.size++;
                        enforceRBTreePropertiesAfterInsert(newNode);
                        return true;
                    } else {
                        // no empty space, keep moving down the tree
                        current = current.context[2]; 
                    }
                }
            }
        }
    }

    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a left child of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * right child of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
        if(child.context[0] != parent)
            throw new IllegalArgumentException();
        if(child.isRightChild()) {// left rotation
            if(child.context[1] != null) {
                child.context[1].context[0] = parent;
            }
            parent.context[2] = child.context[1]; // change right child of original parent
            child.context[1] = parent; // set parent as left child
        } else {// right rotation
            if(child.context[2] != null) {
                child.context[2].context[0] = parent;
            }
            parent.context[1] = child.context[2];
            child.context[2] = parent; // set parent as right child
        }

        child.context[0] = parent.context[0]; // swap parent and child
        parent.context[0] = child; // set the childs parent to original parents parent
         
        // Parent and child swapped
        if(child.context[0] == null) // if parent was root node
            root = child;
        else { // Set child to the new child of the old parent's parent
            if(child.context[0].context[1] == parent) { // if parent was a left child
                child.context[0].context[1] = child;
            } else { // parent was a right child
                child.context[0].context[2] = child;
            }
        }
    }

    protected void enforceRBTreePropertiesAfterInsert(Node<T> node) {
        if(node == root || node.context[0].blackHeight == 1) {
            root.blackHeight = 1;
            return;
        }

        Node<T> parent = node.context[0];
        boolean sameSideParent = parent.isRightChild() == node.isRightChild();
        Node<T> sibling = parent.isRightChild() ? parent.context[0].context[1]
            : parent.context[0].context[2];

        if(sibling == null || sibling.blackHeight == 1) {
            if(!sameSideParent) {
                rotate(node, parent);
                node.blackHeight = 1;
                node.context[0].blackHeight = 0;
                rotate(node, node.context[0]);
            } else {
                parent.blackHeight = 1;
                parent.context[0].blackHeight = 0;
                rotate(parent, parent.context[0]);
            }
        } else {
            parent.blackHeight = 1;
            sibling.blackHeight = 1;
            parent.context[0].blackHeight = 0;
            enforceRBTreePropertiesAfterInsert(parent.context[0]);
        }
        root.blackHeight = 1;
    }

    /*
     * Returns the an ArrayList of data between a minimum and a maximum
     * @param min the minimum value of the range
     * @param max the maximum value of the range
     * @return The data in the nodes between the mimimum and the maximum
    */
	public ArrayList<T> subTreeData(T min, T max) throws NullPointerException {
        // Find the common parent of max and min
        Node<T> commonParent = findCommonParent(min, root, max);
        // return result of helper
        return rangeHelper(min, 
                commonParent,
                max);
    }

    /**
     * Helper for getRangeData that traverses the tree looking for a "middle" node
     * between the min and the max. Sort of like binary search.
     *
     * @param min node corresponding to the min of the range
     * @param curr current node that the algorithm has traversed to
     * @param max node corresponding to the max of the range
     */
    private Node<T> findCommonParent(T min, Node<T> curr, T max) {
        // current node too small
        if(curr.data.compareTo(min) < 0)
            return findCommonParent(min, curr.context[2], max);
        // current node too large
        if(curr.data.compareTo(max) > 0)
            return findCommonParent(min, curr.context[1], max);
        // current node just right
        return curr;
    }


    /**
     * Helper for getRangeData that traverses the tree to get the list of all the
     * nodes in the range between min and max
     * @param min node corresponding to the min of the range
     * @param curr current node that the algorithm has traversed to
     * @param max node corresponding to the max of the range
     */
    private ArrayList<T> rangeHelper(T min,
            Node<T> curr, T max) {
        ArrayList<T> data = new ArrayList<T>(); // data to return
        ArrayList<T> child; // data of the next node traversed
        if(curr == null) // reached end of the tree
            return null;
        child = rangeHelper(min, curr.context[1], max); // recurse to left child
        if(child != null) // append elements in child list
            data.addAll(child);
        // Only add if node is in range
        if(curr.data.compareTo(min) >= 0 && curr.data.compareTo(max) <= 0) {
            data.add(curr.data);
        }

        child = rangeHelper(min, curr.context[2], max); // recures to right chlid
        if(child != null) // append elements in child list
            data.addAll(child);
        return data;
    }

    /**
     * Get the size of the tree (its number of nodes).
     * @return the number of nodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     * @return true of this.size() return 0, false if this.size() > 0
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Removes the value data from the tree if the tree contains the value.
     * This method will not attempt to rebalance the tree after the removal and
     * should be updated once the tree uses Red-Black Tree insertion.
     * @return true if the value was remove, false if it didn't exist
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when data is not stored in the tree
     */
    public boolean remove(T data) throws NullPointerException, IllegalArgumentException {
        // null references will not be stored within this tree
        if (data == null) {
            throw new NullPointerException("This RedBlackTree cannot store null references.");
        } else {
            Node<T> nodeWithData = this.findNodeWithData(data);
            // throw exception if node with data does not exist
            if (nodeWithData == null) {
                throw new IllegalArgumentException("The following value is not in the tree and cannot be deleted: " + data.toString());
            }  
            boolean hasRightChild = (nodeWithData.context[2] != null);
            boolean hasLeftChild = (nodeWithData.context[1] != null);
            if (hasRightChild && hasLeftChild) {
                // has 2 children
                Node<T> successorNode = this.findMinOfRightSubtree(nodeWithData);
                // replace value of node with value of successor node
                nodeWithData.data = successorNode.data;
                // remove successor node
                if (successorNode.context[2] == null) {
                    // successor has no children, replace with null
                    this.replaceNode(successorNode, null);
                } else {
                    // successor has a right child, replace successor with its child
                    this.replaceNode(successorNode, successorNode.context[2]);
                }
            } else if (hasRightChild) {
                // only right child, replace with right child
                this.replaceNode(nodeWithData, nodeWithData.context[2]);
            } else if (hasLeftChild) {
                // only left child, replace with left child
                this.replaceNode(nodeWithData, nodeWithData.context[1]);
            } else {
                // no children, replace node with a null node
                this.replaceNode(nodeWithData, null);
            }
            this.size--;
            return true;
        } 
    }

    /**
     * Checks whether the tree contains the value *data*.
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    public boolean contains(T data) {
        // null references will not be stored within this tree
        if (data == null) {
            throw new NullPointerException("This RedBlackTree cannot store null references.");
        } else {
            Node<T> nodeWithData = this.findNodeWithData(data);
            // return false if the node is null, true otherwise
            return (nodeWithData != null);
        }
    }

    /**
     * Helper method that will replace a node with a replacement node. The replacement
     * node may be null to remove the node from the tree.
     * @param nodeToReplace the node to replace
     * @param replacementNode the replacement for the node (may be null)
     */
    protected void replaceNode(Node<T> nodeToReplace, Node<T> replacementNode) {
        if (nodeToReplace == null) {
            throw new NullPointerException("Cannot replace null node.");
        }
        if (nodeToReplace.context[0] == null) {
            // we are replacing the root
            if (replacementNode != null)
                replacementNode.context[0] = null;
            this.root = replacementNode;
        } else {
            // set the parent of the replacement node
            if (replacementNode != null)
                replacementNode.context[0] = nodeToReplace.context[0];
            // do we have to attach a new left or right child to our parent?
            if (nodeToReplace.isRightChild()) {
                nodeToReplace.context[0].context[2] = replacementNode;
            } else {
                nodeToReplace.context[0].context[1] = replacementNode;
            }
        }
    }

    /**
     * Helper method that will return the inorder successor of a node with two children.
     * @param node the node to find the successor for
     * @return the node that is the inorder successor of node
     */
    protected Node<T> findMinOfRightSubtree(Node<T> node) {
        if (node.context[1] == null && node.context[2] == null) {
            throw new IllegalArgumentException("Node must have two children");
        }
        // take a steop to the right
        Node<T> current = node.context[2];
        while (true) {
            // then go left as often as possible to find the successor
            if (current.context[1] == null) {
                // we found the successor
                return current;
            } else {
                current = current.context[1];
            }
        }
    }

    /**
     * Helper method that will return the node in the tree that contains a specific
     * value. Returns null if there is no node that contains the value.
     * @return the node that contains the data, or null of no such node exists
     */
    protected Node<T> findNodeWithData(T data) {
        Node<T> current = this.root;
        while (current != null) {
            int compare = data.compareTo(current.data);
            if (compare == 0) {
                // we found our value
                return current;
            } else if (compare < 0) {
                // keep looking in the left subtree
                current = current.context[1];
            } else {
                // keep looking in the right subtree
                current = current.context[2];
            }
        }
        // we're at a null node and did not find data, so it's not in the tree
        return null; 
    }

    /**
     * This method performs an inorder traversal of the tree. The string 
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations 
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    public String toInOrderString() {
        // generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (this.root != null) {
            Stack<Node<T>> nodeStack = new Stack<>();
            Node<T> current = this.root;
            while (!nodeStack.isEmpty() || current != null) {
                if (current == null) {
                    Node<T> popped = nodeStack.pop();
                    sb.append(popped.data.toString());
                    if(!nodeStack.isEmpty() || popped.context[2] != null) sb.append(", ");
                    current = popped.context[2];
                } else {
                    nodeStack.add(current);
                    current = current.context[1];
                }
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    /**
     * This method performs a level order traversal of the tree. The string
     * representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (this.root != null) {
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this.root);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.context[1] != null) q.add(next.context[1]);
                if(next.context[2] != null) q.add(next.context[2]);
                sb.append(next.data.toString());
                if(!q.isEmpty()) sb.append(", ");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    public String toString() {
        return "level order: " + this.toLevelOrderString() +
                "\nin order: " + this.toInOrderString();
    }

    
    // Implement at least 3 boolean test methods by using the method signatures below,
    // removing the comments around them and addind your testing code to them. You can
    // use your notes from lecture for ideas on concrete examples of rotation to test for.
    // Make sure to include rotations within and at the root of a tree in your test cases.
    // Give each of the methods a meaningful header comment that describes what is being
    // tested and make sure your test hafe inline comments to help developers read through them.
    // If you are adding additional tests, then name the method similar to the ones given below.
    // Eg: public static boolean test4() {}
    // Do not change the method name or return type of the existing tests.
    // You can run your tests by commenting in the calls to the test methods


 //    private RedBlackTree<Integer> _tree = null;
 //    /**
 //     * This method instantiates a red black tree before every test is run
 //     */
 //    @BeforeEach
 //    public void createTree() {
 //        _tree = new RedBlackTree<>();
 //    }
 //
 //    /**
 //     * This method tests that the RedBlackTree is what it is expected to be when
 //     * inserting values from 1 to 10
 //     */
 //    @Test
 //    public void test1() {
 //        _tree.insert(1);
 //        assertEquals(_tree.toLevelOrderString(), "[ 1 ]");
 //        _tree.insert(2);
 //        assertEquals(_tree.toLevelOrderString(), "[ 1, 2 ]");
 //        _tree.insert(3);
 //        assertEquals(_tree.toLevelOrderString(), "[ 2, 1, 3 ]");
 //        _tree.insert(4);
 //        assertEquals(_tree.toLevelOrderString(), "[ 2, 1, 3, 4 ]");
 //        _tree.insert(5);
 //        assertEquals(_tree.toLevelOrderString(), "[ 2, 1, 4, 3, 5 ]");
 //        _tree.insert(6);
 //        assertEquals(_tree.toLevelOrderString(), "[ 2, 1, 4, 3, 5, 6 ]");
 //        _tree.insert(7);
 //        assertEquals(_tree.toLevelOrderString(), "[ 2, 1, 4, 3, 6, 5, 7 ]");
 //        _tree.insert(8);
 //        assertEquals(_tree.toLevelOrderString(), "[ 4, 2, 6, 1, 3, 5, 7, 8 ]");
 //        _tree.insert(9);
 //        assertEquals(_tree.toLevelOrderString(), "[ 4, 2, 6, 1, 3, 5, 8, 7, 9 ]");
 //        _tree.insert(10);
 //        assertEquals(_tree.toLevelOrderString(), "[ 4, 2, 6, 1, 3, 5, 8, 7, 9, 10 ]");
 //    }
 //
 //    /**
 //     * This method is a helper for test2 that recursively traverses the RedBlackTree to ensure that there are
 //     * no red nodes that have a red parent in the tree. Ensures that the red black tree
 //     * is valid.
 //     */
 //    private void checkDoubleRed(Node<Integer> node) {
 //        if(node == null)
 //            return;
 //        if(node.context[0] != null) // not the root node
 //            assertEquals(node.blackHeight == 0 && node.context[0].blackHeight == 0, false); // check not a double red
 //
 //        checkDoubleRed(node.context[1]); // recursively call the left child
 //        checkDoubleRed(node.context[2]); // recursively call the right child
 //    }
 //
 //
 //    /**
 //     * This method inserts values 1 through 10 and ensures that all red nodes
 //     * with red parents have been resolved after insertion.
 //     */
 //    @Test
 //    public void test2() {
 //        _tree.insert(1);
 //        _tree.insert(2);
 //        _tree.insert(3);
 //        _tree.insert(4);
 //        _tree.insert(5);
 //        _tree.insert(6);
 //        _tree.insert(7);
 //        _tree.insert(8);
 //        _tree.insert(9);
 //        _tree.insert(10);
 //
 //        checkDoubleRed(_tree.root); // call helper method that traverses the red black tree
 //    }
 //
 //    /**
 //     * This method ensures that all black heights from the root of the tree
 //     * to any of the leaf node is the same.
 //     */
 //    @Test
 //    public void test3() {
 //        _tree.insert(1);
 //        _tree.insert(2);
 //        _tree.insert(3);
 //        _tree.insert(4);
 //        _tree.insert(5);
 //        _tree.insert(6);
 //        _tree.insert(7);
 //        _tree.insert(8);
 //        _tree.insert(9);
 //        _tree.insert(10);
 // 
 //        int previousBlackHeight = -1;
 //        
 //        for(int i = 0; i < 8; i++) {
 //            Node<Integer> curr = _tree.root;
 //            int blackHeight = 0;
 //            String bin = Integer.toBinaryString(i);
 //            bin = String.format("%0" + (4-bin.length()) + "d%s", 0, bin); // Make the binary string 4 characters 
 //                                                                          // long to match max height of the tree
 //
 //            for(char c: bin.toCharArray()) { // iterate through each bit
 //                if(curr != null) { // If not at the bottom of the tree
 //                    // Can just add blackHeight because black = 1 and red = 0
 //                    blackHeight += curr.blackHeight; // Since no nodes are being removed there will be no double
 //                                                     // black nodes so do not have to worry about black heights
 //                                                     // of 2 is removing was not resolved correctly
 //                    curr = curr.context[(int)c - 48 + 1]; // -48 to adjust for ascii +1 because it is binary and
 //                                                          // want left or right child which is 1 or 2
 //                }
 //            }
 //
 //            if(previousBlackHeight != -1) // not first height found
 //                assertEquals(blackHeight, previousBlackHeight); // check that the blackheights are the same
 //    
 //            previousBlackHeight = blackHeight; // should be the same values
 //        }
 //    }

    
    /**
     * Main method to run tests. Comment out the lines for each test
     * to run them.
     * @param args
     */
    // public static void main(String[] args) {}

}
