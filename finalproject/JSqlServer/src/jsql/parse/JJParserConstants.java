package jsql.parse;

public interface JJParserConstants {
	  int EOF = 0;
	  int K_ALL = 5;
	  int K_AND = 6;
	  int K_ANY = 7;
	  int K_AS = 8;
	  int K_ASC = 9;
	  int K_AVG = 10;
	  int K_BETWEEN = 11;
	  int K_BINARY_INTEGER = 12;
	  int K_BOOLEAN = 13;
	  int K_BY = 14;
	  int K_CHAR = 15;
	  int K_COMMENT = 16;
	  int K_COMMIT = 17;
	  int K_CONNECT = 18;
	  int K_COUNT = 19;
	  int K_DATE = 20;
	  int K_DELETE = 21;
	  int K_DESC = 22;
	  int K_DISTINCT = 23;
	  int K_EXCLUSIVE = 24;
	  int K_EXISTS = 25;
	  int K_EXIT = 26;
	  int K_FLOAT = 27;
	  int K_FOR = 28;
	  int K_FROM = 29;
	  int K_GROUP = 30;
	  int K_HAVING = 31;
	  int K_IN = 32;
	  int K_INSERT = 33;
	  int K_INTEGER = 34;
	  int K_INTERSECT = 35;
	  int K_INTO = 36;
	  int K_IS = 37;
	  int K_LIKE = 38;
	  int K_LOCK = 39;
	  int K_MAX = 40;
	  int K_MIN = 41;
	  int K_MINUS = 42;
	  int K_MODE = 43;
	  int K_NATURAL = 44;
	  int K_NOT = 45;
	  int K_NOWAIT = 46;
	  int K_NULL = 47;
	  int K_NUMBER = 48;
	  int K_OF = 49;
	  int K_ONLY = 50;
	  int K_OR = 51;
	  int K_ORDER = 52;
	  int K_PRIOR = 53;
	  int K_QUIT = 54;
	  int K_READ = 55;
	  int K_REAL = 56;
	  int K_ROLLBACK = 57;
	  int K_ROW = 58;
	  int K_SELECT = 59;
	  int K_SET = 60;
	  int K_SHARE = 61;
	  int K_SMALLINT = 62;
	  int K_START = 63;
	  int K_SUM = 64;
	  int K_TABLE = 65;
	  int K_TRANSACTION = 66;
	  int K_UNION = 67;
	  int K_UPDATE = 68;
	  int K_VALUES = 69;
	  int K_VARCHAR2 = 70;
	  int K_VARCHAR = 71;
	  int K_WHERE = 72;
	  int K_WITH = 73;
	  int K_WORK = 74;
	  int K_WRITE = 75;
	  int S_NUMBER = 76;
	  int FLOAT = 77;
	  int INTEGER = 78;
	  int DIGIT = 79;
	  int LINE_COMMENT = 80;
	  int MULTI_LINE_COMMENT = 81;
	  int S_IDENTIFIER = 82;
	  int LETTER = 83;
	  int SPECIAL_CHARS = 84;
	  int S_BIND = 85;
	  int S_CHAR_LITERAL = 86;
	  int S_QUOTED_IDENTIFIER = 87;

	  int DEFAULT = 0;

	  String[] tokenImage = {
	    "<EOF>",
	    "\" \"",
	    "\"\\t\"",
	    "\"\\r\"",
	    "\"\\n\"",
	    "\"ALL\"",
	    "\"AND\"",
	    "\"ANY\"",
	    "\"AS\"",
	    "\"ASC\"",
	    "\"AVG\"",
	    "\"BETWEEN\"",
	    "\"BINARY_INTEGER\"",
	    "\"BOOLEAN\"",
	    "\"BY\"",
	    "\"CHAR\"",
	    "\"COMMENT\"",
	    "\"COMMIT\"",
	    "\"CONNECT\"",
	    "\"COUNT\"",
	    "\"DATE\"",
	    "\"DELETE\"",
	    "\"DESC\"",
	    "\"DISTINCT\"",
	    "\"EXCLUSIVE\"",
	    "\"EXISTS\"",
	    "\"EXIT\"",
	    "\"FLOAT\"",
	    "\"FOR\"",
	    "\"FROM\"",
	    "\"GROUP\"",
	    "\"HAVING\"",
	    "\"IN\"",
	    "\"INSERT\"",
	    "\"INTEGER\"",
	    "\"INTERSECT\"",
	    "\"INTO\"",
	    "\"IS\"",
	    "\"LIKE\"",
	    "\"LOCK\"",
	    "\"MAX\"",
	    "\"MIN\"",
	    "\"MINUS\"",
	    "\"MODE\"",
	    "\"NATURAL\"",
	    "\"NOT\"",
	    "\"NOWAIT\"",
	    "\"NULL\"",
	    "\"NUMBER\"",
	    "\"OF\"",
	    "\"ONLY\"",
	    "\"OR\"",
	    "\"ORDER\"",
	    "\"PRIOR\"",
	    "\"QUIT\"",
	    "\"READ\"",
	    "\"REAL\"",
	    "\"ROLLBACK\"",
	    "\"ROW\"",
	    "\"SELECT\"",
	    "\"SET\"",
	    "\"SHARE\"",
	    "\"SMALLINT\"",
	    "\"START\"",
	    "\"SUM\"",
	    "\"TABLE\"",
	    "\"TRANSACTION\"",
	    "\"UNION\"",
	    "\"UPDATE\"",
	    "\"VALUES\"",
	    "\"VARCHAR2\"",
	    "\"VARCHAR\"",
	    "\"WHERE\"",
	    "\"WITH\"",
	    "\"WORK\"",
	    "\"WRITE\"",
	    "<S_NUMBER>",
	    "<FLOAT>",
	    "<INTEGER>",
	    "<DIGIT>",
	    "<LINE_COMMENT>",
	    "<MULTI_LINE_COMMENT>",
	    "<S_IDENTIFIER>",
	    "<LETTER>",
	    "<SPECIAL_CHARS>",
	    "<S_BIND>",
	    "<S_CHAR_LITERAL>",
	    "<S_QUOTED_IDENTIFIER>",
	    "\"(\"",
	    "\",\"",
	    "\")\"",
	    "\";\"",
	    "\"=\"",
	    "\".\"",
	    "\"!=\"",
	    "\"#\"",
	    "\"<>\"",
	    "\">\"",
	    "\">=\"",
	    "\"<\"",
	    "\"<=\"",
	    "\"+\"",
	    "\"-\"",
	    "\"*\"",
	    "\".*\"",
	    "\"?\"",
	    "\"||\"",
	    "\"/\"",
	    "\"**\"",
	  };
}
