/*
 * @(#)WhileExp.java	1.0 2011/01/09
 * Package: Translate
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Translate;

import Temp.Label;
import Tree.JUMP;
import Tree.LABEL;
import Tree.SEQ;
import Tree.Stm;

/**
 * Represents a while expression
 * 
 * @author Kiki
 */
public class WhileExp extends Exp{
	
	/**
	 * The test expression
	 */
	Exp		test;
	
	/**
	 * The body expression
	 */
	Exp		body;
	
	/**
	 * The finish label
	 */
	Label	done;
	
	/**
	 * Constructor
	 * 
	 * @param test
	 * @param body
	 * @param done
	 */
	public WhileExp(Exp test, Exp body, Label done)
	{
		this.test = test;
		this.body = body;
		this.done = done;
	}
	
	/**
	 * While Expression has only on exit
	 */
	@Override
	Stm unCx(Label t, Label f) {
		System.err.println("WhileExp.unCx()");
		return null;
	}
	
	/**
	 * While Expression has no return value
	 */
	@Override
	Tree.Exp unEx() {
		System.err.println("WhileExp.unEx()");
		return null;
	}

	@Override
	Stm unNx() {
		Label begin = new Label();
		Label t = new Label();
		return  new SEQ(new LABEL(begin),
				new SEQ(test.unCx(t, done),
				new SEQ(new LABEL(t),
				new SEQ(body.unNx(),
				new SEQ(new JUMP(begin),
						new LABEL(done))))));
	}

}
