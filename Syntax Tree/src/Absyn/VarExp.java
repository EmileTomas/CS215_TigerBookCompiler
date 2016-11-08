/*
 * @(#)VarExp.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing a variable expression
 * 
 * @author Kiki
 */
public class VarExp extends Exp {
	
	/**
	 * Variable
	 */
	public Var var;
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 * @param v variable
	 */
	public VarExp(int p, Var v) {
		pos=p;
		var=v;
	}
}   
