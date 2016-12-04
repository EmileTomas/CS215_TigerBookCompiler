package Semant;


class FunEntry extends Entry {
	

	/**
	 * Function formals
	 */
	public Types.RECORD formals;
	
	/**
	 * Type of function result
	 */
	public Types.Type result;
	
	/**
	 * Constructor
	 * @param f
	 * @param r
	 */
	public FunEntry(Types.RECORD f, Types.Type r) {
		formals = f;
		result = r;
	}
}