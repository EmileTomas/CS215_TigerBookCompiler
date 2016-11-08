/*
 * @(#)NameTy.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class of type of name
 * 
 * @author Kiki
 */
public class NameTy extends Ty {
	
	/**
	 * Type name
	 */
	public Symbol.Symbol name;
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 * @param n Type name
	 */
	public NameTy(int p, Symbol.Symbol n) {
		pos=p;
		name=n;
	}
}
