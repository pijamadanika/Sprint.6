package org.example.tests;

import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        if ("chrome".equalsIgnoreCase(browser)) {
            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless=new");
            }
            driver = new ChromeDriver(options);
        } else if ("firefox".equalsIgnoreCase(browser)) {
            FirefoxOptions options = new FirefoxOptions().configureFromEnv();
            if (headless) {
                options.addArguments("-headless");
            }
            driver = new FirefoxDriver(options);
        } else {
            throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }

        driver.manage().window().setSize(new Dimension(1440, 1000));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
