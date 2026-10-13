package interview.ui.hooks;

import interview.support.BrowserFactory;
import interview.ui.context.UiContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

/** Gotowe: każdy scenariusz UI dostaje nową przeglądarkę, zamykaną po scenariuszu. */
public class Hooks {
    private final UiContext context;

    public Hooks(UiContext context) {
        this.context = context;
    }

    @Before("@ui")
    public void openBrowser() {
        context.open(BrowserFactory.create());
    }

    @After("@ui")
    public void closeBrowser() {
        context.close();
    }
}
