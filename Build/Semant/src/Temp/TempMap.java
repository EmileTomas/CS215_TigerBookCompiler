/*
 * @(#)TempMap.java	1.0 2011/01/09
 * Package: Temp
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Temp;

/**
 * Interface for a TempMap
 * 
 * @author Kiki
 */
public interface TempMap {
	
	/**
	 * Maps a temporary to a register
	 * 
	 * @param t
	 * @return String
	 */
	public String tempMap(Temp t);
}

