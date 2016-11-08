/*
 * @(#)ArrayExp.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved..
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing an Expression of Array type
 * 
 * @author Kiki
 */
public class ArrayExp extends Exp {
	
	/**
	 * Type of the array elements.
	 */
	public Symbol.Symbol typ;
	
	/**
	 * Size of the array.
	 */
	public Exp		size;
	
	/**
	 * Initial value of the array.
	 */
	public Exp		init;
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 * @param t type of array elements
	 * @param s size of the array
	 * @param i initial value of the array
	 */
	public ArrayExp(int p, Symbol.Symbol t, Exp s, Exp i) {
		pos=p;
		typ=t;
		size=s;
		init=i;
	}
}
