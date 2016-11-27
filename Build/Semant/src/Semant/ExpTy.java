/*
 * @(#)ExpTy.java	1.0 2011/01/09
 * Package: Semant
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Semant;

import Translate.Exp;
import Types.Type;

/**
 * Class representing an expression and its type
 * 
 * @author Kiki
 */
public class ExpTy {
	
	/**
	 * Expression
	 */
	Exp exp;
	
	/**
	 * Expression's type
	 */
	Type ty;
	
	/**
	 * Constructor
	 * 
	 * @param e
	 * @param t
	 */
	ExpTy(Exp e, Type t) {
		exp = e;
		ty = t;
	}
}
