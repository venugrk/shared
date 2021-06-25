package datastructures.btree;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayDeque;

public class CustomWriteableBTree<E> extends BinaryTree<E> {

	public CustomWriteableBTree() {

	}

	public CustomWriteableBTree(Node node) {
		super(node);
	}

	@Override
	public void build(InputStream fis) throws IOException {
		buildTree(fis);
	}

	@Override
	public void write(OutputStream os) throws IOException {
		writeBTree(rootNode, os);

	}

	private Node construct(ArrayDeque<Integer> queue) throws IOException {

		int data = queue.pop();

		if (data == MARKER || queue.isEmpty()) {
			return null;
		}
		Node n = new Node(data);
		n.setLeftNode(construct(queue));
		n.setRightNode(construct(queue));
		return n;

	}

	private void buildTree(InputStream bis) throws IOException {
		ArrayDeque<Integer> dq = new ArrayDeque<>();

		int index = 0;
		byte[] data = bis.readAllBytes();
		if (data.length == 0) {
			return;
		}
		do {
			int value = Bits.getInt(data, index);
			index = index + 4;
			dq.add(Integer.valueOf(value));

		} while (index < data.length);

		rootNode = construct(dq);
	}

	public void writeBTree(Node node, OutputStream fis) throws IOException {
		byte[] d = new byte[4];
		if (node == null) {

			Bits.putInt(d, 0, MARKER);

			fis.write(d);
			return;
		}

		Bits.putInt(d, 0, node.getData());

		fis.write(d);

		writeBTree(node.getLeftNode(), fis);
		writeBTree(node.getRightNode(), fis);
	}

}
