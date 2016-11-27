/*
 * @(#)Cx.java	1.0 2011/01/09
 * Package: Translate
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Translate;

import Temp.Label;
import Temp.Temp;
import Tree.Stm;

/**
 * This class represents a compare.
 * 
 * @author Kiki
 */
abstract class Cx extends Exp {

	@Override
	abstract Stm unCx(Label t, Label f);

	@Override
	Tree.Exp unEx() {
		Temp r = new Temp();
		Label t = new Label();
		Label f = new Label();
		return new Tree.ESEQ(
				new Tree.SEQ(new Tree.MOVE(new Tree.TEMP(r), new Tree.CONST(1)),
				new Tree.SEQ(unCx(t,f),	
				new Tree.SEQ(new Tree.LABEL(f),
				new Tree.SEQ(new Tree.MOVE(new Tree.TEMP(r), new Tree.CONST(0)),
						new Tree.LABEL(t))))),
				new Tree.TEMP(r));
	}

	@Override
	Stm unNx() {
		return new Tree.EXPR(unEx());
	}

}
