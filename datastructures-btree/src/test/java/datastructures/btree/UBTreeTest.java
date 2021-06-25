package datastructures.btree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class UBTreeTest {

	Logger logger = Logger.getLogger(UBTreeTest.class.getName());

	/**
	 * Build a binary tree serialize and de-serialize using depth traversal
	 */
	@Test
	public void compareTestObjectTree() {
		try {

			logger.log(Level.INFO, "Starting compareTestObjectTree ");
			// Build the in memory tree
			Node n = new Node(100);
			Node n1l = new Node(10);
			Node n1r = new Node(20);
			n.setLeftNode(n1l);
			n.setRightNode(n1r);

			Node n2l = new Node(101);
			Node n2r = new Node(102);

			n1l.setLeftNode(n2l);
			n1r.setRightNode(n2r);

			Node n3l = new Node(1011);
			n2l.setLeftNode(n3l);
			n3l.setLeftNode(new Node(10111));

			BinaryTree memtree = new ObjectWriteableBTree(n);
			String builtTree = memtree.asString();
			logger.log(Level.INFO, "Tree is " + builtTree);

			ObjectOutputStream fis = new ObjectOutputStream(
					new FileOutputStream(new File("/tmp/compareTestObjectTree.dat")));
			memtree.write(fis);
			fis.close();

			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream(new File("/tmp/compareTestObjectTree.dat")));
			// Node newnode = (Node) ois.readObject();

			BinaryTree rtree = new ObjectWriteableBTree();
			rtree.build(ois);

			String reconstructed = rtree.asString();
			logger.log(Level.INFO, "Read Tree is" + reconstructed);

			assertEquals("Successfully did not re-construct the tree", builtTree, reconstructed);

		} catch (Exception ex) {
			assertFalse("Successfully did not re-construct the tree", true);
		}

		logger.log(Level.INFO, "Completed compareTestObjectTree ");

	}

	/**
	 * Negative test where serialize one tree and read another and compare
	 */
	@Test
	public void compareTestObjectTreeNegative() {
		try {

			logger.log(Level.INFO, "Starting compareTestObjectTreeNegative ");
			// Build the in memory tree
			Node n = new Node(100);
			Node n1l = new Node(10);
			Node n1r = new Node(20);
			n.setLeftNode(n1l);
			n.setRightNode(n1r);

			Node n2l = new Node(101);
			Node n2r = new Node(102);

			n1l.setLeftNode(n2l);
			n1r.setRightNode(n2r);

			Node n3l = new Node(1011);
			n2l.setLeftNode(n3l);
			Node n4l = new Node(10111);
			n3l.setLeftNode(n4l);
			n4l.setLeftNode(new Node(1014));

			BinaryTree memtree = new ObjectWriteableBTree<>(n);
			String builtTree = memtree.asString();
			logger.log(Level.INFO, "Tree is " + builtTree);

			ObjectOutputStream fis = new ObjectOutputStream(
					new FileOutputStream(new File("/tmp/compareTestObjectTreeNegative.dat")));
			memtree.write(fis);
			fis.close();

			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream(new File("/tmp/compareTestObjectTree.dat")));

			BinaryTree rtree = new ObjectWriteableBTree();
			rtree.build(ois);

			String reconstructed = rtree.asString();
			logger.log(Level.INFO, "Read Tree is" + reconstructed);

			assertNotEquals("we re-construct the same tree", builtTree, reconstructed);

		} catch (Exception ex) {
			ex.printStackTrace();
			assertFalse("Successfully did not re-construct the tree", true);
		}

		logger.log(Level.INFO, "Completed compareTestObjectTreeNegative ");

	}

	@Test
	public void compareCommaSeperatedFile() {
		try {

			logger.log(Level.INFO, "Starting compareCommaSeperatedFile ");
			// Build the in memory tree
			Node n = new Node(100);
			Node n1l = new Node(10);
			Node n1r = new Node(20);
			n.setLeftNode(n1l);
			n.setRightNode(n1r);

			Node n2l = new Node(101);
			Node n2r = new Node(102);

			n1l.setLeftNode(n2l);
			n1r.setRightNode(n2r);

			Node n3l = new Node(1011);
			n2l.setLeftNode(n3l);
			Node n4l = new Node(10111);
			n3l.setLeftNode(n4l);
			n4l.setLeftNode(new Node(44));

			BinaryTree memtree = new CustomWriteableBTree<>(n);
			String builtTree = memtree.asString();
			logger.log(Level.INFO, "Tree is " + builtTree);

			OutputStream fis = new FileOutputStream(new File("/tmp/compareCommaSeperatedFile.dat"));
			memtree.write(fis);
			fis.close();

			InputStream bis = new FileInputStream(new File("/tmp/compareCommaSeperatedFile.dat"));
			BinaryTree rtree = new CustomWriteableBTree();
			rtree.build(bis);

			String reconstructed = rtree.asString();
			logger.log(Level.INFO, "Read Tree is" + reconstructed);

			assertEquals("we re-construct the same tree", builtTree, reconstructed);

		} catch (Exception ex) {
			ex.printStackTrace();
			assertFalse("Successfully did not re-construct the tree", true);
		}

		logger.log(Level.INFO, "Completed compareCommaSeperatedFile ");

	}
}
