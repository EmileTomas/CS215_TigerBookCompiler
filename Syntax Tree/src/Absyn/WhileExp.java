/*
 * @(#)WhileExp.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing a While expression
 * 
 * @author Kiki
 */
public class WhileExp extends Exp {
	
	/**
	 * Condition
	 */
	public Exp test;
	
	/**
	 * While body
	 */
	public Exp body;
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 * @param t condition
	 * @param b while body
	 */
	public WhileExp(int p, Exp t, Exp b) {
		pos=p;
		test=t;
		body=b;
	}
}
