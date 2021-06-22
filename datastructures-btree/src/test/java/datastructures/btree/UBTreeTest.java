package datastructures.btree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class UBTreeTest {
	/**
	 * Rigorous Test :-)
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@Test
	public void compareTreeTestObjectStream() {

		try {

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

			BinaryTree memtree = new BinaryTree(n);
			String builtTree = memtree.toString();
			System.out.println("Tree is " + builtTree);

			ObjectOutputStream fis = new ObjectOutputStream(new FileOutputStream(new File("/tmp/a.dat")));
			memtree.write(fis);
			fis.close();

			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("/tmp/a.dat")));
			// Node newnode = (Node) ois.readObject();

			BinaryTree rtree = new BinaryTree();
			rtree.build(ois);

			String reconstructed = rtree.toString();
			System.out.println("Read Tree is" + reconstructed);

			assertEquals("Successfully did not re-construct the tree", builtTree, reconstructed);

		} catch (Exception ex) {
			assertFalse("Successfully did not re-construct the tree", true);
		}

	}
}
