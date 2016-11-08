/*
 * @(#)RecordTy.java	1.0 2011/01/09
 * Package: Absyn
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Absyn;

/**
 * Class representing a type of record.
 * 
 * @author Kiki
 */
public class RecordTy extends Ty {
	
	/**
	 * Fields of record
	 */
	public FieldList fields;
	
	/**
	 * Constructor
	 * @param p position
	 * @param f fields
	 */
	public RecordTy(int p, FieldList f) {
		pos=p;
		fields=f;
	}
}   
