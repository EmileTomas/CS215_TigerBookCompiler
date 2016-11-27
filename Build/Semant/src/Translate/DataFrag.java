/*
 * @(#)DataFrag.java	1.0 2011/01/09
 * Package: Translate
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Translate;

/**
 * This class stores a string in assembly code.
 * 
 * @author Kiki
 */
public class DataFrag extends Frag{
	
	/**
	 * The string's content
	 */
	public String data;
	
	/**
	 * The string's label
	 */
	public Temp.Label 	label;
	
	/**
	 * Constructor
	 * 
	 * @param l
	 * @param d
	 */
	public DataFrag(Temp.Label l, String d){
		label = l;
		data = d;
	}
}
