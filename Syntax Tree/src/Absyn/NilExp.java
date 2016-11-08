/*
 * @(#)NilExp.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing a nil expression
 * 
 * @author Kiki
 */
public class NilExp extends Exp {
	
	/**
	 * Constructor
	 * 
	 * @param p position
	 */
	public NilExp(int p) {
		pos=p;
	}
}
