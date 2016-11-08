/*
 * @(#)SeqTy.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing a expression sequence
 * 
 * @author Kiki
 */
public class SeqExp extends Exp {
	
	/**
	 * The sequence expression list
	 */
	public ExpList list;
	
	/**
	 * Constructor
	 * @param p position
	 * @param l The sequence expression list
	 */
	public SeqExp(int p, ExpList l) {
		pos=p;
		list=l;
	}
}
