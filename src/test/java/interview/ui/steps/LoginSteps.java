package interview.ui.steps;

import interview.support.Config;
import interview.ui.context.UiContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

/** Zadanie 3: kroki scenariusza features/ui/Login.feature. */
public class LoginSteps {
    private final UiContext context;

    public LoginSteps(UiContext context) {
        this.context = context;
    }

    @Given("I open the login page in {string} layout")
    public void iOpenTheLoginPageInLayout(String layout) {
        context.loginPage().openPage(layout);
    }

    // Odkomentuj kroki w Login.feature i dopisz poniżej ich definicje.
    // Dane gracza są w Config.VALID_USER i Config.VALID_PASSWORD.
}
