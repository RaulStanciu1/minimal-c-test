grammar MCT;

// Parser Rules

prog: (COMMON_CODE code_block)? tests+ EOF;
code_block: LBRACE INT RBRACE;

tests: TEST test_title? LBRACE test_body RBRACE;
test_title: LPAREN STRING RPAREN;
test_body: (environment | test_case)+;

test_case: TEST_CASE test_case_title? using_environments? LBRACE test_case_body RBRACE;
using_environments: USING used_environments;
used_environments: WORD (COMMA WORD)*;
environment: ENVIRONMENT WORD LBRACE environment_body RBRACE;
environment_body: (variable_decl | string_decl)+;
test_case_title: LPAREN STRING RPAREN;

test_case_body: test_case_body_element*;
test_case_body_element:
      test_case_description
    | test_case_steps
    | test_case_var_decls
    | pre_test_code
    | test_code
    | post_test_code;

test_case_description: DESCRIPTION EQ STRING;
test_case_steps: STEPS EQ INT;
test_case_var_decls: variable_decl | string_decl;

pre_test_code: PRE_TEST_CODE code_block;
test_code: TEST_CODE code_block;
post_test_code: POST_TEST_CODE code_block;

variable_decl: VAR WORD LBRACE var_input? var_output? var_delta? RBRACE;
var_input: IN EQ ((FLOAT | INT | ADDR | PTR) | (LSQUARE (FLOAT | INT | ADDR | PTR) (COMMA (FLOAT | INT | ADDR | PTR))* RSQUARE));
var_output: OUT EQ ((FLOAT | INT | ADDR | PTR) | (LSQUARE (FLOAT | INT | ADDR | PTR) (COMMA (FLOAT | INT | ADDR | PTR))* RSQUARE));
var_delta: DELTA EQ (FLOAT | INT);

string_decl: STR WORD LBRACE str_input? str_output? RBRACE;
str_input: IN EQ (STRING | (LSQUARE STRING (COMMA STRING)* RSQUARE));
str_output: OUT EQ (STRING | (LSQUARE STRING (COMMA STRING)* RSQUARE));



// Lexer Rules
COMMON_CODE: 'COMMON_CODE';
TEST: 'TEST';
USING: 'USING';
ENVIRONMENT: 'ENVIRONMENT';
TEST_CASE: 'TEST_CASE';
DESCRIPTION: 'DESCRIPTION';
STEPS: 'STEPS';
VAR: 'VAR';
IN: 'IN';
OUT: 'OUT';
DELTA: 'DELTA';
STR: 'STR';
PRE_TEST_CODE: 'PRE_TEST_CODE';
TEST_CODE: 'TEST_CODE';
POST_TEST_CODE: 'POST_TEST_CODE';



LBRACE: '{';
RBRACE: '}';
LSQUARE: '[';
RSQUARE: ']';
LPAREN: '(';
RPAREN: ')';
EQ: '=';
COMMA: ',';

ADDR: '&' WORD;
PTR: '*' WORD;
FLOAT: [0-9]+ '.' [0-9]* | '.' [0-9]+;
INT: [0-9]+;
STRING: '"' ( ~["\\] | '\\' . | '\r'? '\n' )* '"';

WORD: [a-zA-Z_][a-zA-Z0-9_]*;


COMMENT: '--' ~[\r\n]* -> skip;
WS: [ \t\r\n]+ -> skip;
BOM: '\uFEFF' -> skip;

