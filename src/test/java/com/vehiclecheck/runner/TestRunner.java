package com.vehiclecheck.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "html:target/site/cucumber-pretty.html",
                "json:target/cucumber/cucumber.json"},
        features = {"classpath:features/"},
        glue = {"com.vehiclecheck.stepdefs"},
        monochrome = true
)
public class TestRunner {

}
