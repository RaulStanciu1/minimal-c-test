package io.github.raulstanciu1.services.parser.generated;// Generated from D:/Projects/Java_Projects/minimal-c-test-lang/src/main/antlr4/MCT.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MCTParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MCTVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MCTParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(MCTParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#code_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCode_block(MCTParser.Code_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#tests}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTests(MCTParser.TestsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#test_title}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest_title(MCTParser.Test_titleContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#test_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest_body(MCTParser.Test_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#test_case}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest_case(MCTParser.Test_caseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#using_environments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUsing_environments(MCTParser.Using_environmentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#used_environments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUsed_environments(MCTParser.Used_environmentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#environment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnvironment(MCTParser.EnvironmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#environment_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnvironment_body(MCTParser.Environment_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#test_case_title}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest_case_title(MCTParser.Test_case_titleContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#test_case_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest_case_body(MCTParser.Test_case_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#test_case_body_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest_case_body_element(MCTParser.Test_case_body_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#test_case_description}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest_case_description(MCTParser.Test_case_descriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#test_case_steps}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest_case_steps(MCTParser.Test_case_stepsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#test_case_var_decls}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest_case_var_decls(MCTParser.Test_case_var_declsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#pre_test_code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPre_test_code(MCTParser.Pre_test_codeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#test_code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTest_code(MCTParser.Test_codeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#post_test_code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPost_test_code(MCTParser.Post_test_codeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#variable_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_decl(MCTParser.Variable_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#var_input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_input(MCTParser.Var_inputContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#var_output}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_output(MCTParser.Var_outputContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#var_delta}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_delta(MCTParser.Var_deltaContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#string_decl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString_decl(MCTParser.String_declContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#str_input}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStr_input(MCTParser.Str_inputContext ctx);
	/**
	 * Visit a parse tree produced by {@link MCTParser#str_output}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStr_output(MCTParser.Str_outputContext ctx);
}