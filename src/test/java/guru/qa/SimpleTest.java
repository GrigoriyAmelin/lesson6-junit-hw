package guru.qa;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.channels.IllegalSelectorException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("SimpleTest")
class SimpleTest {

//    @Test
//    void simpleTest() {
//        System.out.println("Hello, world!");
//    }

    @Test
    @DisplayName("SimpleGreenTest")
    void simpleGreenTest() {
        assertTrue(2 > 1);
    }

    @Test
    @DisplayName("SimpleRedTest")
    void simpleRedTest() {
        assertTrue(2 < 1);
    }

    @Test
    @Disabled
//    @DisplayName("SimpleBrokenTest")
    void simpleBrokenTest() {
        throw new IllegalStateException("Broken");
    }
}
