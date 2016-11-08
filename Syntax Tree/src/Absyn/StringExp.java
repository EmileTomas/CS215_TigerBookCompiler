/*
 * @(#)StringExp.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing a string expression
 * 
 * @author Kiki
 */
public class StringExp extends Exp {
	
	/**
	 * The string value
	 */
	public String value;
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 * @param v the string value
	 */
	public StringExp(int p, String v) {
		pos=p; 
		value=v;
	}
}
