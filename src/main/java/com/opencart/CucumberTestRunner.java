package com.opencart;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/java/com/opencart/features",
        glue = "com/opencart/stepdefinitions",
        tags = ""
)
public class CucumberTestRunner {

}
