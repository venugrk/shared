package datastructures.btree;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;

public interface BTree {

	/**
	 * 
	 * @param fis
	 * @throws IOException
	 */
	public void build(InputStream fis) throws IOException;

	/**
	 * 
	 * @param os
	 * @throws IOException
	 */
	public void write(OutputStream os) throws IOException;

	/**
	 * dont want to implement toString and hascode hence asString
	 * 
	 * @return
	 */
	public String asString();
}
