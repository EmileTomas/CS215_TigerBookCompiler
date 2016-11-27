/*
 * @(#)LabelList.java	1.0 2011/01/09
 * Package: Temp
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Temp;

/**
 * Class for label list
 * 
 * @author Kiki
 */
public class LabelList {
	public Label head;
	public LabelList tail;
	public LabelList(Label h, LabelList t) {head=h; tail=t;}
}

