/*
 * @(#)FieldExpList.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing a field expression list
 * 
 * @author Kiki
 */
public class FieldExpList extends Absyn {
	
	/**
	 * Name of the field
	 */
	public Symbol.Symbol name;
	
	/**
	 * Initial value of the field
	 */
	public Exp init;
	
	/**
	 * Next Field
	 */
	public FieldExpList tail;
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 * @param n Name of the field
	 * @param i Initial value of the field
	 * @param t Next Field
	 */
	public FieldExpList(int p, Symbol.Symbol n, Exp i, FieldExpList t) {
		pos=p; 
		name=n; 
		init=i;
		tail=t;
	}
}
