package com.opencart.stepdefinitions;

import com.opencart.managers.ConfigReaderManager;
import com.opencart.managers.DataSubstituteManager;
import com.opencart.managers.DriverManager;
import com.opencart.managers.ScrollManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class GenericSteps {

    WebDriver driver = DriverManager.getInstance().getDriver();

    @Then("the current url contains {string} keyword")
    public void theCurrentUrlContainsRegisterKeyword(String keywordFromTheUrl) throws InterruptedException {
        Thread.sleep(2000);
        String currentUrl = driver.getCurrentUrl();
        boolean currentUrlContainsKeyword = currentUrl.contains(keywordFromTheUrl);

        Assertions.assertTrue(currentUrlContainsKeyword, "The keyword " + keywordFromTheUrl + " is " + currentUrl);
    }

    @Given("{string} end point is accessed")
    public void endPointIsAccessed(String endPointValue) {
        driver.get(ConfigReaderManager.getPropertyValue("url") + endPointValue);
    }

    @Then("the following error message is displayed:")
    public void theFollowingErrorMessageIsDisplayed(List<String> errorMessagesList) throws InterruptedException {
        Thread.sleep(500);
        errorMessagesList.forEach(errorMessage -> {
            boolean errorMessageIsDisplayed = driver.findElement(By.xpath("//*[contains(text()," + errorMessage + ")]")).isDisplayed();
            Assertions.assertTrue(errorMessageIsDisplayed, "The error message:" + errorMessage + "is displayed");
        });
    }

    @And("the {string} from {string} is clicked")
    public void theElementFromPageNameIsClicked(String elementName, String pageName) {
        try {
            Class classInstance = Class.forName("com.opencart.pageobjects." + pageName);
            Field classField = classInstance.getDeclaredField(elementName);
            classField.setAccessible(true);
            WebElement elementToBeClicked = (WebElement) classField.get(classInstance.getDeclaredConstructor(WebDriver.class).newInstance(driver));
            ScrollManager.scrollToTheElement(elementToBeClicked);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(elementToBeClicked));
            elementToBeClicked.click();

            System.out.println("The element " + elementName + " is clicked");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("the following form from {string} is populated as follows:")
    public void theFollowingFormFromIsPopulatedAsFollows(String pageName, Map<String, String> fieldsAndValuesMap) {
        fieldsAndValuesMap.forEach((fieldName, fieldValue) -> {
            try {
                Class classInstance = Class.forName("com.opencart.pageobjects." + pageName);
                Field classField = classInstance.getDeclaredField(fieldName);
                classField.setAccessible(true);
                WebElement inputElement = (WebElement) classField.get(classInstance.getConstructor(WebDriver.class).newInstance(driver));
                fieldValue = DataSubstituteManager.subsitueString(fieldValue);
                inputElement.sendKeys(fieldValue);
                System.out.println("The data [" + fieldName + "] is populated with [" + fieldValue + "]");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}