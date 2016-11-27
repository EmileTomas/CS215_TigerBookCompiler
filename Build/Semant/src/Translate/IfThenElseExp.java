/*
 * @(#)IfThenElseExp.java	1.0 2011/01/09
 * Package: Translate
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author 张轩  5080309672 SJTU
 */

package Translate;

import Temp.Label;
import Temp.Temp;
import Tree.CJUMP;
import Tree.CONST;
import Tree.ESEQ;
import Tree.JUMP;
import Tree.LABEL;
import Tree.MOVE;
import Tree.SEQ;
import Tree.Stm;
import Tree.TEMP;

/**
 * Class represents a If expression
 */
class IfThenElseExp extends Exp {
	
	/**
	 * The test expression
	 */
	Exp	testclause;
	
	/**
	 * The then expression
	 */
	Exp	thenclause;
	
	/**
	 * The else expression
	 */
	Exp	elseclause;
	
	/**
	 * Constructor
	 * 
	 * @param testclause
	 * @param thenclause
	 * @param elseclause
	 */
	public IfThenElseExp(Exp testclause, Exp thenclause, Exp elseclause)
	{
		this.testclause = testclause;
		this.thenclause = thenclause;
		this.elseclause = elseclause;
	}


	@Override
	Stm unCx(Label t, Label f) {
		return new CJUMP(CJUMP.NE, unEx(), new CONST(0), t, f);
	}

//	if-else 这样翻译成有返回值的表达式:
//	if (test) goto T else goto F
//	LABEL T: r=e1
//	goto JOIN
//	LABEL F: r=e2
//	LABEL JOIN: return r
	@Override
	Tree.Exp unEx() {
		Label tExp = new Label();
		Label fExp = new Label();
		Label join = new Label();
		Temp result = new Temp();
		return new ESEQ(new SEQ(testclause.unCx(tExp, fExp),
						new SEQ(new LABEL(tExp),
						new SEQ(new MOVE(new TEMP(result), thenclause.unEx()),
						new SEQ(new JUMP(join), 
						new SEQ(new LABEL(fExp), 
						new SEQ(new MOVE(new TEMP(result), elseclause.unEx()), 
								new LABEL(join))))))),
				new TEMP(result));
	}

//	 if (test) goto T else goto JOIN
//	 LABEL T: e1
//	 LABEL JOIN:
//	如果有else 子句
//	 if (test) goto T else goto F
//	 LABEL T: e1
//	 goto JOIN
//	 LABEL F: e2
//	 LABEL JOIN:
	@Override
	Stm unNx() {
		Label tExp = new Label();
		Label fExp = new Label();
		Label join = new Label();
		if ( elseclause == null) 
			return new SEQ(testclause.unCx(tExp, join),
					new SEQ(new LABEL(tExp),
					new SEQ(thenclause.unNx(), 
							new LABEL(join))));
		else return new SEQ(testclause.unCx(tExp, fExp),
					new SEQ(new LABEL(tExp),
					new SEQ(thenclause.unNx(), 
					new SEQ(new JUMP(join),
					new SEQ(new LABEL(fExp),
					new SEQ(elseclause.unNx(), 
							new LABEL(join)))))));
	}

}
