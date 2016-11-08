/*
 * @(#)ArrayTy.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing an Array type
 * 
 * @author Kiki
 */
public class ArrayTy extends Ty {
	
	/**
	 * Type of the array elements.
	 */
	public Symbol.Symbol typ;
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 * @param t type of array elements
	 */
	public ArrayTy(int p, Symbol.Symbol t) {
		pos=p;
		typ=t;
	}
}
