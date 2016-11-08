/*
 * @(#)StringExp.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing a subscript variable
 * 
 * @author Kiki
 */
public class SubscriptVar extends Var {
	
	/**
	 * The main variable.
	 */
	public Var var;
	
	/**
	 * The variable's index.
	 */
	public Exp index;
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 * @param v The main variable.
	 * @param i The variable's index.
	 */
	public SubscriptVar(int p, Var v, Exp i) {
		pos=p;
		var=v;
		index=i;
	}
}
