package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomerPage extends BasePage {
    // Поле «Имя»
    private final By firstNameInput = By.cssSelector("input[placeholder='* Имя']");
    // Поле «Фамилия»
    private final By lastNameInput = By.cssSelector("input[placeholder='* Фамилия']");
    // Поле адреса доставки
    private final By addressInput = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");
    // Поле выбора станции метро
    private final By metroInput = By.cssSelector("input[placeholder='* Станция метро']");
    // Поле телефона
    private final By phoneInput = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка перехода к аренде
    private final By nextButton = By.xpath("//button[text()='Далее']");

    // Станция метро в выпадающем списке
    private By metroStation(String station) {
        return By.xpath("//button[contains(@class, 'select-search__option')]"
                + "[.//div[text()='" + station + "']]");
    }

    public CustomerPage(WebDriver driver) {
        super(driver);
    }

    public void fillCustomerForm(String firstName, String lastName, String address,
                                 String metro, String phone) {
        fill(firstNameInput, firstName);
        fill(lastNameInput, lastName);
        fill(addressInput, address);
        fill(metroInput, metro);
        click(metroStation(metro));
        fill(phoneInput, phone);
    }

    public RentPage clickNext() {
        click(nextButton);
        return new RentPage(driver);
    }
}
