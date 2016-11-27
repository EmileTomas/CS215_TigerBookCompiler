/*
 * @(#)RelCx.java	1.0 2011/01/09
 * Package: Translate
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Translate;

import Temp.Label;
import Tree.Stm;
import Absyn.OpExp;
import Tree.CJUMP;

/**
 * This class represents a concrete Cx
 * 
 * @author Kiki
 */
public class RelCx extends Cx {
	
	/**
	 * The comparison operator
	 */
	int	op		= 0;
	
	/**
	 * The left operand
	 */
	Exp	left	= null;
	
	/**
	 * The right operand
	 */
	Exp	right	= null;
	
	/**
	 * Constructor
	 * 
	 * @param op
	 * @param left
	 * @param right
	 */
	public RelCx(int op, Exp left, Exp right)
	{
		switch (op) {
		case OpExp.EQ :
			this.op = CJUMP.EQ;
			break;
		case OpExp.NE :
			this.op = CJUMP.NE;
			break;
		case OpExp.GT :
			this.op = CJUMP.GT;
			break;
		case OpExp.GE :
			this.op = CJUMP.GE;
			break;
		case OpExp.LT :
			this.op = CJUMP.LT;
			break;
		case OpExp.LE :
			this.op = CJUMP.LE;
			break;
		default:
			throw new Error("RelCx Operator error!");
		}
		this.left = left;
		this.right = right;
	}
	
	@Override
	Stm unCx(Label t, Label f) {
		return new Tree.CJUMP(op, left.unEx(), right.unEx(), t, f);
	}

}
