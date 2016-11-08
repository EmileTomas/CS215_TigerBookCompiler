/*
 * @(#)RecordExp.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing a record expression.
 * 
 * @author Kiki
 */
public class RecordExp extends Exp {
	
	/**
	 * Record name
	 */
	public Symbol.Symbol typ;
	
	/**
	 * Record fields
	 */
	public FieldExpList fields;
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 * @param t record name
	 * @param f record fields
	 */
	public RecordExp(int p, Symbol.Symbol t, FieldExpList f) {
		pos=p;
		typ=t;
		fields=f;
	}
}
