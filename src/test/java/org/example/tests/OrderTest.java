package org.example.tests;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.example.pages.CustomerPage;
import org.example.pages.MainPage;
import org.example.pages.MainPage.OrderButton;
import org.example.pages.RentPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest extends BaseTest {
    @ParameterizedTest(name = "Заказ через кнопку {0}: {1} {2}")
    @CsvSource(value = {
            "TOP | Ташен | Данияр | Москва, улица Тестовая, дом 10 | Сокольники | 79991234567 | 1 | сутки | black | Позвонить за 10 минут",
            "BOTTOM | Колесин | Крыша | Москва, улица Учебная, дом 25 | Черкизовская | 89997654321 | 2 | трое суток | grey | Оставить у подъезда"
    }, delimiter = '|')
    public void shouldCreateOrder(OrderButton button, String firstName, String lastName,
                                  String address, String metro, String phone, int daysUntilDelivery,
                                  String rentalPeriod, String color, String comment) {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        CustomerPage customerPage = mainPage.clickOrderButton(button);
        customerPage.fillCustomerForm(firstName, lastName, address, metro, phone);
        RentPage rentPage = customerPage.clickNext();

        String deliveryDate = LocalDate.now(ZoneId.of("Europe/Moscow"))
                .plusDays(daysUntilDelivery)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        rentPage.fillRentForm(deliveryDate, rentalPeriod, color, comment);
        rentPage.submitOrder();

        assertTrue(rentPage.getSuccessMessage().contains("Заказ оформлен"),
                "После подтверждения должно появиться окно об успешном создании заказа");
    }
}
