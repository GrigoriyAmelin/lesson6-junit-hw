package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("Проверка отображаемых результатов в яндексе для запроса \"Selenide\"")
    void selenideSearchTest() {
        // Шаги
        Selenide.$("#text").setValue("Selenide");
        Selenide.$("button[type='submit']").click();
        Selenide.$$("li.serp-item").find(text("Selenide")).shouldBe(visible);
    }

    @Test
    @DisplayName("Проверка отображаемых результатов в яндексе для запроса \"JUnit 5\"")
    void junitSearchTest() {
        // Шаги
        Selenide.$("#text").setValue("JUnit 5");
        Selenide.$("button[type='submit']").click();
        Selenide.$$("li.serp-item").find(text("JUnit 5")).shouldBe(visible);
    }

}
