package toby.springbook.study.code;

public interface LineCallback<T> {

    T doSomethingWithLine(String line, T value);
}
