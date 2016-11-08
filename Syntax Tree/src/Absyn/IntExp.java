/*
 * @(#)IntExp.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing an integer expression
 * 
 * @author Kiki
 */
public class IntExp extends Exp {
	
	/**
	 * Integer value
	 */
	public int value;
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 * @param v Integer value
	 */
	public IntExp(int p, int v) {
		pos=p; 
		value=v;
	}
}
