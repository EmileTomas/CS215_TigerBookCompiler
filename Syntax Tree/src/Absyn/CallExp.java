/*
 * @(#)CallExp.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing a call expression
 * 
 * @author Kiki
 */
public class CallExp extends Exp {
	
	/**
	 * Name of the called function
	 */
	public Symbol.Symbol func;
	
	/**
	 * Arguments of the called function
	 */
	public ExpList args;
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 * @param f Name of the called function
	 * @param a Arguments of the called function
	 */
	public CallExp(int p, Symbol.Symbol f, ExpList a) {
		pos=p;
		func=f;
		args=a;
	}
}
