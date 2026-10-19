package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {
    private static final String URL = "https://qa-scooter.education-services.ru/";

    // Кнопка «Заказать» в шапке страницы
    private final By topOrderButton = By.xpath(
            "//div[contains(@class, 'Header_Nav')]//button[text()='Заказать']");
    // Кнопка «Заказать» в нижней части главной страницы
    private final By bottomOrderButton = By.xpath(
            "//div[contains(@class, 'Home_FinishButton')]//button[text()='Заказать']");
    // Кнопка принятия cookies
    private final By acceptCookiesButton = By.id("rcc-confirm-button");

    // Вопрос в разделе «Вопросы о важном», номер от 0 до 7
    private By question(int number) {
        return By.id("accordion__heading-" + number);
    }

    // Ответ, соответствующий вопросу с тем же номером
    private By answer(int number) {
        return By.id("accordion__panel-" + number);
    }

    public enum OrderButton {
        TOP, BOTTOM
    }

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL);
        click(acceptCookiesButton);
    }

    public boolean isAnswerVisible(int number) {
        return driver.findElement(answer(number)).isDisplayed();
    }

    public void openQuestion(int number) {
        click(question(number));
    }

    public String getAnswerText(int number) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(answer(number))).getText();
    }

    public CustomerPage clickOrderButton(OrderButton button) {
        click(button == OrderButton.TOP ? topOrderButton : bottomOrderButton);
        return new CustomerPage(driver);
    }
}
