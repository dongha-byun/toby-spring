package toby.springbook.study;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class JUnitTest {
    static Set<JUnitTest> testObjects = new HashSet<>();

    @Test void test1() {
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);
    }

    @Test void test2() {
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);
    }

    @Test void test3() {
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);
    }
}
