/*
 * @(#)ProcFrag.java	1.0 2011/01/09
 * Package: Translate
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Translate;

/**
 * This class represents a procedure fragment
 * 
 * @author Kiki
 */
public class ProcFrag extends Frag{
	
	/**
	 * The procedure's body
	 */
	public Tree.Stm body;
	
	/**
	 * The frame that procedure lies in
	 */
	public Frame.Frame frame;
	
	/**
	 * Constructor
	 * 
	 * @param stm
	 * @param frame
	 */
	public ProcFrag(Tree.Stm stm, Frame.Frame frame) {
		this.body = stm;
		this.frame = frame;
	}
}
