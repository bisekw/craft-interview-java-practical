package interview.support;

import java.time.Duration;

/** Gotowa konfiguracja lokalnej aplikacji demo. */
public final class Config {
    public static final String BASE_URL = System.getProperty("baseUrl",
            System.getenv().getOrDefault("QA_BASE_URL", "http://127.0.0.1:8000"))
            .strip().replaceAll("/+$", "");
    public static final String VALID_USER = "tester";
    public static final String VALID_PASSWORD = "Interview123!";
    public static final Duration TIMEOUT = Duration.ofSeconds(8);

    private Config() {
    }
}
