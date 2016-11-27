/*
 * @(#)Nx.java	1.0 2011/01/09
 * Package: Translate
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Translate;

import Temp.Label;
import Tree.Stm;

/**
 * This class represents an expression without value
 * 
 * @author Kiki
 */
class Nx extends Exp {
	
	/**
	 * The corresponding statement
	 */
	Tree.Stm stm;
	
	/**
	 * Constructor 
	 * @param s Tree.Stm
	 */
	Nx(Tree.Stm s) {
		stm = s;
	}

	@Override
	Stm unCx(Label t, Label f) {
		return null;
	}

	@Override
	Tree.Exp unEx() {
		return null;
	}

	@Override
	Stm unNx() {
		return stm;
	}

}
