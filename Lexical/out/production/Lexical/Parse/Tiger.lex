/*First Part*/
package Parse;
import ErrorMsg.ErrorMsg;
import java.io.InputStreamReader;

%%

/*Second Part*/

%implements Lexer
%function nextToken
%type java_cup.runtime.Symbol  
%char

%{
StringBuffer string = new StringBuffer();
int comment_nesting = 0;

private void newline() {
  errorMsg.newline(yychar);
}

private void err(int pos, String s) {
  errorMsg.error(pos,s);
}

private void err(String s) {
  err(yychar,s);
}

private java_cup.runtime.Symbol tok(int kind, Object value) {
    return new java_cup.runtime.Symbol(kind, yychar, yychar+yylength(), value);
}

private ErrorMsg errorMsg;

Yylex(java.io.InputStream s, ErrorMsg e) {
  this(new InputStreamReader(s));
  errorMsg=e;
}


%}

%eofval{
	{
	 return tok(sym.EOF, null);
        }
%eofval}

whitespace      = [ \t\f]+
letter          = [A-Za-z]
digit           = [0-9]
alphanumeric    = {letter}|{digit}
other_id_char   = [_]
identifier      = {letter}({alphanumeric}|{other_id_char})*
integer         = {digit}+

%state STRING COMMENT
%%

/* Third Part*/

<YYINITIAL>{
    ","	{return tok(sym.COMMA, null);}
    ":" {return tok(sym.COLON, null);}
    ";" {return tok(sym.SEMICOLON, null);}
    "(" {return tok(sym.LPAREN, null);}
    ")" {return tok(sym.RPAREN, null);}
    "[" {return tok(sym.LBRACK, null);}
    "]" {return tok(sym.RBRACK, null);}
    "{" {return tok(sym.LBRACE, null);}
    "}" {return tok(sym.RBRACE, null);}
    "." {return tok(sym.DOT, null);}
    "+" {return tok(sym.PLUS, null);}
    "-" {return tok(sym.MINUS, null);}
    "*" {return tok(sym.TIMES, null);}
    "/" {return tok(sym.DIVIDE, null);}
    "=" {return tok(sym.EQ, null);}
    "<>"    {return tok(sym.NEQ, null);}
    "<" {return tok(sym.LT, null);}
    "<="    {return tok(sym.LE, null);}
    ">" {return tok(sym.GT, null);}
    ">="    {return tok(sym.GE, null);}
    "&" {return tok(sym.AND, null);}
    "|" {return tok(sym.OR, null);}
    ":="    {return tok(sym.ASSIGN, null);}
    "array"	{return tok(sym.ARRAY,null);}
    "break" {return tok(sym.BREAK,null);}
    "do"    {return tok(sym.DO,null);}
    "else"  {return tok(sym.ELSE,null);}
    "end"   {return tok(sym.END,null);}
    "for"   {return tok(sym.FOR,null);}
    "function"  {return tok(sym.FUNCTION,null);}
    "if"    {return tok(sym.IF,null);}
    "in"    {return tok(sym.IN,null);}
    "let"   {return tok(sym.LET,null);}
    "nil"   {return tok(sym.NIL,null);}
    "of"    {return tok(sym.OF,null);}
    "then"  {return tok(sym.THEN,null);}
    "to"    {return tok(sym.TO,null);}
    "type"  {return tok(sym.TYPE,null);}
    "var"   {return tok(sym.VAR,null);}
    "while" {return tok(sym.WHILE,null);}

     \"              {string.setLength(0); yybegin(STRING); }
    {identifier}    {return tok(sym.ID,yytext());}
    {integer}       {return tok(sym.INT, new Integer(yytext()));}

    "/*"            {yybegin(COMMENT);++comment_nesting;}
    {whitespace}    {}
    \n	{newline();}
    \r  {newline();}
}

<COMMENT>{
    [^*/]*          {}
    "/*"            {++comment_nesting;}
    "*/"            {if(--comment_nesting==0) yybegin(YYINITIAL);}
    [*/]            {}
}

<STRING> {
      \"                             { yybegin(YYINITIAL);return tok(sym.STRING,string.toString()); }
      [^\n\r\"\\]+                   { string.append( yytext() ); }
      \\t                            { string.append('\t'); }
      \\n                            { string.append('\n'); }
      \\r                            { string.append('\r'); }
      \\\"                           { string.append('\"'); }
      \\                             { string.append('\\'); }
    }

