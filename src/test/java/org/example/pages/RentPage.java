package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RentPage extends BasePage {
    // Заголовок второго шага формы
    private final By rentHeader = By.xpath("//div[text()='Про аренду']");
    // Поле даты доставки
    private final By deliveryDateInput = By.cssSelector("input[placeholder='* Когда привезти самокат']");
    // Выпадающий список срока аренды
    private final By rentalPeriodDropdown = By.className("Dropdown-control");
    // Поле комментария для курьера
    private final By commentInput = By.cssSelector("input[placeholder='Комментарий для курьера']");
    // Кнопка «Заказать» в форме аренды
    private final By orderButton = By.xpath(
            "//div[contains(@class, 'Order_Buttons')]//button[text()='Заказать']");
    // Кнопка «Да» в окне подтверждения
    private final By confirmButton = By.xpath(
            "//div[contains(@class, 'Order_Modal')]//button[text()='Да']");
    // Заголовок окна об успешном создании заказа
    private final By successHeader = By.xpath(
            "//div[contains(@class, 'Order_ModalHeader') and contains(., 'Заказ оформлен')]");

    private By rentalPeriodOption(String period) {
        return By.xpath("//div[@class='Dropdown-option' and text()='" + period + "']");
    }

    private By colorCheckbox(String color) {
        return By.id(color);
    }

    public RentPage(WebDriver driver) {
        super(driver);
    }

    public void fillRentForm(String date, String period, String color, String comment) {
        fill(deliveryDateInput, date);
        click(rentHeader);
        click(rentalPeriodDropdown);
        click(rentalPeriodOption(period));
        click(colorCheckbox(color));
        fill(commentInput, comment);
    }

    public void submitOrder() {
        click(orderButton);
        click(confirmButton);
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successHeader)).getText();
    }
}
