/*
 * @(#)FieldVar.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing a field variable.
 * 
 * @author Kiki
 */
public class FieldVar extends Var {
	
	/**
	 * The main variable
	 */
	public Var var;
	
	/**
	 * The field variable
	 */
	public Symbol.Symbol field;
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 * @param v the main variable
	 * @param f the field variable
	 */
	public FieldVar(int p, Var v, Symbol.Symbol f) {
		pos=p;
		var=v;
		field=f;
	}
}
