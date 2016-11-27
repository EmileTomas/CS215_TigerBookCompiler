/*
 * @(#)Translate.java	1.0 2011/01/09
 * Package: Translate
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author 张轩  5080309672 SJTU
 */

package Translate;

import java.util.ArrayList;
import java.util.Stack;

import Temp.Label;
import Temp.Temp;
import Tree.BINOP;
import Tree.CALL;
import Tree.CONST;
import Tree.ESEQ;
import Tree.JUMP;
import Tree.MEM;
import Tree.MOVE;
import Tree.NAME;
import Tree.SEQ;
import Tree.Stm;
import Tree.TEMP;

/**
 * This class translates the abstract syntax into IR
 * 
 * @author Kiki
 */
public class Translate {
	
	/**
	 * The corresponding frame
	 */
	public Frame.Frame frame;
	
	/**
	 * Storing the fragments
	 */
	private Frag frags = null;
	
	/**
	 * A stack help to check whether a break expression 
	 * is in a loop. 
	 */
	private Stack<Label> loops = new Stack<Label>();
	
	/**
	 * Constructor
	 * 
	 * @param frame
	 */
	public Translate(Frame.Frame frame) {
		this.frame = frame;
	}
	
	/**
	 * Translate an Integer expression
	 * 
	 * @param value Integer
	 * @return Translate.Exp
	 */
	public Exp transIntExp(int value) {
		return new Ex(new Tree.CONST(value));
	}
	
	/**
	 * Translate a string expression
	 * 
	 * @param value
	 * @return Exp
	 */
	public Exp transStringExp(String value) {
		Label l = new Label();
		addFrag(new DataFrag(l, frame.string(l, value)));
		return new Ex(new Tree.NAME(l));
	}
	
	/**
	 * Translate a nil expression
	 * 
	 * @return Exp
	 */
	public Exp transNilExp() {
		return new Ex(new CONST(0));
	}
	
	/**
	 * Translate a variable expression
	 * 
	 * @param e
	 * @return Exp
	 */
	public Exp transVarExp(Exp e) {
		return e;
	}
	
	/**
	 * Translate a calculation expression
	 * @param op
	 * @param left
	 * @param right
	 * @return Exp
	 */ 
	public Exp transCalcExp(int op, Exp left, Exp right) {
		return new Ex(new BINOP(op, left.unEx(), right.unEx()));
	}
	
	/**
	 * Translate a string comparison expression
	 * @param op
	 * @param left
	 * @param right
	 * @return Exp
	 */
	public Exp transStringRelExp(int op, Exp left, Exp right) {
		Tree.Exp e = frame.externalCall("_stringCompare", new Tree.ExpList(left.unEx(),
				new Tree.ExpList(right.unEx(), null)));
		return new RelCx(op, new Ex(e), new Ex(new CONST(0)));
	}
	
	/**
	 * Translate a non-string comparison expression
	 * @param op
	 * @param left
	 * @param right
	 * @return Exp
	 */
	public Exp transOtherRelExp(int op, Exp left, Exp right) {
		return new RelCx(op, left, right);
	}
	
	/**
	 * Translate an assignment expression
	 * 
	 * @param lvalue
	 * @param ex
	 * @return Exp
	 */
	public Exp transAssignExp(Exp lvalue, Exp ex) {
		return new Nx(new MOVE(lvalue.unEx(), ex.unEx()));
	}
	
