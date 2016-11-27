/*
 * @(#)DefaultMap .java	1.0 2011/01/09
 * Package: Temp
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Temp;

/**
 * Class for default temp map
 * 
 * @author Kiki
 */
public class DefaultMap implements TempMap {
	
	/**
	 * Return the string of temp
	 */
	public String tempMap(Temp t) {
	   return t.toString();
	}
	
	/**
	 * Constructor
	 */
	public DefaultMap() {
		
	}
}
