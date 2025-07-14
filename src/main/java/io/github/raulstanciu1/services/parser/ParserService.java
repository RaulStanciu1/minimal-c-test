package io.github.raulstanciu1.services.parser;


import io.github.raulstanciu1.models.test.Test;
import io.github.raulstanciu1.models.test.TestCase;
import io.github.raulstanciu1.models.test.TestProject;
import io.github.raulstanciu1.models.ProjectEnvironment;
import io.github.raulstanciu1.services.parser.generated.MCTLexer;
import io.github.raulstanciu1.services.parser.generated.MCTParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserService {

    private static void updateTestProjectCCode(TestProject testProject, List<String> cCodeList){
        for(int i = 0; i < cCodeList.size(); i++){
            String strI = String.valueOf(i);
            if(testProject.getCommonCode().equals(strI)){
                testProject.setCommonCode(cCodeList.get(i));
            }else{
                for(Test test: testProject.getTests()){
                    for(TestCase testCase : test.getTestCases()){
                        if(testCase.getPreTestCode().equals(strI)){
                            testCase.setPreTestCode(cCodeList.get(i));
                        }else if(testCase.getTestCode().equals(strI)){
                            testCase.setTestCode(cCodeList.get(i));
                        }else if(testCase.getPostTestCode().equals(strI)){
                            testCase.setPostTestCode(cCodeList.get(i));
                        }
                    }
                }
            }
        }
    }

    public static void parseMctFile() throws IOException{
        ProjectEnvironment environment = ProjectEnvironment.getInstance();
        List<String> cCodeList = new ArrayList<>();
        String fileContent = PreprocessService.preprocessMctFile(environment.getMctFilePath(), cCodeList);
        CharStream stream = CharStreams.fromString(fileContent);

        MCTLexer lexer = new MCTLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MCTParser parser = new MCTParser(tokens);

        MCTParser.ProgContext tree = parser.prog();
        MCTObjectBuilder builder = new MCTObjectBuilder();

        TestProject testProject = (TestProject)builder.visit(tree);
        updateTestProjectCCode(testProject, cCodeList);

        ProjectEnvironment projectEnvironment = ProjectEnvironment.getInstance();
        projectEnvironment.setTestProject(testProject);
    }
}
