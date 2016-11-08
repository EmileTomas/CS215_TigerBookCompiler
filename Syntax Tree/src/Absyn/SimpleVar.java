/*
 * @(#)SimpleVar.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing a simple variable
 * 
 * @author Kiki
 */
public class SimpleVar extends Var {
	
	/**
	 * Variable name
	 */
	public Symbol.Symbol name;
	
	/**
	 * Constructor
	 * @param p position
	 * @param n variable name
	 */
	public SimpleVar (int p, Symbol.Symbol n) {
		pos=p;
		name=n;
	}
}
