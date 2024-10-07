package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testValidLogin() {
        loginPage.setUsername("tomsmith");
        loginPage.setPassword("SuperSecretPassword!");
        loginPage.clickLogin();
        String message = loginPage.getFlashMessageText();
        assertTrue(message.contains("You logged into a secure area!"));
    }

    @Test
    public void testInvalidPassword() {
        loginPage.setUsername("tomsmith");
        loginPage.setPassword("wrongpassword");
        loginPage.clickLogin();
        String message = loginPage.getFlashMessageText();
        assertTrue(message.contains("Your password is invalid!"));
    }

    @Test
    public void testInvalidUsername() {
        loginPage.setUsername("wrongusername");
        loginPage.setPassword("SuperSecretPassword!");
        loginPage.clickLogin();
        String message = loginPage.getFlashMessageText();
        assertTrue(message.contains("Your username is invalid!"));
    }

    @Test
    public void testEmptyFields() {
        loginPage.clickLogin();
        String message = loginPage.getFlashMessageText();
        assertTrue(message.contains("Your username is invalid!"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
