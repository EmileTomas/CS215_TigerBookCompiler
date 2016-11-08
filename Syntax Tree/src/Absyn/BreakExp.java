/*
 * @(#)BreakExp.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing a Break expression
 * 
 * @author Kiki
 */
public class BreakExp extends Exp {
	
	/**
	 * Construction
	 * 
	 * @param p position
	 */
	public BreakExp(int p) {
		pos=p;
	}
}
