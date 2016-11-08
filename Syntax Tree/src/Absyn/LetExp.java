/*
 * @(#)LetExp.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing an Let expression
 * 
 * @author Kiki
 */
public class LetExp extends Exp {
	
	/**
	 * Declarations
	 */
	public DecList decs;
	
	/**
	 * Main body
	 */
	public Exp body;
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 * @param d declarations
	 * @param b body
	 */
	public LetExp(int p, DecList d, Exp b) {
		pos=p;
		decs=d; 
		body=b;
	}
}
