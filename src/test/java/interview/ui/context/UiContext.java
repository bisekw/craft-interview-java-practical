package interview.ui.context;

import interview.pages.LoginPage;
import org.openqa.selenium.WebDriver;

/** Stan jednego scenariusza UI. PicoContainer tworzy nowy obiekt dla każdego scenariusza. */
public class UiContext {
    private WebDriver driver;
    private LoginPage loginPage;

    public void open(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    }

    public LoginPage loginPage() {
        if (loginPage == null) {
            throw new IllegalStateException("Przeglądarka nie jest uruchomiona. Scenariusz UI wymaga tagu @ui.");
        }
        return loginPage;
    }

    public void close() {
        if (driver != null) {
            driver.quit();
            driver = null;
            loginPage = null;
        }
    }
}
