package interview;

import interview.support.BrowserFactory;
import interview.support.Config;
import io.restassured.path.json.JsonPath;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Gotowa kontrola środowiska, niezależna od rozwiązań zadań. */
@Tag("ready")
class EnvironmentTest {
    @Test
    void applicationIsReady() throws Exception {
        HttpClient client = HttpClient.newBuilder().connectTimeout(Config.TIMEOUT).build();
        HttpRequest request = HttpRequest.newBuilder(URI.create(Config.BASE_URL + "/health"))
                .timeout(Config.TIMEOUT).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode(), "Uruchom app/run.py zgodnie z README.md");
        assertEquals("ok", JsonPath.from(response.body()).getString("status"));
    }

    @Test
    void browserIsReady() {
        WebDriver driver = BrowserFactory.create();
        try {
            driver.get(Config.BASE_URL);
            assertEquals("Logowanie", driver.findElement(By.id("app-title")).getText());
        } finally {
            driver.quit();
        }
    }
}
