package lexicalanalysis;
import static lexicalanalysis.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ \t\r]
%{
    public String lexeme;
%}
%%

/* Espacios en blanco */
{espacio}+ {/*Ignore*/}

/* Comentarios */
( "//"(.)* ) {/*Ignore*/}

/* Salto de linea */
( "\n" ) {return Linea;}

/* Tipos de datos */
( int | float | char ) {lexeme=yytext(); return T_Dato;}

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

/* Error de analisis */
 . {return ERROR;}