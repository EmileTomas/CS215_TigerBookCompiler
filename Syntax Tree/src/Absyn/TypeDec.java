/*
 * @(#)StringExp.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing a type declaration
 * 
 * @author Kiki
 */
public class TypeDec extends Dec {
	
	/**
	 * Type name
	 */
	public Symbol.Symbol name;
	
	/**
	 * Type
	 */
	public Ty ty;
	
	/**
	 * Next type declaration
	 */
	public TypeDec next;
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 * @param n Type name
	 * @param t Type
	 * @param x Next type declaration
	 */
	public TypeDec(int p, Symbol.Symbol n, Ty t, TypeDec x) {
		pos=p;
		name=n;
		ty=t;
		next=x;
	}
}
