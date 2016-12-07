package Semant;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import Absyn.FieldList;
import Types.Type;
import Types.VOID;

public class Semant {

	/**
	 * Environment
	 */
	Env env;
	int loopCount=0;

	/**
	 * Translate
	 */

	/**
	 * System Type
	 */
	Types.Type INT = new Types.INT();
	Types.Type STRING = new Types.STRING();
	Types.Type NIL = new Types.NIL();
	Types.Type VOID = new Types.VOID();

	/**
	 * Public Construction Method
	 * @param err ErrorMsg
	 */
	public Semant(ErrorMsg.ErrorMsg err) {
		env = new Env(err);
	}

	/**
	 * Constructor
	 * @param e Environment
	 */

	Semant(Env e) {
		env = e;
	}

	/**
	 * Translate Program
	 * @param exp Program Expression
	 * @throws NullPointerException Type Checking Error
	 */

	public void transProg(Absyn.Exp exp) throws NullPointerException {
		ExpTy body = transExp(exp);
	}

	/**
	 * Print error on screen
	 * @param pos position
	 * @param str String
	 */
	public void error(int pos, String str) {
		env.errorMsg.error(pos, str);
	}

	/**
	 * Translate Integer expression
	 * @param e Absyn.IntExp
	 * @return ExpTy
	 */
	ExpTy transExp(Absyn.IntExp e) {
		return new ExpTy(null, INT);
	}

	/**
	 * Translate string expression
	 * @param e Absyn.StringExp
	 * @return ExpTy
	 */
	ExpTy transExp(Absyn.StringExp e) {
		return new ExpTy(null, STRING);
	}

	/**
	 * Translate Nil expression
	 * @param e Absyn.NilExp
	 * @return ExpTy
	 */
	ExpTy transExp(Absyn.NilExp e) {
		return new ExpTy(null, NIL);
	}

	/**
	 * Check whether the expression type is Integer
	 * @param et ExpTy
	 * @param pos position
	 * @return Translate.Exp
	 */
	private Translate.Exp checkInt(ExpTy et, int pos) {
		if ( et.ty instanceof Types.INT )
			return et.exp;
		else {
			error(pos, "Integer Required!");
			return et.exp;
		}
	}

	/**
	 * Translate operator expression
	 * @param e Absyn.OpExp
	 * @return ExpTy
	 */
	ExpTy transExp(Absyn.OpExp e) {
		ExpTy left = transExp(e.left);
		ExpTy right = transExp(e.right);
		Types.Type tleft = left.ty.actual();
		Types.Type tright = right.ty.actual();
		switch (e.oper) {
		case Absyn.OpExp.PLUS:
		case Absyn.OpExp.MINUS:
		case Absyn.OpExp.MUL:
		case Absyn.OpExp.DIV:
			if ( tleft instanceof Types.INT && tright instanceof Types.INT )
				return new ExpTy(null, INT);
			else {
				error(e.pos, "Integer Required!");
				return new ExpTy(null, INT);
			}
		case Absyn.OpExp.GE:
		case Absyn.OpExp.GT:
		case Absyn.OpExp.LE:
		case Absyn.OpExp.LT:
			if ( tleft instanceof Types.INT && tright instanceof Types.INT )
				return new ExpTy(null, INT);
			else if ( tleft instanceof Types.STRING && tright instanceof Types.STRING )
				return new ExpTy(null, INT);
			else {
				error(e.pos, "Operands must both be INT or STRING!");
				return new ExpTy(null, INT);
			}
		case Absyn.OpExp.EQ:
		case Absyn.OpExp.NE:
			if ( tleft instanceof Types.INT && tright instanceof Types.INT
					|| tleft instanceof Types.RECORD && tright instanceof Types.RECORD
					|| tleft instanceof Types.ARRAY && tright instanceof Types.ARRAY
					|| tleft instanceof Types.NIL && tright instanceof Types.RECORD
					|| tleft instanceof Types.RECORD && tright instanceof Types.NIL )
				return new ExpTy(null, INT);
			else if ( tleft instanceof Types.STRING && tright instanceof Types.STRING ) {
				return new ExpTy(null, INT);
			}
			else {
				error(e.pos, "Unbalanced Compare!");
				return new ExpTy(null, INT);
			}
		}
		return null;
	}

