/*
 * @(#)OpExp.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing an operation expression
 * 
 * @author Kiki
 */
public class OpExp extends Exp {
	
	/**
	 * left expression of Operation expression
	 */
	public Exp left;
	
	/**
	 * right expression of Operation expression
	 */
	public Exp right;
	
	/**
	 * Operator of Operation expression
	 */
	public int oper;
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 * @param l left expression of Operation expression
	 * @param o right expression of Operation expression
	 * @param r Operator of Operation expression
	 */
	public OpExp(int p, Exp l, int o, Exp r) {
		pos=p;
		left=l;
		oper=o;
		right=r;
	}
	
	/**
	 * Operators
	 */
	public final static int PLUS=0, MINUS=1, MUL=2, DIV=3,
		    EQ=4, NE=5, LT=6, LE=7, GT=8, GE=9;
}
