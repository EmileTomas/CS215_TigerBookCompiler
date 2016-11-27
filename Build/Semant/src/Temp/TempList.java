/*
 * @(#)TempList.java	1.0 2011/01/09
 * Package: Temp
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Temp;

/**
 * Class for a list of temporaries
 * 
 * @author Kiki
 */
public class TempList {
	
	/**
	 * The temporary
	 */
	public Temp head;
	
	/**
	 * Next temporary
	 */
	public TempList tail;
	
	/**
	 * Constructor : link a temporary and a list
	 * 
	 * @param h
	 * @param t
	 */
	public TempList(Temp h, TempList t) {
		head=h; 
		tail=t;
	}
	
	/**
	 * Constructor :  link two lists
	 * 
	 * @param h
	 * @param t
	 */
	public TempList(TempList h, TempList t)	{
		TempList temp = null;
		while ( h != null ) {
			temp = new TempList(h.head, temp);
			h = h.tail;
		}
		tail = t;
		while ( temp != null ) {
			tail = new TempList(temp.head, tail);
			temp = temp.tail;
		}
		head = tail.head;
		tail = tail.tail;
	}
}

