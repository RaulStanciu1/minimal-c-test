package io.github.raulstanciu1.services.parser;

import io.github.raulstanciu1.models.test.*;
import io.github.raulstanciu1.services.Utils;
import io.github.raulstanciu1.services.parser.generated.MCTBaseVisitor;
import io.github.raulstanciu1.services.parser.generated.MCTParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class MCTObjectBuilder extends MCTBaseVisitor<Object> {
    @Override
    public TestProject visitProg(MCTParser.ProgContext ctx){
        TestProject tp = new TestProject();

        String commonCode = "";
        if(ctx.code_block() != null){
            commonCode = ctx.code_block().INT().getText();
        }
        tp.setCommonCode(commonCode);

        if(ctx.tests() != null){
            for(var envCtx : ctx.tests()){
                Test test = (Test) visit(envCtx);
                tp.getTests().add(test);
            }
        }

        return tp;
    }

    @Override
    public Test visitTests(MCTParser.TestsContext ctx){
        Test test = new Test();

        String testTitle = "";
        if(ctx.test_title() != null){
            testTitle = Utils.stripQuotes(ctx.test_title().getText());
        }
        test.setTitle(testTitle);

        if(ctx.test_body() != null){
            if(ctx.test_body().environment() != null){
                for(var envCtx : ctx.test_body().environment()){
                    TestEnvironment env = (TestEnvironment) visit(envCtx);
                    test.getEnvironments().add(env);
                }
            }

            if(ctx.test_body().test_case() != null){
                for(var envCtx : ctx.test_body().test_case()){
                    TestCase tc = (TestCase) visit(envCtx);
                    test.getTestCases().add(tc);
                }
            }
        }

        return test;
    }

    @Override
    public TestEnvironment visitEnvironment(MCTParser.EnvironmentContext ctx){
        TestEnvironment env = new TestEnvironment();
        env.setName(ctx.WORD().getText());

        if(ctx.environment_body() != null){
            if(ctx.environment_body().variable_decl() != null){
                for(var envCtx : ctx.environment_body().variable_decl()){
                    TestVariable variable = (TestVariable) visit(envCtx);
                    env.getTestVariables().add(variable);
                }
            }
            if(ctx.environment_body().string_decl() != null){
                for(var envCtx : ctx.environment_body().string_decl()){
                    TestString string = (TestString) visit(envCtx);
                    env.getTestStrings().add(string);
                }
            }
        }
        return env;
    }

    @Override
    public TestCase visitTest_case(MCTParser.Test_caseContext ctx){
        TestCase tc = new TestCase();

        String title = "";
        if(ctx.test_case_title() != null){
            title = Utils.stripQuotes(ctx.test_case_title().getText());
        }
        tc.setTitle(title);

        if(ctx.using_environments() != null &&
                ctx.using_environments().used_environments() != null &&
                        ctx.using_environments().used_environments().WORD() != null){
            for (TerminalNode terminalNode : ctx.using_environments().used_environments().WORD()) {
                String usedEnv = terminalNode.getText();
                tc.getEnvironmentsUsed().add(usedEnv);
            }
        }

        if(ctx.test_case_body() != null && ctx.test_case_body().test_case_body_element() != null){
            List<MCTParser.Test_case_body_elementContext> elCtxList =
                            ctx.test_case_body().test_case_body_element();
            for(MCTParser.Test_case_body_elementContext elCtx : elCtxList){
                if(elCtx.test_case_description() != null && elCtx.test_case_description().STRING() != null){
                    tc.setDescription(Utils.stripQuotes(elCtx.test_case_description().STRING().getText()));
                }else if(elCtx.test_case_steps() != null){
                    tc.setSteps(Integer.parseInt(elCtx.test_case_steps().INT().getText()));
                }else if(elCtx.pre_test_code() != null){
                    tc.setPreTestCode(elCtx.pre_test_code().code_block().INT().getText());
                }else if(elCtx.test_code() != null){
                    tc.setTestCode(elCtx.test_code().code_block().INT().getText());
                }else if(elCtx.post_test_code() != null){
                    tc.setPostTestCode(elCtx.post_test_code().code_block().INT().getText());
                }else if(elCtx.test_case_var_decls() != null){
                    if(elCtx.test_case_var_decls().variable_decl() != null){
                        TestVariable variable = (TestVariable) visit(elCtx.test_case_var_decls().variable_decl());
                        tc.getTestVariables().add(variable);
                    }else if(elCtx.test_case_var_decls().string_decl() != null){
                        TestString string = (TestString) visit(elCtx.test_case_var_decls().string_decl());
                        tc.getTestStrings().add(string);
                    }
                }
            }
        }

        return tc;
    }

    @Override
    public TestVariable visitVariable_decl(MCTParser.Variable_declContext ctx){
        TestVariable variable = new TestVariable();

        variable.setName(ctx.WORD().getText());

        if(ctx.var_delta() != null){
            variable.setDelta(ctx.var_delta().getChild(2).getText());
        }

        if(ctx.var_input() != null){
            if(ctx.var_input().LSQUARE() != null){
                for(ParseTree child : ctx.var_input().children){
                    if(child instanceof TerminalNode){
                        String val = child.getText();
                        if(!val.equals("[") && !val.equals("]") && !val.equals(",") && !val.equals("=") && !val.equals("IN")){
                            variable.getInputs().add(val);
                        }
                    }
                }
            }else{
                variable.getInputs().add(ctx.var_input().getChild(2).getText());
            }
        }

        if(ctx.var_output() != null){
            if(ctx.var_output().LSQUARE() != null){
                for(ParseTree child : ctx.var_output().children){
                    if(child instanceof TerminalNode){
                        String val = child.getText();
                        if(!val.equals("[") && !val.equals("]") && !val.equals(",") && !val.equals("=") && !val.equals("OUT")){
                            variable.getOutputs().add(val);
                        }
                    }
                }
            }else{
                variable.getOutputs().add(ctx.var_output().getChild(2).getText());
            }
        }

        return variable;
    }

    @Override
    public TestString visitString_decl(MCTParser.String_declContext ctx){
        TestString string = new TestString();

        string.setName(ctx.WORD().getText());

        if(ctx.str_input() != null){
            for(var token : ctx.str_input().STRING()){
                string.getInputs().add(token.getText());
            }
        }

        if(ctx.str_output() != null){
            for(var token : ctx.str_output().STRING()){
                string.getOutputs().add(token.getText());
            }
        }

        return string;
    }
}
