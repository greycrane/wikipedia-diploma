package dev.greycrane.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {
    public MainPage openPage() {
        open("/");
        $(byText("Рекомендуемые продукты")).shouldBe(visible);
        return this;
    }
}
