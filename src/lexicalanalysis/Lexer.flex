package lexicalanalysis;
import static lexicalanalysis.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
C=[^\"]
espacio=[ \t\r]
%{
    public String lexeme;
%}
%%

/* Espacios en blanco */
{espacio}+ {/*Ignore*/}

/* Comentarios */
( "//".* ) {/*Ignore*/}

/* Salto de linea */
( "\n" ) {return Linea;}

/* Tipos de datos */
( int | float | char | string ) {lexeme=yytext(); return T_Dato;}

/* Sentencias */
( program | endProgram | read | print | if | endIf | while | endWhile ) {lexeme=yytext(); return Sentencia;}

/* Operador Igual */
( "=" ) {lexeme=yytext(); return Igual;}

/* Operador Suma */
( "+" ) {lexeme=yytext(); return Suma;}

/* Operador Resta */
( "-" ) {lexeme=yytext(); return Resta;}

/* Operador Multiplicacion */
( "*" ) {lexeme=yytext(); return Multiplicacion;}

/* Operador Division */
( "/" ) {lexeme=yytext(); return Division;}

/* Menor que */
( "<" ) {lexeme=yytext(); return Op_Relacional;}

/* Menor o igual que */
( <= ) {lexeme=yytext(); return Op_Relacional;}

/* Mayor que */
( ">" ) {lexeme=yytext(); return Op_Relacional;}

/* Mayor o igual que */
( ">=" ) {lexeme=yytext(); return Op_Relacional;}

/* Igual */
( "==" ) {lexeme=yytext(); return Op_Relacional;}

/* Diferente */
( "!=" ) {lexeme=yytext(); return Op_Relacional;}

/* Parentesis de apertura */
( "(" ) {lexeme=yytext(); return Parentesis_a;}

/* Parentesis de cierre */
( ")" ) {lexeme=yytext(); return Parentesis_c;}

/* Coma */
( "\," ) {lexeme=yytext(); return Coma;}

/* Punto y coma */
( ";" ) {lexeme=yytext(); return P_coma;}

/* Identificador */
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}

/* Numero */
(-?{D}+) {lexeme=yytext(); return Entero;}

/* Flotante */
(\-?{D}+\.{D}+) {lexeme=yytext(); return Flotante;}

/* Flotante */
(\"{C}*\") {lexeme=yytext(); return Cadena;}

/* Flotante */
(\'{C}\') {lexeme=yytext(); return Caracter;}

/* Error de analisis */
 . {return ERROR;}