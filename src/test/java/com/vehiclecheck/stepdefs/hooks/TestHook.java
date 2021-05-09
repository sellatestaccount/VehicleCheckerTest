package com.vehiclecheck.stepdefs.hooks;

import com.vehiclecheck.contexts.TestContext;
import io.cucumber.java.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestHook {
    TestContext testContext;
    private static final Logger log = LoggerFactory.getLogger(TestHook.class);

    public TestHook(TestContext context) {
        this.testContext = context;
    }

    @After(order = 1)
    public void quitBrowser() {
        log.info("TestHook:quitBrowser ENTERING THE AFTER ORDER 1");
        testContext.getWebDriverManager().closeDriver();
    }
}
