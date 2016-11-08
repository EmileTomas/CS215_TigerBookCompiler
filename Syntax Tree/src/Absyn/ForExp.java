/*
 * @(#)ForExp.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing a For expression
 * 
 * @author Kiki
 */
public class ForExp extends Exp {
	
	/**
	 * The variable of for expression
	 */
	public VarDec var;
	
	/**
	 * The high limit of variable
	 */
	public Exp hi;
	
	/**
	 * The body of for expression
	 */
	public Exp body;
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 * @param v The variable of for expression
	 * @param h The high limit of variable
	 * @param b The body of for expression
	 */
	public ForExp(int p, VarDec v, Exp h, Exp b) {
		pos=p;
		var=v;
		hi=h;
		body=b;
	}
}
