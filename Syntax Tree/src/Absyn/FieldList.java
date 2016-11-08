/*
 * @(#)FieldList.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing a field list
 * 
 * @author Kiki
 */
public class FieldList extends Absyn {
	
	/**
	 * Name of the field
	 */
	public Symbol.Symbol name;
	
	/**
	 * Type of the field
	 */
	public Symbol.Symbol typ;
	
	/**
	 * Next field
	 */
	public FieldList tail;
	
	/**
	 * Indicates whether this field escapes.
	 */
	public boolean escape = true;
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 * @param n Name of the field
	 * @param t type of the field
	 * @param x next field
	 */
	public FieldList(int p, Symbol.Symbol n, Symbol.Symbol t, FieldList x) {
		pos=p;
		name=n;
		typ=t;
		tail=x;
	}
}
