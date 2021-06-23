package datastructures.btree;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * Build a Binary Tree from various sources
 * 
 * @author Venugopal K
 *
 */
public class BinaryTree {
	Logger logger = Logger.getLogger(BinaryTree.class.getCanonicalName());

	/**
	 * Root node of the binary tree
	 */
	private Node rootNode;
	/**
	 * Marker if we are taking care of serializing and de-serializing the tree
	 */
	private final static int MARKER = -1;

	public BinaryTree() {
		// TODO Auto-generated constructor stub
	}

	public BinaryTree(Node rnode) {
		this.rootNode = rnode;
	}

	/**
	 * Convert the tree to String representation
	 */
	public String toString() {

		String tree;
		StringBuilder builder = new StringBuilder();

		printString(rootNode, builder);

		tree = builder.toString();
		logger.log(Level.INFO, tree);

		return tree;
	}

	/**
	 * Re-construct the tree from Object Stream
	 * 
	 * @param ois Object stream to build tree from
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void build(ObjectInputStream ois) throws ClassNotFoundException, IOException {

		rootNode = (Node) ois.readObject();
	}

	/**
	 * Serialize the tree in given Object Stream
	 * 
	 * @param oos Object stream to write the given object to
	 * @throws IOException
	 */
	public void write(ObjectOutputStream oos) throws IOException {

		oos.writeObject(rootNode);

		return;
	}

	public void buildObectTree(ObjectInputStream ois) throws IOException {
		rootNode = buildOTree(ois);
	}

	public void writeObjectTree(ObjectOutputStream oos) throws IOException {
		writeOTree(rootNode, oos);
	}

	private void writeOTree(Node node, ObjectOutputStream oos) throws IOException {

		if (node == null) {
			oos.writeInt(MARKER);
			return;
		}

		oos.writeInt(node.getData());
		writeOTree(node.getLeftNode(), oos);
		writeOTree(node.getRightNode(), oos);
	}

	private Node buildOTree(ObjectInputStream ois) throws IOException {

		int data = ois.readInt();
		if (data == MARKER) {
			return null;
		}
		Node n = new Node(data);
		n.setLeftNode(buildOTree(ois));
		n.setRightNode(buildOTree(ois));
		return n;

	}

	private Node buildTree(ArrayDeque<Integer> queue) throws IOException {

		int data = queue.pop();
		if (data == MARKER || queue.isEmpty()) {
			return null;
		}
		Node n = new Node(data);
		n.setLeftNode(buildTree(queue));
		n.setRightNode(buildTree(queue));
		return n;

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

}
