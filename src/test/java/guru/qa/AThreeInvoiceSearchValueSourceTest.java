package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AThreeInvoiceSearchValueSourceTest {

    @BeforeEach
    void precondition() {

        Configuration.browserSize = "1920x1080";
        Selenide.open("https://www.a-3.ru/payment/6887");
        $("#number").scrollTo().click();
        sleep(2000);
        $("[data-testid='UserDataForm-SubmitButton']").click();
        $(byText("Государственная пошлина за выдачу паспорта гражданина РФ")).scrollTo().click();
        sleep(1000);
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @ValueSource(strings = {"1145613", "1145001"})
    @ParameterizedTest(name = "Проверка ввода корректного номера подразделения ФМС \"{0}\"")
    void divisionFmsSearch(String testData) {

        $("[data-testid='Input']").scrollTo().setValue(testData);
        sleep(1000);
        $("[data-testid='UserDataForm-SubmitButton']").click();
        $("[data-testid='UserDataForm-Section-Inputs']").$(byText("Услуга"))
                .parent().shouldHave(text("3"));
        $("[data-testid='UserDataForm-Section-Inputs']").$(byText("Номер подразделения"))
                .parent().shouldHave(text(testData));
        sleep(1000);
    }

    @ValueSource(ints = {1145019, 1145025})
    @ParameterizedTest(name = "Проверка ввода корректного номера подразделения ФМС \"{0}\"")
    void divisionFmsSearchWithInts(int testData) {

        $("[data-testid='Input']").scrollTo().setValue(String.valueOf(testData));
        sleep(1000);
        $("[data-testid='UserDataForm-SubmitButton']").click();
        $("[data-testid='UserDataForm-Section-Inputs']").$(byText("Услуга"))
                .parent().shouldHave(text("3"));
        $("[data-testid='UserDataForm-Section-Inputs']").$(byText("Номер подразделения"))
                .parent().shouldHave(text(String.valueOf(testData)));
        sleep(1000);
    }
}
