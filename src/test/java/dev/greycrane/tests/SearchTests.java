package dev.greycrane.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import dev.greycrane.helpers.Attachments;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.*;

@Tag("tinkoff")
public class SearchTests {
    @BeforeAll
    static void configTests() {
        TestBase testBase = new TestBase();
        testBase.configureTests();
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Test
    @Owner("szhuravlev")
    @Severity(BLOCKER)
    @DisplayName("Проверка возможности начать оформление дебетовой карты")
    @Story("Проверка возможности начать оформление дебетовой карты")
    void checkGetDebitCardButton() {
        step("Открыть главную страницу tinkoff.ru", () -> {
                    open("/");
                });
        step("Нажать на кнопку \"Оформить карту\"", () -> {
            $(byText("Оформить карту")).click();
        });
        step("Проверить, что можно выбрать дизайн карты", () -> {
            $(byText("Выберите дизайн карты")).shouldBe(visible);
        });
        step("Открыть главную страницу tinkoff.ru", () -> {

        });
    }

    @Test
    @Owner("szhuravlev")
    @Severity(CRITICAL)
    @DisplayName("Проверка наличия опроса по подбору тарифа мобильной связи")
    @Story("Проверка наличия опроса по подбору тарифа мобильной связи")
    void checkMobileTariffSurvey() {
        step("Открыть страницу \"Сим-карта\"", () -> {
            open("/mobile-operator");
        });
        step("Проверить текст заголовка \"Безлимит от Тинькофф\"", () -> {
            $(byText("Безлимит от Тинькофф")).shouldBe(visible);
        });
    }

    @Test
    @Owner("szhuravlev")
    @Severity(BLOCKER)
    @DisplayName("Проверка перехода на открытие ИИС")
    @Story("Проверка перехода на открытие ИИС")
    void checkInvestmentsPage() {
        step("Открыть страницу \"Инвестиции\"", () -> {
            open("/invest");
        });
        step("Проверить текст заголовка", () -> {
            $(byText("Инвестируйте в ценные бумаги")).shouldBe(visible);
        });
        step("Нажать на кнопку \"Открыть ИИС\"", () -> {
            $(byText("Открыть ИИС")).click();
        });
        step("Проверить текст заголовка", () -> {
            $(byText("Счет для инвестирования с возможностью получить +13% через налоговый вычет"))
                    .scrollTo()
                    .shouldBe(visible);
        });
    }

    @Test
    @Owner("szhuravlev")
    @Severity(CRITICAL)
    @DisplayName("Проверка наличия всех страховых продуктов")
    @Story("Проверка наличия всех страховых продуктов")
    void checkInsurancePage() {
        step("Открыть страницу \"Страхование\"", () -> {
            open("/insurance");
        });
        step("Проверить текст заголовка", () -> {
            $(byText("Страховые продукты Тинькофф")).shouldBe(visible);
        });
        step("Проверить наличие продукта \"Электронное ОСАГО\"", () -> {
            $(byText("Электронное ОСАГО")).shouldBe(visible);
        });
        step("Проверить наличие продукта \"Автострахование КАСКО\"", () -> {
            $(byText("Автострахование КАСКО")).shouldBe(visible);
        });
        step("Проверить наличие продукта \"Страхование для туристов\"", () -> {
            $(byText("Страхование для туристов")).shouldBe(visible);
        });
        step("Проверить наличие продукта \"Страхование ипотеки\"", () -> {
            $(byText("Страхование ипотеки")).shouldBe(visible);
        });
        step("Проверить наличие продукта \"Страхование квартиры\"", () -> {
            $(byText("Страхование квартиры")).shouldBe(visible);
        });
        step("Проверить наличие продукта \"Страхование от несчастных случаев\"", () -> {
            $(byText("Страхование от несчастных случаев")).shouldBe(visible);
        });
    }

    @Test
    @Owner("szhuravlev")
    @Severity(NORMAL)
    @DisplayName("Проверка наличия ошибки при неверном номере телефона при входе в ЛК")
    @Story("Проверка наличия ошибки при неверном номере телефона при входе в ЛК")
    void checkIncorrectLoginErrorAppears() {
        step("Открыть страницу входа в ЛК", () -> {
            open("/login");
        });
        step("Проверить текст заголовка", () -> {
            $(byText("Вход в Тинькофф")).shouldBe(visible);
        });
        step("Ввести некорректный номер телефона", () -> {
            $("#phoneNumber").click();
            $("#phoneNumber").append("5644654754764764");
        });
        step("Нажать \"Продолжить\"", () -> {
            $(byText("Продолжить")).click();
        });
        step("Проверить, что появилась ошибка", () -> {
            $("#formError").shouldBe(visible);
        });
    }

    @AfterEach
    void addAttachments() {
        Attachments.screenshotAs("Final screenshot");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        Attachments.addVideo();
    }
}
