/*
 * @(#)VarEntry.java	1.0 2011/01/09
 * Package: Semant
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Semant;

/**
 * Class for variable entry
 * 
 * @author Kiki
 */
class VarEntry extends Entry {
	
	/**
	 * Variable's access
	 */
	Translate.Access access;
	
	/**
	 * Variable type
	 */
	Types.Type ty;
	
	/**
	 * Constructor
	 * 
	 * @param acc
	 * @param t
	 */
	VarEntry(Translate.Access acc, Types.Type t) {
		access = acc;
		ty = t;
	}
}
