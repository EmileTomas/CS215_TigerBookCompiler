/*
 * @(#)Level.java	1.0 2011/01/09
 * Package: Translate
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Translate;

/**
 * This class stores a frame's static link and formals
 * 
 * @author Kiki
 */
public class Level {
	
	/**
	 * The parent level
	 */
	public Level parent;
	
	/**
	 * The level's frame : not public!
	 */
	Frame.Frame frame;
	
	/**
	 * The frame's formals
	 */
	public AccessList formals;
	
	/**
	 * Constructor
	 * @param parent parent
	 * @param name name
	 * @param formals
	 */
	public Level(Level parent, Symbol.Symbol name, Util.BoolList formals) {
		this.parent = parent;
		Temp.Label label = new Temp.Label(name);
		this.frame = parent.frame.newFrame(label, new Util.BoolList(true, formals));
		this.formals = allocFormals(this.frame.formals);
	}
	
	/**
	 * Generate accesses for formals
	 * 
	 * @param formals
	 * @return AccessList
	 */
	private AccessList allocFormals(Frame.AccessList formals) {
		if ( formals == null)
			return null;
		return new AccessList(new Access(this, formals.head), allocFormals(formals.tail));
	}
	
	/**
	 * Constructor
	 * 
	 * @param f
	 */
	public Level(Frame.Frame f) {
		frame = f;
	}
	
	/**
	 * Allocate local variable
	 * 
	 * @param escape
	 * @return Access
	 */
	public Access allocLocal(boolean escape) {
		return new Access(this, frame.allocLocal(escape));
	}
	
	/**
	 * Get the static link of the level
	 * The static link allocated at the tail of formals
	 * 
	 * @return access for static link
	 */
	public Access staticLink(){
		AccessList f = formals;
		if ( f == null )
			return null;
		while ( f.tail != null )
			f = f.tail;
		return f.head;
	}
}
