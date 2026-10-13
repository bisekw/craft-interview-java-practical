package interview.support;

import java.io.File;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

/** Gotowa obsługa Chrome. Selenium Manager dobiera driver, jeśli nie podano ścieżki. */
public final class BrowserFactory {
    private BrowserFactory() {
    }

    public static WebDriver create() {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getProperty("headless", "true"))) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1440,1000");
        String binary = System.getenv("QA_BROWSER_BINARY");
        if (binary != null && !binary.isBlank()) {
            options.setBinary(binary);
        }
        ChromeDriverService.Builder service = new ChromeDriverService.Builder();
        String driverPath = System.getenv("QA_DRIVER_PATH");
        if (driverPath != null && !driverPath.isBlank()) {
            service.usingDriverExecutable(new File(driverPath));
        }
        WebDriver driver = new ChromeDriver(service.build(), options);
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ZERO);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
            return driver;
        } catch (RuntimeException error) {
            try {
                driver.quit();
            } catch (RuntimeException cleanupError) {
                error.addSuppressed(cleanupError);
            }
            throw error;
        }
    }
}
