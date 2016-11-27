/*
 * @(#)AccessList.java	1.0 2011/01/09
 * Package: Translate
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Translate;

/**
 * Class for an access list
 * 
 * @author Kiki
 */
public class AccessList {
	
	/**
	 * The access itself
	 */
	public Access		head;
	
	/**
	 * The next access
	 */
	public AccessList	tail;
	
	/**
	 * Constructor
	 * 
	 * @param h
	 * @param t
	 */
	public AccessList(Access h, AccessList t) {
		head = h;
		tail = t;
	}
}
