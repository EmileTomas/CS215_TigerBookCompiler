/*
 * @(#)Lex.java	1.0 2011/01/09
 * Package: Parse
 * Copyright 2011 Kiki Tiger Compiler, Inc. All rights reserved.
 * @author ÕÅÐù  5080309672 SJTU
 */

package Parse;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * This class print the abstract syntax tree to file
 * 
 * @author Kiki
 */
public class Lex {
	
	/**
	 * Print
	 * @param filename input file name
	 * @param filenameLex output file name
	 * @throws Exception
	 */
	public static void lex(String filename, String filenameLex) throws Exception{
		ErrorMsg.ErrorMsg errorMsg = new ErrorMsg.ErrorMsg(filename);
		java.io.InputStream inp = new java.io.FileInputStream(filename);
		PrintStream out = new PrintStream(new FileOutputStream(filenameLex), true);
		Lexer lexer = new Yylex(inp,errorMsg);
		java_cup.runtime.Symbol tok;
		do {
			tok = lexer.nextToken();
			String sym = symnames[tok.sym];
			if ( sym.length() < 8 )
				sym += "\t\t";
			else
				sym += "\t";
			out.print(sym);
			out.print("( " + tok.left + ", " + tok.right + " )\t");
			if ( tok.value != null ) 
				out.print( tok.value.toString() );
			out.println();
		} while (tok.sym != sym.EOF);
		inp.close();
		out.close();
	}

	static String symnames[] = new String[100];
	static {
		symnames[sym.FUNCTION] = "FUNCTION";
		symnames[sym.EOF] = "EOF";
		symnames[sym.INT] = "INT";
		symnames[sym.GT] = "GT";
		symnames[sym.DIVIDE] = "DIVIDE";
		symnames[sym.COLON] = "COLON";
		symnames[sym.ELSE] = "ELSE";
		symnames[sym.OR] = "OR";
		symnames[sym.NIL] = "NIL";
		symnames[sym.DO] = "DO";
		symnames[sym.GE] = "GE";
		symnames[sym.error] = "error";
		symnames[sym.LT] = "LT";
		symnames[sym.OF] = "OF";
		symnames[sym.MINUS] = "MINUS";
		symnames[sym.ARRAY] = "ARRAY";
		symnames[sym.TYPE] = "TYPE";
		symnames[sym.FOR] = "FOR";
		symnames[sym.TO] = "TO";
		symnames[sym.TIMES] = "TIMES";
		symnames[sym.COMMA] = "COMMA";
		symnames[sym.LE] = "LE";
		symnames[sym.IN] = "IN";
		symnames[sym.END] = "END";
		symnames[sym.ASSIGN] = "ASSIGN";
		symnames[sym.STRING] = "STRING";
		symnames[sym.DOT] = "DOT";
		symnames[sym.LPAREN] = "LPAREN";
		symnames[sym.RPAREN] = "RPAREN";
		symnames[sym.IF] = "IF";
		symnames[sym.SEMICOLON] = "SEMICOLON";
		symnames[sym.ID] = "ID";
		symnames[sym.WHILE] = "WHILE";
		symnames[sym.LBRACK] = "LBRACK";
		symnames[sym.RBRACK] = "RBRACK";
		symnames[sym.NEQ] = "NEQ";
		symnames[sym.VAR] = "VAR";
		symnames[sym.BREAK] = "BREAK";
		symnames[sym.AND] = "AND";
		symnames[sym.PLUS] = "PLUS";
		symnames[sym.LBRACE] = "LBRACE";
		symnames[sym.RBRACE] = "RBRACE";
		symnames[sym.LET] = "LET";
		symnames[sym.THEN] = "THEN";
		symnames[sym.EQ] = "EQ";
	}
}