package toby.springbook.study;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import toby.springbook.study.code.BufferedReaderTemplate;
import toby.springbook.study.code.Calculator;

public class CalculatorTest {

    @Test
    @DisplayName("numbers.txt 파일의 합을 구한다.")
    void add_all_of_number_txt() throws IOException {
        Calculator calculator = new Calculator(new BufferedReaderTemplate());
        int sum = calculator.calcSum(getClass().getResource("/numbers.txt").getPath());

        assertThat(sum).isEqualTo(10);
    }

    @Test
    @DisplayName("numbers.txt 파일의 곱을 구한다.")
    void multiple_all_of_number_txt() throws IOException {
        Calculator calculator = new Calculator(new BufferedReaderTemplate());
        int sum = calculator.calcMultiple(getClass().getResource("/numbers.txt").getPath());

        assertThat(sum).isEqualTo(24);
    }

    @Test
    @DisplayName("numbers.txt 파일의 내용을 모두 문자열로 합친다.")
    void concat_all_of_number_txt() throws IOException {
        Calculator calculator = new Calculator(new BufferedReaderTemplate());
        String result = calculator.concat(getClass().getResource("/numbers.txt").getPath());

        assertThat(result).isEqualTo("1234");
    }
}
