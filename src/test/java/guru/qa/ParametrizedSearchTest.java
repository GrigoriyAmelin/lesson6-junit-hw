package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;

public class ParametrizedSearchTest {

    @BeforeEach
    void precondition() {
        Selenide.open("https://ya.ru/");
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @ValueSource(strings = {"Selenide", "JUnit 5"})
    @ParameterizedTest(name = "Проверка отображаемых результатов в яндексе для запроса \"{0}\"")
    void commonSearchTest(String testData) {
        Selenide.$("#text").setValue(testData);
        Selenide.$("button[type='submit']").click();
        Selenide.$$("li.serp-item").find(text(testData)).shouldBe(visible);
    }

    @ValueSource(ints = {1, 5})
    @ParameterizedTest(name = "Проверка отображаемых результатов в яндексе для запроса \"{0}\"")
    void commonSearchTestInts(int testData) {
        Selenide.$("#text").setValue(String.valueOf(testData));
        Selenide.$("button[type='submit']").click();
        Selenide.$$("li.serp-item").find(text(String.valueOf(testData))).shouldBe(visible);
    }

    @CsvSource(value = {
            "Selenide| concise UI tests in Java",
            "JUnit 5| IntelliJ IDEA"
    },
            delimiter = '|'
    )
    @ParameterizedTest(name = "Проверка отображаемых результатов в яндексе для запроса \"{0}\"")
    void complexSearchTest(String testData, String expectedText) {
        Selenide.$("#text").setValue(testData);
        Selenide.$("button[type='submit']").click();
        Selenide.$$("li.serp-item").find(text(expectedText)).shouldBe(visible);
    }

    static Stream<Arguments> mixedArgumentsTestingDataProvider() {
        return Stream.of(
                Arguments.of("Selenide", List.of(1, 2, 3), true),
                Arguments.of(("JUnit 5"), List.of(5, 6, 7), false)
        );
    }

    @MethodSource(value = "mixedArgumentsTestingDataProvider")
    @ParameterizedTest(name = "Name {1}")
    void mixedArgumentsTest(String firstArg, List<Integer> secondArg, boolean aBooleanValue) {
        System.out.println("String: " + firstArg + "list: " + secondArg.toString() + " boolean: " + aBooleanValue);
    }
}
