package interview.api.steps;

import interview.support.Config;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

/** Zadanie 2: kroki scenariusza features/api/PlayerApi.feature. */
public class PlayerApiSteps {
    private Response response;

    @When("I request player {string}")
    public void iRequestPlayer(String playerId) {
        // Wykonaj GET Config.BASE_URL + "/api/players/" + playerId z jawnym timeoutem.
        // Zapisz odpowiedź w polu response, aby sprawdziły ją kolejne kroki.
        // REST Assured jest dostępny. Możesz też użyć java.net.http.HttpClient
        // i zmienić typ pola.
    }

    // Odkomentuj kroki w PlayerApi.feature i dopisz poniżej ich definicje.
}
