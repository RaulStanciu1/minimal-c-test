package io.github.raulstanciu1.services;

import io.github.raulstanciu1.exceptions.InvalidEnvironmentException;
import io.github.raulstanciu1.models.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class TestGenerator {
    private static Path outputDirectoryPath;
    private static TestProject testProject;

    public static void generateTestFiles() throws IOException, InvalidEnvironmentException {
        ProjectEnvironment pe = ProjectEnvironment.getInstance();
        Path tmpDirPath = pe.getSourcePaths().getTempDirPath();
        outputDirectoryPath = tmpDirPath.resolve("out");
        pe.setOutputDirectory(outputDirectoryPath);
        Files.createDirectories(outputDirectoryPath);
        Files.createDirectories(outputDirectoryPath.resolve("res"));
        testProject = pe.getTestProject();
        copySourceFiles(tmpDirPath, outputDirectoryPath);
        generateCommonHeaderFile();
        TestProject tp = ProjectEnvironment.getInstance().getTestProject();
        for(int i = 0; i < tp.getTests().size(); i++){
            Files.createDirectories(outputDirectoryPath.resolve("res").resolve(String.valueOf(i)));
            generateEnvironmentHeaderFiles(i, tp.getTests().get(i));
            generateTestSourceFile(i, tp.getTests().get(i));
        }
        Utils.copyResourceFile("___mct___.c", outputDirectoryPath);
        Utils.copyResourceFile("___mct___.h", outputDirectoryPath);
        replaceKeyWords();
        insertTestCaseCalls();
    }

    private static void copySourceFiles(Path srcDir, Path targetDir) throws IOException{
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(srcDir, "*.c")) {
            for (Path file : stream) {
                if (Files.isRegularFile(file)) {
                    Path destinationFile = targetDir.resolve(file.getFileName());
                    Files.copy(file, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }

    private static void generateCommonHeaderFile() throws IOException {
        Path commonHeaderPath = outputDirectoryPath.resolve("___mct_common_src___.h");
        List<String> commonHeaderContent = new ArrayList<>();
        commonHeaderContent.add("#ifndef MTC_COMMON_SRC_H");
        commonHeaderContent.add("#define MTC_COMMON_SRC_H");
        List<String> commonCode = List.of(testProject.getCommonCode().split("\n\r"));
        commonHeaderContent.addAll(commonCode);
        commonHeaderContent.add("#endif");
        Files.write(commonHeaderPath, commonHeaderContent, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }

    private static void generateEnvironmentHeaderFiles(int testIndex, Test test) throws InvalidEnvironmentException, IOException{
        for(TestEnvironment env : test.getEnvironments()){
            Path envFilePath = outputDirectoryPath.resolve("___mct_env_"+testIndex+"_"+env.getName()+"___.h");
            List<String> envFileContent = new ArrayList<>();
            envFileContent.add("void ___mct__"+env.getName()+"_IN(){");
            for(TestVariable testVariable: env.getTestVariables()){
                if(!testVariable.getOutputs().isEmpty()){
                    throw new InvalidEnvironmentException("Environment variables cannot be outputs");
                }
                if(testVariable.getInputs().size() != 1){
                    throw new InvalidEnvironmentException("Environment variables cannot have multiple inputs");
                }else {
                    envFileContent.add("\t"+testVariable.getName()+ " = "+testVariable.getInputs().getFirst()+";");
                }
            }
            for(TestString testString: env.getTestStrings()) {
                if(!testString.getOutputs().isEmpty()){
                    throw new InvalidEnvironmentException("Environment variables cannot be outputs");
                }
                if (testString.getInputs().size() != 1) {
                    throw new InvalidEnvironmentException("Environment variables cannot have multiple outputs");
                } else {
                    envFileContent.add("\tstrcpy(" + testString.getName() + ", \"" + testString.getInputs().getFirst() + "\");");
                }
            }
            envFileContent.add("}");
            Files.write(envFilePath, envFileContent, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        }
    }

    private static void generateTestSourceFile(int testIndex, Test test) throws InvalidEnvironmentException, IOException {
        Path testFilePath = outputDirectoryPath.resolve("___mct_"+testIndex+"___.c");
        List<String> testFileContents = new ArrayList<>();
        for(int i = 0; i < test.getTestCases().size(); i++){
            testFileContents.addAll(generateTestCaseContent(testIndex, i, test.getTestCases().get(i)));
        }
        Files.write(testFilePath, testFileContents, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }

    private static List<String> generateTestCaseContent(int testIndex, int testCaseIndex, TestCase testCase) throws InvalidEnvironmentException{
        List<String> testCaseContentList = new ArrayList<>();
        testCaseContentList.add("void ___mct_tc_"+testIndex+"_"+testCaseIndex+"___() {");
        //Set up the mct environment
        testCaseContentList.add("\tif(___mct_setup(\"res/"+testIndex+"/___mct_tc_"+testCaseIndex+"_res.txt\") == -1){ exit(1); }");

        //Add the environment functions
        for(String env: testCase.getEnvironmentsUsed()){
            boolean validEnv = false;
            for(TestEnvironment te: testProject.getTests().get(testIndex).getEnvironments()){
                if(te.getName().equals(env)){
                    validEnv = true;
                    break;
                }
            }
            if(!validEnv){
                throw new InvalidEnvironmentException("Used environment doesn't exist");
            }
            testCaseContentList.add("\t___mct__"+env+"_IN();");
        }

        for(int i = 0; i < testCase.getSteps(); i++){

            //Add the test step checkpoint begin
            testCaseContentList.add("\tMCT_STEP_CHECKPOINT_BEGIN("+i+");");

            //Add the pre-test code
            testCaseContentList.add(testCase.getPreTestCode());

            //Add the inputs
            for(TestVariable testVar: testCase.getTestVariables()){
                if(!testVar.getInputs().isEmpty()){
                    testCaseContentList.add("\t"+testVar.getName() + " = "+ testVar.getInputs().get(i)+";");
                }
            }
            for(TestString testString: testCase.getTestStrings()){
                if(!testString.getInputs().isEmpty()){
                    testCaseContentList.add("\tstrcpy("+testString.getName()+", "+testString.getInputs().get(i)+");");
                }
            }

            //Add the test code
            testCaseContentList.add(testCase.getTestCode());

            //Add the outputs
            for(TestVariable testVar: testCase.getTestVariables()){
                if(!testVar.getOutputs().isEmpty()){
                    if(testVar.getDelta() != null){
                        testCaseContentList.add("\tMCT_ASSERT_EQ_DELTA("+testVar.getName()+","+testVar.getOutputs().get(i)+","+testVar.getDelta()+");");
                    }else{
                        testCaseContentList.add("\tMCT_ASSERT_EQ("+testVar.getName()+","+testVar.getOutputs().get(i)+");");
                    }
                }
            }
            for(TestString testString: testCase.getTestStrings()){
                if(!testString.getOutputs().isEmpty()){
                    testCaseContentList.add("\tMCT_ASSERT_EQ_STR("+testString.getName()+","+testString.getOutputs().get(i)+");");
                }
            }

            //Add the post-test code
            testCaseContentList.add(testCase.getPostTestCode());

            //Add the test step checkpoint end
            testCaseContentList.add("\tMCT_STEP_CHECKPOINT_END("+i+");");
        }

        //Clean up the mct environment
        testCaseContentList.add("\t___mct_cleanup();");
        testCaseContentList.add("}");
        return testCaseContentList;
    }


    private static void replaceKeyWords() throws IOException{
        Path mctCFile = outputDirectoryPath.resolve("___mct___.c");
        String fileContents = Files.readString(mctCFile);
        int srcFilesNbr = ProjectEnvironment.getInstance().getSourcePaths().getCFilePaths().size();
        //Replace the number of tests
        fileContents = Utils.replaceWord(fileContents,"___MCT__TEST__N___",String.valueOf(testProject.getTests().size()));

        //Add the source file includes
        StringBuilder srcFilesIncludes = new StringBuilder();
        for(int i = 0; i < srcFilesNbr; i++){
            srcFilesIncludes.append("#include \"___src_").append(i).append("___.c\"\n");
        }
        fileContents = Utils.replaceWord(fileContents,"___MCT__ADD__SRC__FILES__INCLUDES___",srcFilesIncludes.toString());

        //Add the environment source file includes
        StringBuilder envFilesIncludes = new StringBuilder();
        for(int i = 0; i < testProject.getTests().size(); i++){
            for(TestEnvironment te: testProject.getTests().get(i).getEnvironments()){
                envFilesIncludes.append("#include \"___mct_env_").append(i).append("_").append(te.getName()).append("___.h \"\n");
            }
        }
        fileContents = Utils.replaceWord(fileContents,"___MCT__ADD_ENV__FILES__INCLUDES___",envFilesIncludes.toString());

        //Add the test case file includes
        StringBuilder tcFilesIncludes = new StringBuilder();
        for(int i = 0; i < srcFilesNbr; i++){
            tcFilesIncludes.append("#include \"___mct_").append(i).append("___.c\"\n");
        }
        fileContents = Utils.replaceWord(fileContents,"___MCT__ADD__TEST__FILES__INCLUDES___",tcFilesIncludes.toString());

        Files.writeString(mctCFile, fileContents);
    }

    private static void insertTestCaseCalls() throws IOException {
        Path mctCFile = outputDirectoryPath.resolve("___mct___.c");
        String fileContents = Files.readString(mctCFile);
        StringBuilder testRunFunc = new StringBuilder("void __MCT__TEST_RUN__(int t){\n");
        testRunFunc.append("\tswitch(t){\n");
        for(int i = 0; i < testProject.getTests().size(); i++){
            testRunFunc.append("\t\tcase ").append(i).append(":\n\t\t{\n");
            for(int j = 0; j < testProject.getTests().get(i).getTestCases().size(); j++){
                testRunFunc.append("\t\t\t___mct_tc_").append(i).append("_").append(j).append("___();\n");
            }
            testRunFunc.append("\t\t\tbreak;\n\t\t}\n");
        }
        testRunFunc.append("\t}\n}\n\n\n");

        fileContents = Utils.insertBeforeWord(fileContents,"int main", testRunFunc.toString());

        Files.writeString(mctCFile, fileContents);
    }
}
