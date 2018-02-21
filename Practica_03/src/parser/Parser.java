//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package parser;



//#line 2 "../../src/parser/parser.y"
/* * Declaraciones de c�digo Java*/
/* * Se sit�an al comienzo del archivo generado*/
/* * El package lo a�ade yacc si utilizamos la opci�n -Jpackage*/
import scanner.Scanner;
import java.io.Reader;
//#line 23 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short INT_CONSTANT=257;
public final static short INPUT=258;
public final static short PRINT=259;
public final static short DEF=260;
public final static short WHILE=261;
public final static short IF=262;
public final static short ELSE=263;
public final static short INT=264;
public final static short DOUBLE=265;
public final static short CHAR=266;
public final static short STRUCT=267;
public final static short RETURN=268;
public final static short VOID=269;
public final static short ID=270;
public final static short REAL_CONSTANT=271;
public final static short CHAR_CONSTANT=272;
public final static short GREATER=273;
public final static short SMALLER=274;
public final static short EQUALS=275;
public final static short NEGATION=276;
public final static short MAIN=277;
public final static short OR=278;
public final static short AND=279;
public final static short CAST=280;
public final static short UNARIO=281;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    2,    2,    4,    7,    7,    7,    7,
    6,    6,    5,    5,   11,   11,   12,    8,    8,    3,
   13,   13,   10,   10,   10,   10,   10,   14,   14,   15,
    9,    9,   16,   16,   16,   16,   16,   16,   16,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   18,   18,   18,   18,   18,   18,   18,   18,
   18,   18,   17,   17,   19,   21,   20,   24,   24,   23,
   22,   25,   25,   26,   26,
};
final static short yylen[] = {                            2,
    9,    2,    0,    2,    1,   10,    1,    1,    2,    0,
    1,    1,    0,    1,    1,    3,    3,    2,    3,    3,
    1,    3,    1,    1,    1,    4,    4,    1,    2,    4,
    1,    2,    3,    3,    3,    2,    1,    1,    2,    1,
    1,    1,    1,    3,    4,    3,    4,    2,    2,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    1,    3,    3,    6,    5,    0,    2,    3,
    4,    0,    1,    1,    3,
};
final static short yydefred[] = {                         3,
    0,    0,    0,   21,    2,    0,    5,    0,    0,    0,
    4,    0,    0,    0,    0,   23,   24,   25,    0,    0,
   20,   22,    0,    0,    0,   15,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   28,    0,   17,    0,   16,
    0,    0,   27,   29,   26,   12,    0,   11,    0,    0,
    0,    1,   30,   41,    0,    0,    0,    0,    0,    0,
   43,   42,    0,    0,    0,    0,    0,    0,    0,   31,
    0,    0,   37,   38,    0,   40,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   18,    6,    0,
    0,    0,   32,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   36,
   39,   34,    0,   33,    0,    0,   35,   74,    0,    0,
    0,   44,   19,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   71,    0,    0,   45,    0,    0,    0,
   67,   75,   66,   70,   69,
};
final static short yydgoto[] = {                          1,
    2,    5,    6,    7,   24,   47,   67,   68,   69,   21,
   25,   26,    8,   35,   36,   70,   77,   71,   72,   73,
   74,   75,  143,  151,  119,  120,
};
final static short yysindex[] = {                         0,
    0, -257, -232,    0,    0,  -57,    0,   57,  -29,  -26,
    0,  -62, -229, -228,    3,    0,    0,    0,  -77, -203,
    0,    0,   13,   38,   37,    0,   34, -170,   27,  -62,
   68, -228, -142,   58, -109,    0,  -62,    0,  -90,    0,
    5,  -62,    0,    0,    0,    0,    7,    0,   11,   78,
  485,    0,    0,    0,  404,  404,  404,  404,  404,   99,
    0,    0,  404,  404,  315,   84,   19,  485,  509,    0,
  123,   92,    0,    0,   94,    0,  -32,  431,   -7,  149,
  185,  211, -116,  -18,  -18,  121,  343,    0,    0,  104,
  509,   99,    0,  404,  404,  404,  404,  404,  404,  404,
  404,  404,  404,  404,  404,  404,  404,  404,  404,    0,
    0,    0,  404,    0,   41,   44,    0,    0,  131,  127,
  404,    0,    0,  831,  809,  546,  770,  457,  464,  431,
  820,  238,  514,  277,  -27,   -3,  -18,   82,  405,  431,
  509,  509,  -85,    0,  -88,  -18,    0,  275,  305,   44,
    0,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  139,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  146,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   63,    0,    0,    0,    0,    0,    0,    0,    0,   61,
    0,    0,    0,    0,    0,    0,    0,   64,   72,    0,
    0,    0,    0,    0,    0,    0,    0,   -4,    0,    0,
    0,    0,  152,  -11,   16,    0,    0,    0,    0,    0,
   75,  371,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  165,
    0,    0,    0,  667,  717,  741,  728,  157,  750,  151,
  704,  641,  628,  566,   97,   88,   25,  -37,    0,   21,
    0,    0,  396,    0,    0,   52,    0,    0,    0,    0,
    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  -33,    0,    0,    0,    0,    0,  -51,  187,
    0,  176,   89,    0,  177,  130,  163,  868,    0,    0,
    0,    0,   70,    0,    0,    0,
};
final static int YYTABLESIZE=1093;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         46,
   20,   11,    3,   46,   46,   46,   46,   46,   46,   46,
   14,  113,    4,   15,  107,   43,   91,   66,  108,  106,
   46,   46,   46,   46,   46,   48,  112,  108,   20,   48,
   48,   48,   48,   48,   90,   48,  113,    9,  107,   63,
   22,   23,  108,   27,   10,   28,   48,   48,   48,   48,
   48,  114,   49,   29,   63,   46,   49,   49,   49,   49,
   49,   50,   49,  109,   64,   50,   50,   50,   50,   50,
   30,   50,  109,   49,   49,   49,   49,   49,   31,   64,
   32,   48,   50,   50,   50,   50,   50,  109,   47,  148,
  149,   33,   47,   47,   47,   47,   47,   40,   47,    4,
   13,   13,   40,   40,   21,   40,   40,   40,   49,   47,
   47,   47,   47,   47,   12,   42,   34,   50,   21,   37,
   40,   40,   40,   34,   51,   39,   41,   49,   51,   51,
   51,   51,   51,   52,   51,   52,   53,   52,   83,   52,
   52,   52,   88,   89,   47,   51,   51,   51,   51,   51,
  110,   40,  111,  118,   52,   52,   52,   52,   52,  105,
    4,  121,  123,  141,  107,  104,  142,  103,  108,  106,
  145,  144,  109,   16,   17,   18,   19,  150,   46,   13,
   51,  152,  101,  100,  102,  105,   14,   10,    7,   52,
  107,  104,   72,  103,  108,  106,    8,   62,   93,    9,
   62,   16,   17,   18,   19,   73,  115,   40,  101,   65,
  102,   44,    0,  109,   62,   62,   38,   62,   79,  155,
   93,  105,    0,   45,    0,   48,  107,  104,   50,  103,
  108,  106,    0,    0,    0,   46,   46,   46,   46,  109,
   46,   46,  116,    0,  101,    0,  102,  105,    0,   62,
    0,   86,  107,  104,    0,  103,  108,  106,    0,    0,
    0,   48,   48,   48,   48,    0,   48,   48,    0,  117,
  101,    0,  102,    0,  105,  109,    0,   93,   93,  107,
  104,    0,  103,  108,  106,    0,    0,    0,   49,   49,
   49,   49,    0,   49,   49,    0,    0,   50,   50,   50,
   50,  109,   50,   50,    0,    0,    0,   64,    0,    0,
    0,    0,    0,  105,   65,    0,    0,    0,  107,   63,
    0,    0,  108,  106,   47,   47,   47,   47,  109,   47,
   47,    0,    0,   40,   40,   40,   40,   64,   40,   40,
    0,    0,    0,    0,   65,    0,    0,   64,    0,   63,
    0,    0,    0,    0,   65,    0,    0,    0,    0,   63,
   51,   51,   51,   51,    0,   51,   51,  109,    0,   52,
   52,   52,   52,    0,   52,   52,    0,    0,    0,  105,
    0,    0,    0,  122,  107,  104,    0,  103,  108,  106,
    0,    0,    0,    0,    0,   94,   95,   96,   97,  153,
   98,   99,  101,    0,  102,   20,    0,   40,    0,    0,
    0,    0,   40,   40,    0,   40,   40,   40,    0,    0,
    0,   94,   95,   96,   97,    0,   98,   99,   68,  154,
   40,   40,   40,  109,   62,   68,   64,    0,    0,    0,
   68,  105,    0,   65,    0,    0,  107,  104,   63,  103,
  108,  106,    0,    0,    0,    0,    0,   94,   95,   96,
   97,   40,   98,   99,  101,    0,  102,  105,    0,    0,
    0,    0,  107,  104,    0,  103,  108,  106,    0,    0,
    0,    0,    0,   94,   95,   96,   97,    0,   98,   99,
  101,    0,  102,  105,    0,  109,    0,  147,  107,  104,
  105,  103,  108,  106,    0,  107,  104,    0,  103,  108,
  106,    0,    0,    0,    0,    0,  101,   64,  102,    0,
   68,  109,    0,  101,   65,  102,    0,    0,    0,   63,
    0,   54,   55,   56,    0,   57,   58,    0,    0,    0,
    0,   64,   59,    0,   92,   61,   62,  109,   65,    0,
  105,    0,    0,   63,  109,  107,  104,    0,    0,  108,
  106,   54,   55,   56,    0,   57,   58,    0,    0,    0,
    0,   54,   59,    0,   92,   61,   62,    0,   16,   17,
   18,   19,  105,    0,   76,   61,   62,  107,  104,    0,
  103,  108,  106,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  109,  101,   53,  102,   53,   53,
   53,    0,    0,    0,    0,   94,   95,   96,   97,    0,
   98,   99,    0,   53,   53,   53,   53,   53,    0,    0,
    0,    0,    0,    0,    0,    0,  109,    0,    0,    0,
    0,    0,    0,   40,   40,   40,   40,    0,   40,   40,
    0,    0,   68,   68,   68,    0,   68,   68,   53,    0,
   54,    0,    0,   68,    0,   68,   68,   68,   54,    0,
    0,   54,   54,   76,   61,   62,    0,   94,   95,   96,
   97,   55,   98,   99,   55,   54,   54,   54,   54,   54,
    0,    0,    0,    0,    0,    0,    0,    0,   55,   55,
   55,   55,   55,   94,   95,   96,   97,   56,   98,   99,
   56,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   54,    0,    0,    0,   56,   56,   56,   56,    0,   94,
   95,   96,   97,   55,    0,   99,   94,   95,   96,   97,
    0,   54,   55,   56,   57,   57,   58,   57,    0,    0,
    0,    0,   59,    0,   60,   61,   62,   58,    0,   56,
   58,   57,   57,   57,   57,   54,   55,   56,   59,   57,
   58,   59,    0,    0,   58,   58,   59,   58,   92,   61,
   62,   60,    0,    0,   60,   59,   59,    0,   59,    0,
   61,    0,    0,   61,    0,    0,   57,    0,   60,   60,
    0,   60,    0,    0,    0,    0,  105,   61,   61,   58,
   61,  107,  104,    0,  103,  108,  106,    0,   94,   95,
   59,   97,    0,    0,    0,    0,    0,    0,    0,  101,
    0,  102,    0,   60,    0,    0,    0,    0,   53,   53,
   53,   53,   61,   53,   53,  105,    0,    0,    0,    0,
  107,  104,    0,  103,  108,  106,  105,    0,    0,    0,
  109,  107,  104,    0,  103,  108,  106,  105,  101,    0,
  102,    0,  107,  104,    0,  103,  108,  106,    0,    0,
    0,  102,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  102,    0,    0,    0,    0,    0,    0,  109,
   54,   54,   54,   54,    0,   54,   54,    0,    0,    0,
  109,    0,    0,   55,   55,   55,   55,    0,   55,   55,
    0,  109,   78,   78,   80,   81,   82,    0,    0,    0,
   84,   85,   87,    0,    0,    0,    0,    0,    0,   56,
   56,   56,   56,    0,   56,   56,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  124,  125,  126,  127,  128,  129,  130,  131,  132,
  133,  134,  135,  136,  137,  138,  139,   57,   57,   57,
  140,   57,   57,    0,    0,    0,    0,    0,  146,    0,
   58,   58,   58,    0,   58,   58,    0,    0,    0,    0,
    0,    0,   59,   59,    0,   59,   59,    0,    0,    0,
    0,    0,    0,    0,    0,   60,    0,    0,   60,   60,
    0,    0,    0,    0,    0,    0,    0,   61,   61,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   94,   95,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   94,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   94,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   59,  260,   41,   42,   43,   44,   45,   46,   47,
   40,   44,  270,   40,   42,  125,   68,   51,   46,   47,
   58,   59,   60,   61,   62,   37,   59,   46,   91,   41,
   42,   43,   44,   45,   68,   47,   44,  270,   42,   44,
  270,  270,   46,   41,  277,  123,   58,   59,   60,   61,
   62,   59,   37,  257,   59,   93,   41,   42,   43,   44,
   45,   37,   47,   91,   44,   41,   42,   43,   44,   45,
   58,   47,   91,   58,   59,   60,   61,   62,   41,   59,
   44,   93,   58,   59,   60,   61,   62,   91,   37,  141,
  142,   58,   41,   42,   43,   44,   45,   37,   47,  270,
   44,   44,   42,   43,   44,   45,   46,   47,   93,   58,
   59,   60,   61,   62,   58,   58,   28,   93,   58,   93,
   60,   61,   62,   35,   37,   58,  269,  123,   41,  123,
   43,   44,   45,   37,   47,  125,   59,   41,   40,   43,
   44,   45,   59,  125,   93,   58,   59,   60,   61,   62,
   59,   91,   59,  270,   58,   59,   60,   61,   62,   37,
  270,   41,   59,  123,   42,   43,  123,   45,   46,   47,
   44,   41,   91,  264,  265,  266,  267,  263,  269,   41,
   93,  270,   60,   61,   62,   37,   41,  125,  125,   93,
   42,   43,   41,   45,   46,   47,  125,   41,   69,  125,
   44,  264,  265,  266,  267,   41,   58,   32,   60,   59,
   62,   35,   -1,   91,   58,   59,   30,   61,   56,  150,
   91,   37,   -1,   37,   -1,   39,   42,   43,   42,   45,
   46,   47,   -1,   -1,   -1,  273,  274,  275,  276,   91,
  278,  279,   58,   -1,   60,   -1,   62,   37,   -1,   93,
   -1,   65,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,  273,  274,  275,  276,   -1,  278,  279,   -1,   59,
   60,   -1,   62,   -1,   37,   91,   -1,  148,  149,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,  273,  274,
  275,  276,   -1,  278,  279,   -1,   -1,  273,  274,  275,
  276,   91,  278,  279,   -1,   -1,   -1,   33,   -1,   -1,
   -1,   -1,   -1,   37,   40,   -1,   -1,   -1,   42,   45,
   -1,   -1,   46,   47,  273,  274,  275,  276,   91,  278,
  279,   -1,   -1,  273,  274,  275,  276,   33,  278,  279,
   -1,   -1,   -1,   -1,   40,   -1,   -1,   33,   -1,   45,
   -1,   -1,   -1,   -1,   40,   -1,   -1,   -1,   -1,   45,
  273,  274,  275,  276,   -1,  278,  279,   91,   -1,  273,
  274,  275,  276,   -1,  278,  279,   -1,   -1,   -1,   37,
   -1,   -1,   -1,   41,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   -1,   -1,  273,  274,  275,  276,  125,
  278,  279,   60,   -1,   62,   91,   -1,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,  273,  274,  275,  276,   -1,  278,  279,   33,  125,
   60,   61,   62,   91,  278,   40,   33,   -1,   -1,   -1,
   45,   37,   -1,   40,   -1,   -1,   42,   43,   45,   45,
   46,   47,   -1,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,   91,  278,  279,   60,   -1,   62,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,
   60,   -1,   62,   37,   -1,   91,   -1,   93,   42,   43,
   37,   45,   46,   47,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,   60,   33,   62,   -1,
  125,   91,   -1,   60,   40,   62,   -1,   -1,   -1,   45,
   -1,  257,  258,  259,   -1,  261,  262,   -1,   -1,   -1,
   -1,   33,  268,   -1,  270,  271,  272,   91,   40,   -1,
   37,   -1,   -1,   45,   91,   42,   43,   -1,   -1,   46,
   47,  257,  258,  259,   -1,  261,  262,   -1,   -1,   -1,
   -1,  257,  268,   -1,  270,  271,  272,   -1,  264,  265,
  266,  267,   37,   -1,  270,  271,  272,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   91,   60,   41,   62,   43,   44,
   45,   -1,   -1,   -1,   -1,  273,  274,  275,  276,   -1,
  278,  279,   -1,   58,   59,   60,   61,   62,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,   -1,   -1,
   -1,   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,
   -1,   -1,  257,  258,  259,   -1,  261,  262,   93,   -1,
  257,   -1,   -1,  268,   -1,  270,  271,  272,   41,   -1,
   -1,   44,   45,  270,  271,  272,   -1,  273,  274,  275,
  276,   41,  278,  279,   44,   58,   59,   60,   61,   62,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,   59,
   60,   61,   62,  273,  274,  275,  276,   41,  278,  279,
   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   93,   -1,   -1,   -1,   58,   59,   60,   61,   -1,  273,
  274,  275,  276,   93,   -1,  279,  273,  274,  275,  276,
   -1,  257,  258,  259,   41,  261,  262,   44,   -1,   -1,
   -1,   -1,  268,   -1,  270,  271,  272,   41,   -1,   93,
   44,   58,   59,   60,   61,  257,  258,  259,   41,  261,
  262,   44,   -1,   -1,   58,   59,  268,   61,  270,  271,
  272,   41,   -1,   -1,   44,   58,   59,   -1,   61,   -1,
   41,   -1,   -1,   44,   -1,   -1,   93,   -1,   58,   59,
   -1,   61,   -1,   -1,   -1,   -1,   37,   58,   59,   93,
   61,   42,   43,   -1,   45,   46,   47,   -1,  273,  274,
   93,  276,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   60,
   -1,   62,   -1,   93,   -1,   -1,   -1,   -1,  273,  274,
  275,  276,   93,  278,  279,   37,   -1,   -1,   -1,   -1,
   42,   43,   -1,   45,   46,   47,   37,   -1,   -1,   -1,
   91,   42,   43,   -1,   45,   46,   47,   37,   60,   -1,
   62,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   62,   -1,   -1,   -1,   -1,   -1,   -1,   91,
  273,  274,  275,  276,   -1,  278,  279,   -1,   -1,   -1,
   91,   -1,   -1,  273,  274,  275,  276,   -1,  278,  279,
   -1,   91,   55,   56,   57,   58,   59,   -1,   -1,   -1,
   63,   64,   65,   -1,   -1,   -1,   -1,   -1,   -1,  273,
  274,  275,  276,   -1,  278,  279,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   94,   95,   96,   97,   98,   99,  100,  101,  102,
  103,  104,  105,  106,  107,  108,  109,  274,  275,  276,
  113,  278,  279,   -1,   -1,   -1,   -1,   -1,  121,   -1,
  274,  275,  276,   -1,  278,  279,   -1,   -1,   -1,   -1,
   -1,   -1,  275,  276,   -1,  278,  279,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  275,   -1,   -1,  278,  279,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  278,  279,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  273,  274,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  273,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  273,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=281;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"INT_CONSTANT","INPUT","PRINT",
