package datastructures.btree;

import java.io.Serializable;

/**
 * 
 * Node class to be used in datastructures
 * 
 * @author Venugopal K
 *
 */
public class Node implements Serializable {

	/**
	 * Data stored as integers
	 */
	private int data = -1;

	/**
	 * left node of the binary tree/linked list etc..
	 * 
	 */
	private Node lnode = null;

	/**
	 * right node of the binary tree/linked list ...
	 * 
	 */
	private Node rnode = null;

	/**
	 * 
	 * @param value
	 */
	public Node(int value) {
		this.data = value;
	}

	/**
	 * return the value of the node
	 * 
	 * @return
	 */
	public int getData() {
		return data;
	}

	/**
	 * update the right node
	 * 
	 * @param node
	 */
	public void setRightNode(Node node) {
		this.rnode = node;
	}

	/**
	 * update the left node
	 * 
	 * @param node
	 */
	public void setLeftNode(Node node) {
		this.lnode = node;
	}

	/**
	 * return the left node
	 * 
	 * @return
	 */
	public Node getLeftNode() {
		return this.lnode;
	}

	/**
	 * return the right node
	 * 
	 * @return
	 */
	public Node getRightNode() {
		return this.rnode;
	}

}
