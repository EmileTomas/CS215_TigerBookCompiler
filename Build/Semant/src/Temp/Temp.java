/*
 * @(#)Temp.java	1.0 2011/01/09
 * Package: Temp
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Temp;

/**
 * Represents a temporary
 * 
 * @author Kiki
 */
public class Temp {
	
	/**
	 * Count the total temporaries
	 */
	private static int count;
	
	/**
	 * The number of the current temporary
	 */
	private int num;
	
	@Override
	public String toString() {
		return "t" + num;
	}
	
	/**
	 * Constructor
	 */
	public Temp() { 
		num=count++;
	}
}

