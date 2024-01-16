package toby.springbook.study.code;

import java.io.IOException;

public class Calculator {
    private final BufferedReaderTemplate template;

    public Calculator(BufferedReaderTemplate template) {
        this.template = template;
    }

    public int calcSum(String filePath) throws IOException {
        LineCallback<Integer> lineCallback = (line, value) -> value + Integer.parseInt(line);
        return template.lineReadTemplate(filePath, lineCallback, 0);
    }

    public int calcMultiple(String filePath) throws IOException {
        LineCallback<Integer> lineCallback = (line, value) -> value * Integer.parseInt(line);
        return template.lineReadTemplate(filePath, lineCallback, 1);
    }

    public String concat(String filePath) throws IOException{
        LineCallback<String> lineCallback = ((line, value) -> value.concat(line));
        return template.lineReadTemplate(filePath, lineCallback, "");
    }
}
