/*
 * @(#)FunEntry.java	1.0 2011/01/09
 * Package: Semant
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Semant;

/**
 * Class for function entry
 * 
 * @author Kiki
 */
class FunEntry extends Entry {
	
	/**
	 * The level that function belongs to
	 */
	public Translate.Level level;
	
	/**
	 * Function name
	 */
	public Temp.Label label;
	
	/**
	 * Function formals
	 */
	public Types.RECORD formals;
	
	/**
	 * Type of function result
	 */
	public Types.Type result;
	
	/**
	 * Constructor
	 * 
	 * @param v
	 * @param l
	 * @param f
	 * @param r
	 */
	public FunEntry(Translate.Level v, Temp.Label l, Types.RECORD f, Types.Type r) {
		level = v;
		label = l;
		formals = f;
		result = r;
	}
}