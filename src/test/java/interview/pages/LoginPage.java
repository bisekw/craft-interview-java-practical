package interview.pages;

import interview.support.Config;
import java.util.Objects;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/** Zadanie 1: uzupełnij cztery selektory. Metody są gotowe. */
public class LoginPage extends BasePage {
    private static final By USERNAME_INPUT = null; // TODO: login w formularzu gracza.
    private static final By PASSWORD_INPUT = null; // TODO: hasło w formularzu gracza; ID zmienia się po każdym załadowaniu.
    private static final By LOGIN_BUTTON = null; // TODO: Zaloguj w formularzu gracza.
    private static final By SUCCESS_MESSAGE = null; // TODO: komunikat wyniku logowania w formularzu gracza.

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openPage(String layout) {
        driver.get(Config.BASE_URL + "/?layout=" + layout);
    }

    public void login(String username, String password) {
        typeText(USERNAME_INPUT, username);
        typeText(PASSWORD_INPUT, password);
        click(LOGIN_BUTTON);
    }

    public boolean isLoggedIn() {
        Objects.requireNonNull(SUCCESS_MESSAGE, "Uzupełnij selector w LoginPage.java");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_MESSAGE));
            wait.until(ExpectedConditions.textToBe(SUCCESS_MESSAGE, "Zalogowano jako tester"));
            return true;
        } catch (TimeoutException error) {
            return false;
        }
    }
}
