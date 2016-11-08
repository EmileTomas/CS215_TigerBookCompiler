/*
 * @(#)DecList.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing a declaration list
 * 
 * @author Kiki
 */
public class DecList {
	
	/**
	 * The declaration
	 */
	public Dec head;
	
	/**
	 * The next declaration
	 */
	public DecList tail;
	
	/**
	 * Construtor
	 * 
	 * @param h The declaration
	 * @param t The next declaration
	 */
	public DecList(Dec h, DecList t) {
		head=h;
		tail=t;
	}
}
