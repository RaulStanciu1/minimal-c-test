package io.github.raulstanciu1.services.parser.generated;// Generated from D:/Projects/Java_Projects/minimal-c-test-lang/src/main/antlr4/MCT.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MCTParser}.
 */
public interface MCTListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MCTParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(MCTParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(MCTParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#code_block}.
	 * @param ctx the parse tree
	 */
	void enterCode_block(MCTParser.Code_blockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#code_block}.
	 * @param ctx the parse tree
	 */
	void exitCode_block(MCTParser.Code_blockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#tests}.
	 * @param ctx the parse tree
	 */
	void enterTests(MCTParser.TestsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#tests}.
	 * @param ctx the parse tree
	 */
	void exitTests(MCTParser.TestsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#test_title}.
	 * @param ctx the parse tree
	 */
	void enterTest_title(MCTParser.Test_titleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#test_title}.
	 * @param ctx the parse tree
	 */
	void exitTest_title(MCTParser.Test_titleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#test_body}.
	 * @param ctx the parse tree
	 */
	void enterTest_body(MCTParser.Test_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#test_body}.
	 * @param ctx the parse tree
	 */
	void exitTest_body(MCTParser.Test_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#test_case}.
	 * @param ctx the parse tree
	 */
	void enterTest_case(MCTParser.Test_caseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#test_case}.
	 * @param ctx the parse tree
	 */
	void exitTest_case(MCTParser.Test_caseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#using_environments}.
	 * @param ctx the parse tree
	 */
	void enterUsing_environments(MCTParser.Using_environmentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#using_environments}.
	 * @param ctx the parse tree
	 */
	void exitUsing_environments(MCTParser.Using_environmentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#used_environments}.
	 * @param ctx the parse tree
	 */
	void enterUsed_environments(MCTParser.Used_environmentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#used_environments}.
	 * @param ctx the parse tree
	 */
	void exitUsed_environments(MCTParser.Used_environmentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#environment}.
	 * @param ctx the parse tree
	 */
	void enterEnvironment(MCTParser.EnvironmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#environment}.
	 * @param ctx the parse tree
	 */
	void exitEnvironment(MCTParser.EnvironmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#environment_body}.
	 * @param ctx the parse tree
	 */
	void enterEnvironment_body(MCTParser.Environment_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#environment_body}.
	 * @param ctx the parse tree
	 */
	void exitEnvironment_body(MCTParser.Environment_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#test_case_title}.
	 * @param ctx the parse tree
	 */
	void enterTest_case_title(MCTParser.Test_case_titleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#test_case_title}.
	 * @param ctx the parse tree
	 */
	void exitTest_case_title(MCTParser.Test_case_titleContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#test_case_body}.
	 * @param ctx the parse tree
	 */
	void enterTest_case_body(MCTParser.Test_case_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#test_case_body}.
	 * @param ctx the parse tree
	 */
	void exitTest_case_body(MCTParser.Test_case_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#test_case_body_element}.
	 * @param ctx the parse tree
	 */
	void enterTest_case_body_element(MCTParser.Test_case_body_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#test_case_body_element}.
	 * @param ctx the parse tree
	 */
	void exitTest_case_body_element(MCTParser.Test_case_body_elementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#test_case_description}.
	 * @param ctx the parse tree
	 */
	void enterTest_case_description(MCTParser.Test_case_descriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#test_case_description}.
	 * @param ctx the parse tree
	 */
	void exitTest_case_description(MCTParser.Test_case_descriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#test_case_steps}.
	 * @param ctx the parse tree
	 */
	void enterTest_case_steps(MCTParser.Test_case_stepsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#test_case_steps}.
	 * @param ctx the parse tree
	 */
	void exitTest_case_steps(MCTParser.Test_case_stepsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#test_case_var_decls}.
	 * @param ctx the parse tree
	 */
	void enterTest_case_var_decls(MCTParser.Test_case_var_declsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#test_case_var_decls}.
	 * @param ctx the parse tree
	 */
	void exitTest_case_var_decls(MCTParser.Test_case_var_declsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#pre_test_code}.
	 * @param ctx the parse tree
	 */
	void enterPre_test_code(MCTParser.Pre_test_codeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#pre_test_code}.
	 * @param ctx the parse tree
	 */
	void exitPre_test_code(MCTParser.Pre_test_codeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#test_code}.
	 * @param ctx the parse tree
	 */
	void enterTest_code(MCTParser.Test_codeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#test_code}.
	 * @param ctx the parse tree
	 */
	void exitTest_code(MCTParser.Test_codeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#post_test_code}.
	 * @param ctx the parse tree
	 */
	void enterPost_test_code(MCTParser.Post_test_codeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#post_test_code}.
	 * @param ctx the parse tree
	 */
	void exitPost_test_code(MCTParser.Post_test_codeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#variable_decl}.
	 * @param ctx the parse tree
	 */
	void enterVariable_decl(MCTParser.Variable_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#variable_decl}.
	 * @param ctx the parse tree
	 */
	void exitVariable_decl(MCTParser.Variable_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#var_input}.
	 * @param ctx the parse tree
	 */
	void enterVar_input(MCTParser.Var_inputContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#var_input}.
	 * @param ctx the parse tree
	 */
	void exitVar_input(MCTParser.Var_inputContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#var_output}.
	 * @param ctx the parse tree
	 */
	void enterVar_output(MCTParser.Var_outputContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#var_output}.
	 * @param ctx the parse tree
	 */
	void exitVar_output(MCTParser.Var_outputContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#var_delta}.
	 * @param ctx the parse tree
	 */
	void enterVar_delta(MCTParser.Var_deltaContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#var_delta}.
	 * @param ctx the parse tree
	 */
	void exitVar_delta(MCTParser.Var_deltaContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#string_decl}.
	 * @param ctx the parse tree
	 */
	void enterString_decl(MCTParser.String_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#string_decl}.
	 * @param ctx the parse tree
	 */
	void exitString_decl(MCTParser.String_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#str_input}.
	 * @param ctx the parse tree
	 */
	void enterStr_input(MCTParser.Str_inputContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#str_input}.
	 * @param ctx the parse tree
	 */
	void exitStr_input(MCTParser.Str_inputContext ctx);
	/**
	 * Enter a parse tree produced by {@link MCTParser#str_output}.
	 * @param ctx the parse tree
	 */
	void enterStr_output(MCTParser.Str_outputContext ctx);
	/**
	 * Exit a parse tree produced by {@link MCTParser#str_output}.
	 * @param ctx the parse tree
	 */
	void exitStr_output(MCTParser.Str_outputContext ctx);
}