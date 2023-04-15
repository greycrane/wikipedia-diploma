package dev.greycrane.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {
    public MainPage openPage() {
        open("/wiki/Main_Page");
        $(byText("From today's featured article")).shouldBe(visible);
        return this;
    }

    public MainPage openSearchField() {
        $("#p-search").click();
        $(".cdx-search-input__input-wrapper").shouldBe(visible);
        return this;
    }
}
