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

<YYINITIAL>","	{return tok(sym.COMMA, null);}
<YYINITIAL>":" {return tok(sym.COLON, null);}
<YYINITIAL>";" {return tok(sym.SEMICOLON, null);}
<YYINITIAL>"(" {return tok(sym.LPAREN, null);}
<YYINITIAL>")" {return tok(sym.RPAREN, null);}
<YYINITIAL>"[" {return tok(sym.LBRACK, null);}
<YYINITIAL>"]" {return tok(sym.RBRACK, null);}
<YYINITIAL>"{" {return tok(sym.LBRACE, null);}
<YYINITIAL>"}" {return tok(sym.RBRACE, null);}
<YYINITIAL>"." {return tok(sym.DOT, null);}
<YYINITIAL>"+" {return tok(sym.PLUS, null);}
<YYINITIAL>"-" {return tok(sym.MINUS, null);}
<YYINITIAL>"*" {return tok(sym.TIMES, null);}
<YYINITIAL>"/" {return tok(sym.DIVIDE, null);}
<YYINITIAL>"=" {return tok(sym.EQ, null);}
<YYINITIAL>"<>"    {return tok(sym.NEQ, null);}
<YYINITIAL>"<" {return tok(sym.LT, null);}
<YYINITIAL>"<="    {return tok(sym.LE, null);}
<YYINITIAL>">" {return tok(sym.GT, null);}
<YYINITIAL>">="    {return tok(sym.GE, null);}
<YYINITIAL>"&" {return tok(sym.AND, null);}
<YYINITIAL>"|" {return tok(sym.OR, null);}
<YYINITIAL>":="    {return tok(sym.ASSIGN, null);}
<YYINITIAL>"array"	{return tok(sym.ARRAY,null);}
<YYINITIAL>"break" {return tok(sym.BREAK,null);}
<YYINITIAL>"do"    {return tok(sym.DO,null);}
<YYINITIAL>"else"  {return tok(sym.ELSE,null);}
<YYINITIAL>"end"   {return tok(sym.END,null);}
<YYINITIAL>"for"   {return tok(sym.FOR,null);}
<YYINITIAL>"function"  {return tok(sym.FUNCTION,null);}
<YYINITIAL>"if"    {return tok(sym.IF,null);}
<YYINITIAL>"in"    {return tok(sym.IN,null);}
<YYINITIAL>"let"   {return tok(sym.LET,null);}
<YYINITIAL>"nil"   {return tok(sym.NIL,null);}
<YYINITIAL>"of"    {return tok(sym.OF,null);}
<YYINITIAL>"then"  {return tok(sym.THEN,null);}
<YYINITIAL>"to"    {return tok(sym.TO,null);}
<YYINITIAL>"type"  {return tok(sym.TYPE,null);}
<YYINITIAL>"var"   {return tok(sym.VAR,null);}
<YYINITIAL>"while" {return tok(sym.WHILE,null);}


<YYINITIAL> {
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

