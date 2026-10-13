package interview.pages;

import interview.support.Config;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Gotowe operacje na elementach z oczekiwaniem na właściwy stan. */
public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Config.TIMEOUT);
    }

    protected void typeText(By locator, String value) {
        Objects.requireNonNull(locator, "Uzupełnij selector w LoginPage.java");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(value);
    }

    protected void click(By locator) {
        Objects.requireNonNull(locator, "Uzupełnij selector w LoginPage.java");
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}