	/**
	 * Translate Let expression
	 * @param e Absyn.LetExp
	 * @return ExpTy
	 */
	ExpTy transExp(Absyn.LetExp e) {
		env.venv.beginScope();
		env.tenv.beginScope();
		Translate.Exp ex = null;
		for (Absyn.DecList p = e.decs; p != null; p = p.tail)
			transDec(p.head);
		ExpTy et = transExp(e.body);
		env.venv.endScope();
		env.tenv.endScope();
		if ( et.ty.actual().coerceTo(VOID) )
			return new ExpTy(null, VOID);
		return new ExpTy(null, et.ty.actual());
	}

	/**
	 * Translate If expression
	 * @param e Absyn.IfExp
	 * @return ExpTy
	 */
	ExpTy transExp(Absyn.IfExp e) {
		ExpTy test = transExp(e.test);
		checkInt(test, e.test.pos);
		ExpTy etThen = transExp(e.thenclause);
		if ( e.elseclause == null ) {
			if ( !(etThen.ty.actual() instanceof Types.NIL )
					&& !(etThen.ty.actual() instanceof Types.VOID)) {
				error(e.thenclause.pos, "If-then Returns No Value!");
				return new ExpTy(null, VOID);
			}
			else
				return new ExpTy(null, VOID);
		}
		else {
			ExpTy etElse = transExp(e.elseclause);
			if ( etThen.ty.actual().coerceTo(etElse.ty.actual()) )
				return new ExpTy(null, etElse.ty.actual());
			else if ( etElse.ty.actual().coerceTo(etThen.ty.actual()) )
				return new ExpTy(null, etThen.ty.actual());
			else {
				error(e.pos, "Same Return Type Required!");
				return new ExpTy(null, etThen.ty.actual());
			}
		}
	}

	/**
	 * Translate variable expression
	 * @param e Absyn.VarExp
	 * @return ExpTy
	 */
	ExpTy transExp(Absyn.VarExp e) {
		return transVar(e.var);
	}

	/**
	 * Translate expression sequence
	 * @param e Absyn.SeqExp
	 * @return ExpTy
	 */
	ExpTy transExp(Absyn.SeqExp e) {
		if ( e.list == null )
			return new ExpTy(null, VOID);
		ExpTy result = null;
		Absyn.ExpList exp;
		for (exp = e.list; exp.tail != null; exp = exp.tail) {
			 transExp(exp.head);
		}
		result = transExp(exp.head);
		return new ExpTy(null, result.ty.actual());
	}

	/**
	 * Translate array expression
	 * @param e Absyn.ArrayExp
	 * @return ExpTy
	 */
	ExpTy transExp(Absyn.ArrayExp e) {
		Types.Type type = (Types.Type)env.tenv.get(e.typ);
		if ( type == null ) {
			error(e.pos, "Undefined Array Type!");
			return new ExpTy(null, new Types.ARRAY(INT));
		}
		if ( type.actual() instanceof Types.ARRAY ) {
			ExpTy etSize = transExp(e.size);
			checkInt(etSize, e.size.pos);
			ExpTy etInit = transExp(e.init);
			Types.Type elemTy = ((Types.ARRAY)type.actual()).element.actual();
			if ( !elemTy.coerceTo(etInit.ty.actual()) ) {
				error(e.init.pos, "Mismatching Array Type!");
			}
			return new ExpTy(null, (Types.ARRAY)type.actual());
		}
		else {
			error(e.pos, "Undefined Array Type : "+e.typ);
			return new ExpTy(null, new Types.ARRAY(INT));
		}
	}

