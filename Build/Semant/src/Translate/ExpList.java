/*
 * @(#)ExpList.java	1.0 2011/01/09
 * Package: Translate
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Translate;

/**
 * Represents an expression list
 * 
 * @author Kiki
 */
public class ExpList {
	
	/**
	 * The expression
	 */
	public Exp		head;
	
	/**
	 * The tail expression
	 */
	public ExpList	tail;
	
	/**
	 * Constructor
	 * 
	 * @param head
	 * @param tail
	 */
	public ExpList(Exp head, ExpList tail) {
		this.head = head;
		this.tail = tail;
	}
}
