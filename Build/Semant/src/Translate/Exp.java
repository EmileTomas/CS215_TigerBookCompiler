/*
 * @(#)Exp.java	1.0 2011/01/09
 * Package: Translate
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Translate;

/**
 * This class represents an expression.
 * 
 * @author Kiki
 */
public abstract class Exp {
	
	/**
	 * Expression represented as a Tree.Exp
	 * @return Tree.Exp
	 */
	abstract Tree.Exp unEx();
	
	/**
	 * No Result represented as a Tree statement.
	 * @return Tree.Stm
	 */
	abstract Tree.Stm unNx();
	
	/**
	 * Conditional, represented as a function form from label-pair to statement.
	 * @param t True destination
	 * @param f False destination
	 * @return Tree.Stm
	 */
	abstract Tree.Stm unCx(Temp.Label t, Temp.Label f);
}
