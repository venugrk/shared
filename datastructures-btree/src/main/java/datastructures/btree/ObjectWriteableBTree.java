package datastructures.btree;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ObjectWriteableBTree<E> extends BinaryTree<E> {

	public ObjectWriteableBTree() {

	}

	public ObjectWriteableBTree(Node node) {
		super(node);
	}

	@Override
	public void build(InputStream fis) throws IOException {
		rootNode = buildOTree((ObjectInputStream) fis);

	}

	@Override
	public void write(OutputStream os) throws IOException {
		writeOTree(rootNode, (ObjectOutputStream) os);

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

}