	/**
	 * Translate function call expression
	 * @param e Absyn.CallExp
	 * @return ExpTy
	 */
	ExpTy transExp(Absyn.CallExp e) {
		Entry ent = (Entry)env.venv.get(e.func);
		if ( ent instanceof FunEntry )
		{
			FunEntry en = (FunEntry)ent;
			if ( en == null ) {
				error(e.pos, "Undefined Function : "+e.func);
				return new ExpTy(null, INT);
			}
			else {
				Types.RECORD rec = ((FunEntry) env.venv.get(e.func)).formals;
				Absyn.ExpList args = e.args;
				Stack<Translate.Exp> stack = new Stack<Translate.Exp>();
				for( ; rec != null; rec = rec.tail, args = args.tail)
				{
					if ( args == null ) {
						error(e.pos, "Mismatching Function Args Number!");
						return new ExpTy(null, en.result);
					}
					ExpTy et = transExp(args.head);
					if ( !et.ty.coerceTo(rec.fieldType) ) {
						error(args.head.pos, "Mismatching Function Call Type!");
						return new ExpTy(null, en.result);
					}
					stack.push(et.exp);
				}
				while ( stack.size() != 0) {
					Translate.Exp exp = stack.pop();
				}
				if ( args != null )
					error(e.pos, "Mismatching Function Args Number!");
				return new ExpTy(null,en.result);
			}
		}
		else {
			error(e.pos, "Undefined Function : "+e.func);
			return new ExpTy(null, INT);
		}
	}

	/**
	 * Translate assign expression
	 * @param e Absyn.AssignExp
	 * @return ExpTy
	 */
	ExpTy transExp(Absyn.AssignExp e) {
		ExpTy etVar = transVar(e.var);
		ExpTy etExp = transExp(e.exp);
		if ( !etVar.ty.actual().coerceTo(etExp.ty.actual()) ) {
			error(e.pos, "Unmatched Assign Types!");
		}
		return new ExpTy(null, VOID);
	}

	/**
	 * Translate While expression
	 * @param e Absyn.WhileExp
	 * @return ExpTy
	 */

	ExpTy transExp(Absyn.WhileExp e) {
		ExpTy etTest = transExp(e.test);
		checkInt(etTest, e.test.pos);
		++loopCount;
		ExpTy etBody = transExp(e.body);
		if ( !(etBody.ty.actual().coerceTo(VOID)) ) {
			error(e.body.pos, "While Expression Returns No Value!");
		}
		--loopCount;
		return new ExpTy(null, VOID);
	}

	/**
	 * Translate For expression
	 * @param e Absyn.ForExp
	 * @return ExpTy
	 */
	ExpTy transExp(Absyn.ForExp e) {
		++loopCount;
		env.venv.beginScope();
		ExpTy etLow = transExp(e.var.init);
		checkInt(etLow, e.var.init.pos);
		ExpTy etHi = transExp(e.hi);
		checkInt(etHi, e.hi.pos);
		env.venv.put(e.var.name, new VarEntry(etLow.ty.actual()));
		ExpTy etBody = transExp(e.body);
		Types.Type type;
		if ( etBody == null )
			type = VOID;
		else
			type = etBody.ty.actual();
		if ( !(type.actual().coerceTo(VOID)) ) {
			error(e.body.pos, "For Expression Returns No Value!");
		}
		env.venv.endScope();
		--loopCount;
		return new ExpTy(null, VOID);
	}

	/**
	 * Translate break expression
	 * @param e Absyn.BreakExp
	 * @return ExpTy
	 */
	ExpTy transExp(Absyn.BreakExp e) {
		if(loopCount==0) {
			error(e.pos, "BREAK must be in a LOOP!");
			return new ExpTy(null, VOID);
		}
		return new ExpTy(null, VOID);
	}

