/*
 * @(#)CombineMap.java	1.0 2011/01/09
 * Package: Temp
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Temp;

/**
 * Class to combine two maps
 * 
 * @author Kiki
 */
public class CombineMap implements TempMap {
	
	TempMap tmap1, tmap2;
	
	public String tempMap(Temp t) {
	   String s = tmap1.tempMap(t);
	   if (s!=null) return s;
	   return tmap2.tempMap(t);
	}

	public CombineMap(TempMap t1, TempMap t2) {
	   tmap1=t1; tmap2=t2;
	}
}
