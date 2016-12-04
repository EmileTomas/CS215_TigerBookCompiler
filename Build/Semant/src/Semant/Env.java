package Semant;

import Symbol.Table;
import Symbol.Symbol;
import Types.RECORD;
import Types.Type;


class Env {
	
	/**
	 * value environment
	 */
	Table venv;
	
	/**
	 * type environment
	 */
	Table tenv;
	
	/**
	 * Error Message
	 */
	ErrorMsg.ErrorMsg errorMsg;


	/**
	 * Constructor
	 * 
	 * @param err
	 */
	Env(ErrorMsg.ErrorMsg err) {
		errorMsg = err;
		//initialize venv and tenv with predefined identifiers.
		InitVenv();
		InitTenv();
	}
	
	/**
	 * initialize tenv with predefined identifiers.
	 */
	private void InitTenv() {
		tenv = new Table();
		tenv.put(Symbol.symbol("int"), new Types.INT());
		tenv.put(Symbol.symbol("string"), new Types.STRING());
	}
	
	/**
	 * initialize venv with predefined identifiers.
	 */
	private void InitVenv() {

		venv = new Table();
		Symbol name;
		Types.RECORD formals;
		Types.Type result;
		
		Types.Type INT = new Types.INT();
		Types.Type STRING = new Types.STRING();
		Types.Type VOID = new Types.VOID();
		
		
		name = Symbol.symbol("print");
		formals = new Types.RECORD(Symbol.symbol("s"), STRING, null);
		result = VOID;
		SystemFunction(name, formals, result);
		
		name = Symbol.symbol("printi");
		formals = new Types.RECORD(Symbol.symbol("i"), INT, null);
		result = VOID;
		SystemFunction(name, formals, result);	
		
		name = Symbol.symbol("flush");
		formals = null;
		result = VOID;
		SystemFunction(name, formals, result);

		name = Symbol.symbol("getchar");
		formals = null;
		result = STRING;
		SystemFunction(name, formals, result);
		
		name = Symbol.symbol("ord");
		formals = new Types.RECORD(Symbol.symbol("s"), STRING, null);
		result = INT;
		SystemFunction(name, formals, result);
		
		name = Symbol.symbol("chr");
		formals = new Types.RECORD(Symbol.symbol("i"), INT, null);
		result = STRING;
		SystemFunction(name, formals, result);
		
		name = Symbol.symbol("size");
		formals = new Types.RECORD(Symbol.symbol("s"), STRING, null);
		result = INT;
		SystemFunction(name, formals, result);
		
		name = Symbol.symbol("substring");
		formals = new Types.RECORD(Symbol.symbol("s"), STRING, 
				new Types.RECORD(Symbol.symbol("f"), INT,
						new Types.RECORD(Symbol.symbol("n"),INT,null)));
		result = STRING;
		SystemFunction(name, formals, result);
		
		name = Symbol.symbol("concat");
		formals = new Types.RECORD(Symbol.symbol("s1"), STRING,
				new Types.RECORD(Symbol.symbol("s2"), STRING, null));
		result = STRING;
		SystemFunction(name, formals, result);
		
		name = Symbol.symbol("not");
		formals = new Types.RECORD(Symbol.symbol("i"), INT, null);
		result = INT;
		SystemFunction(name, formals, result);
		
		name = Symbol.symbol("exit");
		formals = new Types.RECORD(Symbol.symbol("i"), INT, null);
		result = VOID;
		SystemFunction(name, formals, result);		
	}
	
	/**
	 * Add System function entry
	 * 
	 * @param name
	 * @param formals
	 * @param result
	 */
	private void SystemFunction(Symbol name, RECORD formals, Type result) {
		FunEntry func = new FunEntry(formals, result);
		venv.put(name, func);
	}

}
