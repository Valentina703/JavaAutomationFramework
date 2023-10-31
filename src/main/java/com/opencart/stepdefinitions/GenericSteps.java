package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class GenericSteps {

    WebDriver driver  = DriverManager.getInstance().getDriver();
    @Then("the current url contains {string} keyword")
    public void theCurrentUrlContainsRegisterKeyword(String keywordFromTheUrl) throws InterruptedException {
        Thread.sleep(1000);
        String currentUrl = driver.getCurrentUrl();
        boolean currentUrlContainsKeyword = currentUrl.contains(keywordFromTheUrl);

        Assertions.assertTrue(currentUrlContainsKeyword, "The keyword " + keywordFromTheUrl + " is " + currentUrl);

    }

    @Given("{string} end point is accessed")
    public void endPointIsAccessed(String endPointValue) {
        driver.get("https://andreisecuqa.host" + endPointValue);
    }
}