	/**
	 * Translate function call expression
	 * 
	 * @param home
	 * @param dest
	 * @param name
	 * @param argValue
	 * @return Exp
	 */
	public Exp transCallExp(Level home, Level dest, Label name, ExpList argValue) {
		/**
		 * if system call , transExtCallExp
		 */
		if ( name.toString().equals("printi")
				|| name.toString().equals("print")
				|| name.toString().equals("flush")
				|| name.toString().equals("getchar")
				|| name.toString().equals("ord")
				|| name.toString().equals("chr")
				|| name.toString().equals("size")
				|| name.toString().equals("substring")
				|| name.toString().equals("concat")
				|| name.toString().equals("exit") )
			return transExtCallExp(home, name, argValue);
		else if ( name.toString().equals("not") )
			return transExtCallExp(home, new Label("_not"), argValue);
		/**
		 * Else...
		 */
		Tree.ExpList args = transExpList(argValue);
		Level l = home;
		Tree.Exp staticLink = new TEMP(l.frame.FP());
		while ( dest.parent != l ) {
			staticLink = l.staticLink().access.exp(staticLink);
			l = l.parent;
		}
		args = new Tree.ExpList(staticLink, args);
		return new Ex(new CALL(new NAME(name), args ));
	}
	
	/**
	 * Translate External Call expression
	 * 
	 * @param home
	 * @param name
	 * @param argValue
	 * @return Exp
	 */
	public Exp transExtCallExp(Level home, Label name, ExpList argValue) {
		return new Ex(home.frame.externalCall( name.toString(), transExpList(argValue)));
	}
	
	/**
	 * Convert Translate.ExpList into Tree.ExpList
	 * 
	 * @param exps
	 * @return Tree.ExpList
	 */
	private Tree.ExpList transExpList(ExpList exps) {
		if ( exps == null )
			return null;
		else
			return new Tree.ExpList(exps.head.unEx(), transExpList(exps.tail));
	}
	
	/**
	 * Translate a record expression
	 * 
	 * @param level
	 * @param field
	 * @return Exp
	 */
	public Exp transRecordExp(Level level, ArrayList<Exp> field) {
		Temp addr = new Temp();
		Tree.Exp alloc = level.frame.externalCall("_allocRecord",
			new Tree.ExpList(
			new CONST((field.size() == 0 ? 1 : field.size())
			* level.frame.wordSize()), null));
		Stm init = transNoOp().unNx(), init0 = init;
		for ( int i = field.size() - 1; i >= 0; --i ) {
			Tree.Exp offset = new BINOP(BINOP.PLUS, new TEMP(addr), new CONST(i
				* level.frame.wordSize()));
			Tree.Exp e = (field.get(i)).unEx();
			if ( init != init0 )
				init = new SEQ(new MOVE(new MEM(offset), e), init);
			else
				init = new MOVE(new MEM(offset), e);
		}
		return new Ex(new ESEQ(new SEQ(new MOVE(new TEMP(addr), alloc), init),
			new TEMP(addr)));
	}
	
	/**
	 * Translate an empty expression
	 * 
	 * @return Exp
	 */
	public Exp transNoOp() {
		return new Ex(new CONST(0));
	}
	
	/**
	 * Translate an array expression
	 * 
	 * @param home
	 * @param init
	 * @param size
	 * @return Exp
	 */
	public Exp transArrayExp(Level home, Exp init, Exp size){
		Tree.Exp alloc = home.frame.externalCall("_initArray", new Tree.ExpList(size
				.unEx(), new Tree.ExpList(init.unEx(), null)));
		return new Ex(alloc);
	}
	
	/**
	 * Translate an if expression 
	 * 
	 * @param test
	 * @param e1
	 * @param e2
	 * @return Exp
	 */
	public Exp transIfExp(Exp test, Exp e1, Exp e2) {
		return new IfThenElseExp(test, e1, e2);
	}
	
	/**
	 * Translate a while expression
	 * @param test
	 * @param body
	 * @param done
	 * @return Exp
	 */
	public Exp transWhileExp(Exp test, Exp body, Label done) {
		return new WhileExp(test, body, done);
	}
	
	/**
	 * Translate a for expression
	 * @param home
	 * @param var
	 * @param low
	 * @param high
	 * @param body
	 * @param done
	 * @return Exp
	 */
	public Exp transForExp(Level home, Access var, Exp low, Exp high,
							Exp body, Label done) {
		return new ForExp(home, var, low, high, body, done);
	}
	
