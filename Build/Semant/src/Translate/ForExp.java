/*
 * @(#)ForExp.java	1.0 2011/01/09
 * Package: Translate
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Translate;

import Temp.Label;
import Tree.BINOP;
import Tree.CJUMP;
import Tree.CONST;
import Tree.JUMP;
import Tree.LABEL;
import Tree.MOVE;
import Tree.SEQ;
import Tree.Stm;
import Tree.TEMP;

/**
 * Class represents a For expression
 * 
 * @author Kiki
 */
public class ForExp extends Exp {

	/**
	 * The expression's level
	 */
	Level	level;
	
	/**
	 * The expression's loop variable
	 */
	Access	var;
	
	/**
	 * The for expression's low field
	 */
	Exp		low;
	
	/**
	 * The for expression's high field
	 */
	Exp		high;
	
	/**
	 * The for expression's body
	 */
	Exp		body;
	
	/**
	 * The for expression's finish label
	 */
	Label	done;
	
	/**
	 * Constructor
	 * 
	 * @param level
	 * @param var
	 * @param low
	 * @param high
	 * @param body
	 * @param done
	 */
	public ForExp(Level level, Access var, Exp low, Exp high, Exp body,
		Label done)
	{
		this.level = level;
		this.var = var;
		this.low = low;
		this.high = high;
		this.body = body;
		this.done = done;
	}
	
	/**
	 * While Expression has only on exit
	 */
	@Override
	public Stm unCx(Label t, Label f) {
		System.err.println("ForExp.unCx()");
		return null;
	}
	
	/**
	 * For Expression has no return value
	 */
	@Override
	public Tree.Exp unEx() {
		System.err.println("ForExp.unEx()");
		return null;
	}

	@Override
	public Stm unNx() {
		/*
		MOVE VAR, LOW
		MOVE LIMIT, HIGH
		if (VAR<=LIMIT) goto BEGIN else goto DONE
		LABEL BEGIN:
		body
		if (VAR<LIMIT) goto GOON else goto DONE
		LABEL GOON:
		VAR=VAR+1
		GOTO BEGIN:
		LABEL DONE:
		*/
		Access limit = level.allocLocal(true);
		Label begin = new Label();
		Label goon = new Label();
		return  new SEQ(new MOVE(var.access.exp(new TEMP(level.frame.FP())), low.unEx()), 
				new SEQ(new MOVE(limit.access.exp(new TEMP(level.frame.FP())), high.unEx()), 
				new SEQ(new CJUMP(CJUMP.LE, var.access.exp(new TEMP(level.frame.FP())), 
						limit.access.exp(new TEMP(level.frame.FP())), begin, done), 
				new SEQ(new LABEL(begin), new SEQ(body.unNx(), 
				new SEQ(new CJUMP(CJUMP.LT, var.access.exp(new TEMP(level.frame.FP())),
						limit.access.exp(new TEMP(level.frame.FP())), goon, done), 
				new	SEQ(new LABEL(goon), 
				new SEQ(new MOVE( var.access.exp(new TEMP(level.frame.FP())), 
						new BINOP(BINOP.PLUS, var.access.exp(new TEMP(level.frame.FP())), 
								new CONST(1))), 
				new SEQ(new JUMP(begin),
						new LABEL(done))))))))));
	}
}

