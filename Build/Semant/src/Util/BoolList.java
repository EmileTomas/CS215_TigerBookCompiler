package Util;

public class BoolList {
	
	/**
	 * Boolean head
	 */
	public boolean head;
	
	/**
	 * Next Boolean
	 */
	public BoolList tail;
	
	/**
	 * Constructor
	 * 
	 * @param h
	 * @param t
	 */
	public BoolList(boolean h, BoolList t) {
		head=h;
		tail=t;
	}
}
