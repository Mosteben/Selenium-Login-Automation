package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void setUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        driver.findElement(By.name("username")).sendKeys(username);
    }

    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='password']")));
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(By.className("radius")));
        driver.findElement(By.className("radius")).click();
    }

    public String getFlashMessageText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        return driver.findElement(By.id("flash")).getText();
    }
}
