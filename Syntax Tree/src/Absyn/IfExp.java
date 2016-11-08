/*
 * @(#)IfExp.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing an If expression
 * 
 * @author Kiki
 */
public class IfExp extends Exp {
	
	/**
	 * The condition of If expression
	 */
	public Exp test;
	
	/**
	 * Then clause of If expression
	 */
	public Exp thenclause;
	
	/**
	 * Else clause of If expression
	 */
	public Exp elseclause; /* optional */
	
	/**
	 * Constructor
	 * @param p position
	 * @param x The condition of If expression
	 * @param y Then clause of If expression
	 */
	public IfExp(int p, Exp x, Exp y) {
		pos=p;
		test=x;
		thenclause=y;
		elseclause=null;
	}
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 * @param x The condition of If expression
	 * @param y Then clause of If expression
	 * @param z Else clause of If expression
	 */
	public IfExp(int p, Exp x, Exp y, Exp z) {
		pos=p;
		test=x;
		thenclause=y;
		elseclause=z;
	}
}
