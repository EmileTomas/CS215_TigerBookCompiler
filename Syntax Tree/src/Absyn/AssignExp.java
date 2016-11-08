/*
 * @(#)AssignExp.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing an assign expression
 * 
 * @author Kiki
 */
public class AssignExp extends Exp {
	
	/**
	 * The variable to assign to
	 */
	public Var var;
	
	/**
	 * Expression of assign value
	 */
	public Exp exp;
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 * @param v The variable to assign to
	 * @param e Expression of assign value
	 */
	public AssignExp(int p, Var v, Exp e) {
		pos=p;
		var=v;
		exp=e;
	}
}