"DEF","WHILE","IF","ELSE","INT","DOUBLE","CHAR","STRUCT","RETURN","VOID","ID",
"REAL_CONSTANT","CHAR_CONSTANT","GREATER","SMALLER","EQUALS","NEGATION","MAIN",
"OR","AND","CAST","UNARIO",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones DEF MAIN '(' ')' ':' VOID '{' '}'",
"definiciones : definiciones definicion",
"definiciones :",
"definicion : def ';'",
"definicion : funcion",
"funcion : DEF ID '(' params ')' ':' retorno '{' body '}'",
"body : defs",
"body : sentencias",
"body : defs sentencias",
"body :",
"retorno : tipo",
"retorno : VOID",
"params :",
"params : param",
"param : par",
"param : param ',' par",
"par : ID ':' tipo",
"defs : def ';'",
"defs : defs def ';'",
"def : ids ':' tipo",
"ids : ID",
"ids : ids ',' ID",
"tipo : INT",
"tipo : DOUBLE",
"tipo : CHAR",
"tipo : '[' INT_CONSTANT ']' tipo",
"tipo : STRUCT '{' campos '}'",
"campos : campo",
"campos : campos campo",
"campo : ids ':' tipo ';'",
"sentencias : sentencia",
"sentencias : sentencias sentencia",
"sentencia : PRINT list ';'",
"sentencia : INPUT list ';'",
"sentencia : RETURN expresion ';'",
"sentencia : asignacion ';'",
"sentencia : condicionales",
"sentencia : while",
"sentencia : invocacion ';'",
"expresion : ID",
"expresion : INT_CONSTANT",
"expresion : CHAR_CONSTANT",
"expresion : REAL_CONSTANT",
"expresion : '(' expresion ')'",
"expresion : expresion '[' expresion ']'",
"expresion : expresion '.' expresion",
"expresion : '(' tipo ')' expresion",
"expresion : '-' expresion",
"expresion : '!' expresion",
"expresion : expresion '*' expresion",
"expresion : expresion '/' expresion",
"expresion : expresion '%' expresion",
"expresion : expresion '+' expresion",
"expresion : expresion '-' expresion",
"expresion : expresion '>' expresion",
"expresion : expresion GREATER expresion",
"expresion : expresion '<' expresion",
"expresion : expresion SMALLER expresion",
"expresion : expresion NEGATION expresion",
"expresion : expresion EQUALS expresion",
"expresion : expresion AND expresion",
"expresion : expresion OR expresion",
"list : expresion",
"list : list ',' expresion",
"asignacion : expresion '=' expresion",
"while : WHILE expresion ':' '{' sentencias '}'",
"condicionales : IF expresion ':' cuerpo else",
"else :",
"else : ELSE cuerpo",
"cuerpo : '{' sentencias '}'",
"invocacion : ID '(' args ')'",
"args :",
"args : arg",
"arg : ID",
"arg : arg ',' ID",
};

//#line 207 "../../src/parser/parser.y"
// * C�digo Java
// * Se crea una clase "Parser", lo que aqu� ubiquemos ser�:
//	- Atributos, si son variables
//	- M�todos, si son funciones
//   de la clase "Parser"

// * Estamos obligados a implementar:
//	int yylex()
//	void yyerror(String)

// * Referencia al analizador l�xico
private Scanner scanner;

// * Llamada al analizador l�xico
private int yylex () {
    int token=0;
    try { 
		token=scanner.yylex(); 	
		this.yylval = scanner.getYylval();
    } catch(Throwable e) {
	    System.err.println ("Lexical error at line " + scanner.getLine() + " and column "+scanner.getColumn()+":\n\t"+e); 
    }
    return token;
}

// * Manejo de Errores Sint�cticos
public void yyerror (String error) {
    System.err.println ("Syntactical error at line " + scanner.getLine() + " and column "+scanner.getColumn()+":\n\t"+error);
}

// * Constructor del Sint�ctico
public Parser(Scanner scanner) {
	this.scanner = scanner;
}
//#line 573 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
