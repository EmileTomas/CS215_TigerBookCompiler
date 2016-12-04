package Semant;


class VarEntry extends Entry {
	/**
	 * Variable type
	 */
	Types.Type ty;

	/**
	 * Constructor
	 * @param t
	 */
	VarEntry(Types.Type t) {
		ty = t;
	}
}
