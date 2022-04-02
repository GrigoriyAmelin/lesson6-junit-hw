package guru.qa;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ATreeInvoiceSearch {

    @BeforeEach
    void precondition() {

        Selenide.open("https://a-3.ru/");
        $("[data-testid='MainPageData-Gos-ShowAll']").scrollTo().click();
        sleep(1000);
        $("[data-testid='MainPageData-GosMigration-Link']").click();
        sleep(1000);
        $("[data-testid='Payment-H1']").shouldHave(text("Госпошлины миграционной службы"));
        $(".flocktory-widget-overlay");
        switchTo().frame("fl-297663");
        $("button[type='button']").click();
        switchTo().defaultContent();
        $("#number").scrollTo().click();
        sleep(1000);
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
}
