/*
 * @(#)VarDec.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing a variable declaration
 * 
 * @author Kiki
 */
public class VarDec extends Dec {
	
	/**
	 * variable name
	 */
	public Symbol.Symbol name;
	
	/**
	 * Indicates whether this field escapes.
	 */
	public boolean escape = true;
	
	/**
	 * Variable type
	 */
	public NameTy typ; /* optional */
	
	/**
	 * Initial expression of variable
	 */
	public Exp init;
	
	/**
	 * Constructor
	 * 
	 * @param p position 
	 * @param n variable name
	 * @param t Variable type
	 * @param i Initial expression of variable
	 */
	public VarDec(int p, Symbol.Symbol n, NameTy t, Exp i) {
		pos=p;
		name=n;
		typ=t;
		init=i;
	}
}