	/**
	 * Translate record expression
	 * @param e Absyn.RecordExp
	 * @return ExpTy
	 */
	ExpTy transExp(Absyn.RecordExp e) {
		Types.Type type = (Types.Type) env.tenv.get(e.typ);
		if(type == null) {
			error(e.pos, "Undefined Type : "+e.typ);
			return new ExpTy(null, INT);
		}
		else if ( !(type.actual() instanceof Types.RECORD) ) {
			error(e.pos, "Record Type Required!");
			return new ExpTy(null, INT);
		}
		else{
			Types.RECORD rec = (Types.RECORD)type.actual();
			ArrayList<Translate.Exp> arraylist = new ArrayList<Translate.Exp>();
			Absyn.FieldExpList field = e.fields;
			for ( ; rec != null; rec = rec.tail, field = field.tail ) {
				if ( field == null ) {
					error(e.pos, "Mismatching Type Fields!");
					return new ExpTy(null, type);
				}
				if ( field.name != rec.fieldName ) {
					error(field.pos, "Mismatching Record Variable!");
					return new ExpTy(null, type);
				}
				ExpTy etField = transExp(field.init);
				arraylist.add(etField.exp);
				if ( !etField.ty.actual().coerceTo(rec.fieldType.actual()) ) {
					error(field.pos, "Mismatching Variable Type!");
					return new ExpTy(null, type);
				}
			}
			if ( field != null ) {
				error(field.pos, "Mismatching Record Variable!");
				return new ExpTy(null, type);
			}
			return new ExpTy(null, type);
		}
	}

	/**
	 * Translate Expression
	 * @param e Absyn.Exp
	 * @return ExpTy
	 */
	ExpTy transExp(Absyn.Exp e) {
		if (e instanceof Absyn.IntExp)
			return transExp((Absyn.IntExp) e);
		if (e instanceof Absyn.StringExp)
			return transExp((Absyn.StringExp) e);
		if (e instanceof Absyn.OpExp)
			return transExp((Absyn.OpExp) e);
		if (e instanceof Absyn.VarExp)
			return transExp((Absyn.VarExp) e);
		if (e instanceof Absyn.CallExp)
			return transExp((Absyn.CallExp) e);
		if (e instanceof Absyn.ArrayExp)
			return transExp((Absyn.ArrayExp) e);
		if (e instanceof Absyn.IfExp)
			return transExp((Absyn.IfExp) e);
		if (e instanceof Absyn.LetExp)
			return transExp((Absyn.LetExp) e);
		if (e instanceof Absyn.SeqExp)
			return transExp((Absyn.SeqExp) e);
		if (e instanceof Absyn.AssignExp)
			return transExp((Absyn.AssignExp) e);
		if (e instanceof Absyn.WhileExp)
			return transExp((Absyn.WhileExp) e);
		if (e instanceof Absyn.ForExp)
			return transExp((Absyn.ForExp) e);
		if (e instanceof Absyn.BreakExp)
			return transExp((Absyn.BreakExp) e);
		if (e instanceof Absyn.RecordExp)
			return transExp((Absyn.RecordExp) e);
		if (e instanceof Absyn.NilExp)
			return transExp((Absyn.NilExp) e);
		throw new Error("transExp!");
	}

	/**
	 * Translate simple variable
	 * @param v Absyn.SimpleVar
	 * @return ExpTy
	 */
	ExpTy transVar(Absyn.SimpleVar v) {
		Entry x = (Entry) env.venv.get(v.name);
		if (x instanceof VarEntry) {
			VarEntry ent = (VarEntry)x;
			return new ExpTy(null, ent.ty);
		}
		else {
			error(v.pos, "Undeclared Variable : "+v.name);
			return new ExpTy(null, INT); //anything will do!
		}
	}

	/**
	 * Translate field variable
	 * @param v Absyn.FieldVar
	 * @return ExpTy
	 */
	ExpTy transVar(Absyn.FieldVar v) {
		ExpTy et = transVar(v.var);
		if ( !(et.ty.actual() instanceof Types.RECORD) ) {
			error(v.var.pos, "RECORD type Required!");
			return new ExpTy(null, INT);
		}
		Types.RECORD rec = (Types.RECORD)et.ty.actual();
		for ( int count = 0 ; rec != null; rec = rec.tail )
		{
			if (rec.fieldName == v.field)
				return new ExpTy(null, rec.fieldType);
			count++;
		}
		error(v.pos, "Undeclared Variable : "+v.field);
		return new ExpTy(null, INT);
	}

