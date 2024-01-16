package toby.springbook.study.code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderTemplate {

    public int fileReadTemplate(String filePath, BufferedReaderCallback callback) throws IOException{
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            return callback.doSomethingWithReader(bufferedReader);
        } catch (IOException e) {
            throw e;
        } finally {
            if(bufferedReader != null) {
                try{
                    bufferedReader.close();
                } catch (IOException e){
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    public <T> T lineReadTemplate(String filePath, LineCallback<T> lineCallback, T initVal) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            T result = initVal;
            String line;
            while((line = bufferedReader.readLine()) != null) {
                result = lineCallback.doSomethingWithLine(line, result);
            }
            return result;
        } catch (IOException e) {
            throw e;
        } finally {
            if(bufferedReader != null) {
                try{
                    bufferedReader.close();
                } catch (IOException e){
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
