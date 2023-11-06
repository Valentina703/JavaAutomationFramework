package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import com.opencart.managers.RandomDataManager;
import com.opencart.pageobjects.RegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class RegisterPageSteps {

    WebDriver driver = DriverManager.getInstance().getDriver();

    RegisterPage registerPage = new RegisterPage(driver);

    @When("the Register Form is populated with valid random  data")
    public void theRegisterFormIsPopulatedWithValidRandomData() {

        String randomEmail = RandomDataManager.generateRandomEmail();
        String randomPassword = RandomDataManager.generatePassword();

        System.out.println(randomEmail);
        System.out.println(randomPassword);

        registerPage.fillInTheRegisterForm(RandomDataManager.generateFirstName(),
                RandomDataManager.generateLastName(), randomEmail, randomPassword, true);

        System.out.println("The Register Form is populated with valid random data");
    }

    @And("continue button is clicked")
    public void continueButtonIsClicked() {
        registerPage.clickTheContinueButton();
        System.out.println("The continue has been clicked");

    }

    @When("the Register Form is populated with the following data:")
    public void theRegisterFormIsPopulatedWithTheFollowingData(Map<String, String> formData) {
        String firstNameValue = formData.get("firstName");
        if(firstNameValue != null && firstNameValue.toUpperCase().equals("RANDOM")){
            firstNameValue = RandomDataManager.generateFirstName();
        }
        String lastNameValue = formData.get("lastName");
        if(lastNameValue != null && lastNameValue.toUpperCase().equals("RANDOM")){
            lastNameValue = RandomDataManager.generateLastName();
        }
        String emailInput = formData.get("email");
        if(emailInput != null && emailInput.toUpperCase().equals("RANDOM")){
            emailInput = RandomDataManager.generateRandomEmail();
        }
        String password = formData.get("password");
        if(password != null && password.equalsIgnoreCase("RANDOM"));

        registerPage.fillInTheRegisterForm(firstNameValue, lastNameValue, emailInput, password, true);
    }
}