	/**
	 * Translate subscript variable
	 * @param v Absyn.SubscriptVar
	 * @return ExpTy
	 */
	ExpTy transVar(Absyn.SubscriptVar v) {
		ExpTy et = transVar(v.var);
		if ( !(et.ty.actual() instanceof Types.ARRAY) ) {
			error(v.var.pos, "ARRAY type Required!");
			return new ExpTy(null, INT);
		}
		ExpTy exp = transExp(v.index);
		if ( !(exp.ty instanceof Types.INT) ) {
			error(v.index.pos, "Integer Required!");
			return new ExpTy(null, INT);
		}
		return new ExpTy(null, ((Types.ARRAY) et.ty.actual()).element.actual());
	}

	/**
	 * Translate Variable
	 * @param v Absyn.Var
	 * @return ExpTy
	 */
	ExpTy transVar(Absyn.Var v) {
		if (v instanceof Absyn.SimpleVar)
			return transVar((Absyn.SimpleVar) v);
		if (v instanceof Absyn.FieldVar)
			return transVar((Absyn.FieldVar) v);
		if (v instanceof Absyn.SubscriptVar)
			return transVar((Absyn.SubscriptVar) v);
		throw new Error("transVar");
	}

	/**
	 * Translate Variable Declaration
	 * @param d Absyn.VarDec
	 * @return Translate.Exp
	 */
	Translate.Exp transDec(Absyn.VarDec d) {
		ExpTy etInit = transExp(d.init);
		if (etInit.ty.actual().coerceTo(VOID)) {
			error(d.init.pos, "Mismatching Variable Declaration Type!");
			return null;
		}
		if (d.typ == null) {
			if (etInit.ty.actual() instanceof Types.NIL) {
				error(d.init.pos, "Illegal NIL expression!");
				return null;
			}
			env.venv.put(d.name, new VarEntry(etInit.ty.actual()));
			return null;
		}
		else {
			Types.Type type = (Types.Type) env.tenv.get(d.typ.name);
			if (type == null) {
				error(d.typ.pos, "Undefined Type!");
				return null;
			}
			if (!etInit.ty.actual().coerceTo(type.actual())) {
				error(d.pos, "Unmatching Types!");
				return null;
			}
			else {
				env.venv.put(d.name, new VarEntry(type.actual()));
				return null;
			}
		}
	}

	/**
	 * Translate Type Declaration
	 * @param d Absyn.TypeDec
	 * @return Translate.Exp
	 */
	Translate.Exp transDec(Absyn.TypeDec d) {
		java.util.HashSet<Symbol.Symbol> set = new java.util.HashSet<Symbol.Symbol>();
		for (Absyn.TypeDec td = d; td != null; td = td.next) {
			if ( !set.add(td.name) ) {
				error(td.pos, "Redefined Type Name in a recursive declaration!");
				return null;
			}
			env.tenv.put(td.name, new Types.NAME(td.name));
		}
		for (Absyn.TypeDec td = d; td != null; td = td.next) {
			Types.NAME t = (Types.NAME) env.tenv.get(td.name);
			t.bind(transTy(td.ty));
		}
		for (Absyn.TypeDec td = d; td != null; td = td.next) {
			Types.NAME type = (Types.NAME) env.tenv.get(td.name);
			if ( type.isLoop() ) {
				error(td.pos, "Loop Type Definition!");
				return null;
			}
		}
		return null;
	}
	/**
	 * Translate Function Declaration
	 * @param d Absyn.FunctionDec
	 * @return Translate.Exp
	 */
	Translate.Exp transDec(Absyn.FunctionDec d) {
		java.util.HashSet<Symbol.Symbol> set = new java.util.HashSet<Symbol.Symbol>();
		Types.Type result;
		Types.RECORD formals;
		for(Absyn.FunctionDec fd = d; fd != null; fd = fd.next) {
			if ( !set.add(fd.name) ) {
				error(fd.pos, "Redefined Function Name in a recursive declaration!");
				return null;
			}
			formals = transTypeFields(fd.params);
			result = (fd.result == null? VOID : transTy(fd.result).actual());
			env.venv.put(fd.name, new FunEntry(formals, result));
		}
		for(Absyn.FunctionDec fd = d; fd != null; fd = fd.next) {
			FunEntry fun = (FunEntry) env.venv.get(fd.name);	//save it because params may cover it
			env.venv.beginScope();
			LinkedList<FieldList> stack = new LinkedList<FieldList>();
			for ( FieldList field = fd.params; field != null; field = field.tail )
			{
				stack.addLast(field);
			}
			while ( stack.size() != 0 )
			{
				FieldList field = stack.removeLast();
				Type type = (Type) env.tenv.get(field.typ);
				VarEntry entry = new VarEntry(type);
				env.venv.put(field.name, entry);
			}

			Semant newsem = new Semant(env);
			ExpTy et = newsem.transExp(fd.body);
			if (fd.result == null && !et.ty.actual().coerceTo(VOID)) {
				error(fd.pos, "Procedure Returns No Value!");
				return null;
			}
			else if (!et.ty.actual().coerceTo(fun.result.actual())) {
				error(fd.result.pos, "Unmatched Return Type!");
				return null;
			}
			env.venv.endScope();
		}
		return null;
	}

