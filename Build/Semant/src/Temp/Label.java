/*
 * @(#)Label .java	1.0 2011/01/09
 * Package: Temp
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Temp;

/**
 * A Label represents an address in assembly language.
 * 
 * @author Kiki
 */
public class Label {
	
	/**
	 * Label name
	 */
	private String name;
	
	/**
	 * Label count
	 */
	private static int count;
	
	/**
	 * a printable representation of the label, for use in assembly 
	 * language output.
	 * 
	 * @return String
	 */
	public String toString() {
		return name;
	}
	
	/**
	 * Makes a new label that prints as "name".
	 * Warning: avoid repeated calls to <tt>new Label(s)</tt> with
	 * the same name <tt>s</tt>.
	 */
	public Label(String n) {
		name=n;
	}
	
	/**
	 * Makes a new label with an arbitrary name.
	 */
	public Label() {
		this("L" + count++);
	}
	
	/**
	 * Makes a new label whose name is the same as a symbol.
	 */
	public Label(Symbol.Symbol s) {
		this(s.toString());
	}
}
