package datastructures.btree;

import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * Build a Binary Tree from various sources
 * 
 * @author Venugopal K
 * @param <E>
 *
 */
public abstract class BinaryTree<E> implements BTree {
	Logger logger = Logger.getLogger(BinaryTree.class.getCanonicalName());

	/**
	 * Root node of the binary tree
	 */
	protected Node rootNode;
	/**
	 * Marker if we are taking care of serializing and de-serializing the tree
	 */
	protected final static int MARKER = -1;

	public BinaryTree() {
		// TODO Auto-generated constructor stub
	}

	public BinaryTree(Node rnode) {
		this.rootNode = rnode;
	}

	public void printString(Node node, StringBuilder builder) {
		if (node == null) {
			builder.append(MARKER);
			builder.append(",");

			return;
		}

		builder.append(node.getData());
		builder.append(",");

		printString(node.getLeftNode(), builder);
		printString(node.getRightNode(), builder);
	}

	/**
	 * Convert the tree to String representation
	 */
	public String asString() {

		String tree;
		StringBuilder builder = new StringBuilder();

		printString(rootNode, builder);

		tree = builder.toString();
		logger.log(Level.INFO, tree);

		return tree;
	}

}