	/**
	 * Translate Function parameters to Types.RECORD
	 * @param params Absyn.FieldList
	 * @return Types.RECORD
	 */
	private Types.RECORD transTypeFields(Absyn.FieldList params) {
		if (params != null)
			return new Types.RECORD(params.name, ((Types.Type)env.tenv.get(params.typ)).actual(), transTypeFields(params.tail));
		else
			return null;
	}

	/**
	 * Translate Function parameters to Util.BoolList
	 * @param params Absyn.FieldList
	 * @return Util.BoolList
	 */
	private Util.BoolList transFormals(Absyn.FieldList params) {
		if ( params == null )
			return null;
		return new Util.BoolList(params.escape, transFormals(params.tail));
	}

	/**
	 * Translate Declaration
	 * @param d Absyn.Dec
	 * @return Translate.Exp
	 */
	Translate.Exp transDec(Absyn.Dec d) {
		if (d instanceof Absyn.VarDec)
			return transDec((Absyn.VarDec) d);
		if (d instanceof Absyn.TypeDec)
			return transDec((Absyn.TypeDec) d);
		if (d instanceof Absyn.FunctionDec)
			return transDec((Absyn.FunctionDec) d);
		throw new Error("transDec");
	}

	/**
	 * Translate RecordTy to Types.RECORD
	 * @param t Absyn.RecordTy
	 * @return Types.RECORD
	 */
	Types.RECORD transTy(Absyn.RecordTy t) {
		if(t.fields == null)
			return null;
		else {
			Types.Type type = (Types.Type)env.tenv.get(t.fields.typ);
			if(type == null) {
				error(t.pos, "Undefined type: " + t.fields.typ);
				return null;
			}
			return new Types.RECORD(t.fields.name, type ,transTy(new Absyn.RecordTy(t.fields.pos, t.fields.tail)));
		}
	}

	/**
	 * Translate NameTy to Types.Type
	 * @param t Absyn.NameTy
	 * @return Types.Type
	 */
	Types.Type transTy(Absyn.NameTy t) {
		Types.Type type = (Types.Type) env.tenv.get(t.name);
		if ( type == null )
			error(t.pos, "Undefined type: " + t.name);
		return  type;
	}

	/**
	 * Translate ArrayTy to Types.Array
	 * @param t Absyn.ArrayTy
	 * @return Types.Array
	 */
	Types.ARRAY transTy(Absyn.ArrayTy t) {
		Types.Type type = (Types.Type) env.tenv.get(t.typ);
		if ( type == null ) {
			error(t.pos, "Undefined type: " + t.typ);
			return null;
		}
		return new Types.ARRAY(type);
	}

	/**
	 * The transTy function translates type expressions as found in the abstract
	 * syntax (Absyn.Ty) to the digested type descriptions that we will put into
	 * environment (Types.Type).
	 * @param t Absyn.Ty
	 * @return Types.Type
	 */
	Types.Type transTy(Absyn.Ty t) {
		if (t instanceof Absyn.RecordTy)
			return transTy((Absyn.RecordTy) t);
		if (t instanceof Absyn.NameTy)
			return transTy((Absyn.NameTy) t);
		if (t instanceof Absyn.ArrayTy)
			return transTy((Absyn.ArrayTy) t);
		throw new Error("transTy");
	}
}