	/**
	 * Translate a break expression
	 * 
	 * @return Exp
	 */
	public Exp transBreakExp() {
		return new Nx(new JUMP(getLoopLabel()));
	}
	
	/**
	 * Translate a simple variable
	 * @param var
	 * @param home
	 * @return Exp
	 */
	public Exp transSimpleVar(Access var, Level home) {
		Tree.Exp r = new TEMP(home.frame.FP());
		Level l = home;
		while ( l != var.home ) {
			r = l.staticLink().access.exp(r);
			l = l.parent;
		}
		return new Ex(var.access.exp(r));
	}
	
	/**
	 * Translate a subscript variable
	 * @param var
	 * @param index
	 * @return Exp
	 */
	public Exp transSubscriptVar(Exp var, Exp index) {
		Tree.Exp addr = var.unEx();
		Tree.Exp offset = new BINOP(BINOP.MUL, index.unEx(), new CONST(frame
			.wordSize()));
		return new Ex(new MEM(new BINOP(BINOP.PLUS, addr, offset)));
	}
	
	/**
	 * Translate a field variable
	 * @param var
	 * @param num
	 * @return Exp
	 */
	public Exp transFieldVar(Exp var, int num) {
		Tree.Exp addr = var.unEx();
		Tree.Exp offset = new CONST(num * frame.wordSize());
		return new Ex(new MEM(new BINOP(BINOP.PLUS, addr, offset)));
	}
	
	/**
	 * Combine two statement
	 * @param e1
	 * @param e2
	 * @return Exp
	 */
	public Exp combine2Stm(Exp e1, Exp e2) {
		if ( e1 == null )
			return new Nx(e2.unNx());
		else if ( e2 == null )
			return new Nx(e1.unNx());
		else
			return new Nx(new SEQ(e1.unNx(), e2.unNx()));
	}
	/**
	 * Combine two expression
	 * @param e1
	 * @param e2
	 * @return Exp
	 */
	public Exp combine2Exp(Exp e1, Exp e2) {
		if ( e1 == null )
			return new Ex(e2.unEx());
		else
			return new Ex(new ESEQ(e1.unNx(), e2.unEx()));
	}
	
	
	/**
	 * Translate a type declaration
	 * 
	 * @return Exp
	 */
	public Exp transTypeDec() {
		return transNoOp();
	}
	
	/**
	 * Translate a function declaration
	 * 
	 * @return Exp
	 */
	public Exp transFunctionDec() {
		return transNoOp();
	}
	
	/**
	 * Indicates an error
	 * 
	 * @return Exp
	 */
	public Exp Error() {
		return new Ex(new CONST(0));
	}
	
	/**
	 * Add a fragment to the result of translation
	 * 
	 * @param frag
	 */
	public void addFrag(Frag frag) {
		frag.next = frags;
		frags = frag;
	}
	
	/**
	 * Get the translation result
	 * 
	 * @return Frag
	 */
	public Frag getResult(){
		return frags;
	}
	
	/**
	 * Enter in a loop; produce a "done" label;
	 */
	public void newLoop() {
		loops.push(new Label());
	}
	
	/**
	 * Exit a loop; delete the label;
	 */
	public void exitLoop() {
		loops.pop();
	}
	
	/**
	 * Check a break expression is in loop or not.
	 * @return Boolean
	 */
	public boolean isInLoop() {
		return !loops.empty();
	}
	
	/**
	 * Get the "done" label of a loop expression
	 * @return Temp.Label
	 */
	public Label getLoopLabel() {
		return loops.lastElement();
	}
	
	/**
	 * Translate a function call
	 * 
	 * @param level
	 * @param body
	 * @param returnValue
	 */
	public void procEntryExit(Level level, Exp body, boolean returnValue) {
		Tree.Stm b = null;
		if (returnValue)
			b = new Tree.MOVE(new Tree.TEMP(level.frame.RV()), body.unEx());
		else
			b = body.unNx();
		b = level.frame.procEntryExit1(b);
		addFrag(new ProcFrag(b, level.frame)); //加入函数段
	}
}
