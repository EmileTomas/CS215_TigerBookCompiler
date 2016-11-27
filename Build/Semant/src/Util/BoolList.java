/*
 * @(#)BoolList.java	1.0 2011/01/09
 * Package: Util
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Util;

/**
 * A boolean list
 * 
 * @author Kiki
 */
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
