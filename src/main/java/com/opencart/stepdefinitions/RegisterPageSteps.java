package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import com.opencart.managers.RandomDataManager;
import com.opencart.pageobjects.RegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

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
}
