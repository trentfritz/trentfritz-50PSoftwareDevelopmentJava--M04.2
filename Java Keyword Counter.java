import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class KeywordCounter {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java KeywordCounter <filename>");
            return;
        }

        String fileName = args[0];

        Set<String> keywords = new HashSet<>();
        keywords.add("abstract");
        keywords.add("assert");
        keywords.add("boolean");
        keywords.add("break");
        keywords.add("byte");
        keywords.add("case");
        keywords.add("catch");
        keywords.add("char");
        keywords.add("class");
        keywords.add("const");
        keywords.add("continue");
        keywords.add("default");
        keywords.add("do");
        keywords.add("double");
        keywords.add("else");
        keywords.add("enum");
        keywords.add("extends");
        keywords.add("final");
        keywords.add("finally");
        keywords.add("float");
        keywords.add("for");
        keywords.add("if");
        keywords.add("implements");
        keywords.add("import");
        keywords.add("instanceof");
        keywords.add("int");
        keywords.add("interface");
        keywords.add("long");
        keywords.add("native");
        keywords.add("new");
        keywords.add("package");
        keywords.add("private");
        keywords.add("protected");
        keywords.add("public");
        keywords.add("return");
        keywords.add("short");
        keywords.add("static");
        keywords.add("strictfp");
        keywords.add("super");
        keywords.add("switch");
        keywords.add("synchronized");
        keywords.add("this");
        keywords.add("throw");
        keywords.add("throws");
        keywords.add("transient");
        keywords.add("try");
        keywords.add("void");
        keywords.add("volatile");
        keywords.add("while");

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean inComment = false;
            boolean inString = false;
            int keywordCount = 0;

            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '"') {
                        inString = !inString;
                    }

                    if (!inString) {
                        if (line.charAt(i) == '/' && i < line.length() - 1 && line.charAt(i + 1) == '*') {
                            inComment = true;
                            i++;
                        } else if (line.charAt(i) == '*' && i < line.length() - 1 && line.charAt(i + 1) == '/') {
                            inComment = false;
                            i++;
                        }

                        if (!inComment) {
                            StringBuilder word = new StringBuilder();
                            while (i < line.length() && Character.isLetterOrDigit(line.charAt(i))) {
                                word.append(line.charAt(i));
                                i++;
                            }
                            if (keywords.contains(word.toString())) {
                                keywordCount++;
                            }
                        }
                    }
                }
            }

            System.out.println("Number of keywords: " + keywordCount);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
