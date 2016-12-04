package Semant;

import Translate.Exp;
import Types.Type;

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
