package io.github.raulstanciu1.services.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PreprocessService {
    private final static String[] keywords = {"COMMON_CODE", "PRE_TEST_CODE", "TEST_CODE", "POST_TEST_CODE"};
    public static String preprocessMctFile(Path mctFilePath, List<String> cCodeList) throws IOException {
        /*
         * Due to limitations of the MCT grammar, all COMMON_CODE, PRE_TEST_CODE, TEST_CODE and POST_TEST_CODE
         * content will be stored separately in the ProjectEnvironment's cCodeList
         *  will be replaced with numbers which indicate the index from cCodeList
         */
        String fileContent = Files.readString(mctFilePath);
        StringBuilder fileContentOutput = new StringBuilder();

        int index = 0;
        int codeBlockId = 0;

        while (index < fileContent.length()) {
            boolean matched = false;

            for (String keyword : keywords) {
                if (fileContent.startsWith(keyword, index)) {
                    index += keyword.length();

                    // Skip whitespace
                    while (index < fileContent.length() && Character.isWhitespace(fileContent.charAt(index))) index++;

                    if (index < fileContent.length() && fileContent.charAt(index) == '{') {
                        int braceStart = index;
                        int braceEnd = findMatchingBrace(fileContent, braceStart);
                        if (braceEnd == -1) throw new RuntimeException("Unmatched brace after " + keyword);

                        String blockContent = fileContent.substring(braceStart + 1, braceEnd);
                        cCodeList.add(blockContent);

                        // Append replacement
                        fileContentOutput.append(keyword).append(" { ").append(codeBlockId++).append(" }");

                        index = braceEnd + 1;
                        matched = true;
                        break;
                    }
                }
            }

            if (!matched) {
                fileContentOutput.append(fileContent.charAt(index));
                index++;
            }
        }


        return fileContentOutput.toString();
    }

    private static int findMatchingBrace(String input, int start) {
        int braceCount = 0;
        for (int i = start; i < input.length(); i++) {
            if (input.charAt(i) == '{') braceCount++;
            else if (input.charAt(i) == '}') braceCount--;

            if (braceCount == 0) return i;
        }
        return -1; // unmatched
    }
}
