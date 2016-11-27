/*
 * @(#)Access.java	1.0 2011/01/09
 * Package: Translate
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Translate;

/**
 * This class represents a variable.
 * 
 * @author Kiki
 */
public class Access {
	
	/**
	 * The access's level
	 */
	Level home;
	
	/**
	 * The corresponding access in frame
	 */
	Frame.Access access;
	
	/**
	 * Constructor
	 * 
	 * @param h
	 * @param a
	 */
	Access(Level h, Frame.Access a) {
		home = h;
		access = a;
	}
	
}
