/*
 * @(#)Ex.java	1.0 2011/01/09
 * Package: Translate
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Translate;

import Temp.Label;
import Tree.CJUMP;
import Tree.CONST;
import Tree.Stm;

/**
 * This class represents an expression with value
 * 
 * @author Kiki
 */
class Ex extends Exp{
	
	/**
	 * The expression to represent
	 */
	Tree.Exp exp = null;
	
	/**
	 * Constructor
	 * @param e Tree.Exp
	 */
	Ex(Tree.Exp e) {
		exp = e;
	}
	
	@Override
	Tree.Exp unEx() {
		return exp;
	}

	@Override
	Stm unCx(Label t, Label f) {
		//if (exp!=0) goto T else goto F
		return new CJUMP(CJUMP.NE, exp , new CONST(0), t, f);
	}

	@Override
	Stm unNx() {
		return exp==null ? null : new Tree.EXPR(exp);
	}
	
}
