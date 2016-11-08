/*
 * @(#)FunctionDec.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing a function declaration
 * 
 * @author Kiki
 */
public class FunctionDec extends Dec {
	
	/**
	 * Fuction name
	 */
	public Symbol.Symbol name;
	
	/**
	 * Function parameters
	 */
	public FieldList params;
	
	/**
	 * Type of function result
	 */
	public NameTy result;  /* optional */
	
	/**
	 * Function body
	 */
	public Exp body;
	
	/**
	 * Next function declaration
	 */
	public FunctionDec next;
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 * @param n Function name
	 * @param a Function parameters
	 * @param r Type of function result
	 * @param b Function body
	 * @param x Next function declaration
	 */
	public FunctionDec(int p, Symbol.Symbol n, FieldList a, NameTy r, Exp b, FunctionDec x) {
	   pos=p;
	   name=n;
	   params=a;
	   result=r;
	   body=b;
	   next=x;
	}
}
