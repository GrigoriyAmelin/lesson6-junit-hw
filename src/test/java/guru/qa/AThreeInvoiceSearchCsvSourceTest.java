package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AThreeInvoiceSearchCsvSourceTest {

    @BeforeEach
    void precondition() {

        Configuration.browserSize = "1920x1080";
        Selenide.open("https://www.a-3.ru/payment/6887");
        sleep(2000);
        $("[data-testid='UserDataForm-SubmitButton']").click();
        $(byText("Государственная пошлина за выдачу паспорта гражданина РФ")).scrollTo().click();
        sleep(1000);
    }

    @AfterEach
    void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @CsvSource(value = {
            "7706012716/ 45381000/ 3",
            "7707089101/ 45339000/ 3",
    },
            delimiter = '/'
    )
    @ParameterizedTest(name = "Проверка ввода корректного ИНН \"{0}\" и ОКТМО подразделения ФМС")
    void divisionFmsSearch(String inn, String oktmo, String service) {

        $$("[data-testid='Input']").get(0).setValue(inn);
        $$("[data-testid='Input']").get(1).setValue(oktmo);
        sleep(1000);
        $("[data-testid='UserDataForm-SubmitButton']").click();
        $("[data-testid='UserDataForm-Section-Inputs']").$(byText("Услуга"))
                .parent().shouldHave(text(service));
        $("[data-testid='UserDataForm-Section-Inputs']").$(byText("ИНН подразделения"))
                .parent().shouldHave(text(inn));
        $("[data-testid='UserDataForm-Section-Inputs']").$(byText("ОКТМО подразделения"))
                .parent().shouldHave(text(oktmo));
        sleep(1000);
    }
}
