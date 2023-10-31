package com.opencart;

import com.opencart.managers.DriverManager;
import com.opencart.managers.RandomDataManager;
import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.RegisterPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestRegistrationFlowWIthJUnit {

    WebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;

    @BeforeAll
    public static void executeThisMethodBeforeAllTests() {
        System.out.println("The execution of the test suite has started");
    }

    @BeforeEach
    public void executeTheCodeBeforeEachTest() {
        System.out.println("The code is executed before each test case");
        driver = DriverManager.getInstance().getDriver();
        driver.get("https://andreisecuqa.host/");
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        homePage.navigateToRegisterPageFromHeader();
    }

    @Test
    @DisplayName("The registration of a new user with valid data redirects to the account page")
    public void registerWIthValidCredentialsTest() throws InterruptedException {
        System.out.println("This is test nr1");

        String randomEmail = RandomDataManager.generateRandomEmail();
        String randomPassword = RandomDataManager.generatePassword();

        System.out.println(randomEmail);
        System.out.println(randomPassword);

        registerPage.fillInTheRegisterForm(RandomDataManager.generateFirstName(), RandomDataManager.generateLastName(), randomEmail, randomPassword, true);
        registerPage.clickTheContinueButton();
        Thread.sleep(500);

        String currentUrl = driver.getCurrentUrl();
        boolean doesTheCorrectUrlContainSuccessAccountRoute = currentUrl.contains("route=account/success");
        Assertions.assertTrue(doesTheCorrectUrlContainSuccessAccountRoute);
        System.out.println("The execution is over");
        Thread.sleep(500);
    }

    @Test
    @DisplayName("The user remains on Register page when trying to register with invalid password")
    public void registerWithInvalidPasswordTest() throws InterruptedException {
        System.out.println("This is test nr2");

        String randomEmail = RandomDataManager.generateRandomEmail();

        System.out.println(randomEmail);

        registerPage.fillInTheRegisterForm(RandomDataManager.generateFirstName(),
                RandomDataManager.generateLastName(), randomEmail, "123", true);
        registerPage.clickTheContinueButton();

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://andreisecuqa.host/index.php?route=account/register&language=en-gb";
        Assertions.assertEquals(expectedUrl, actualUrl, "The urls should be egual");
        Thread.sleep(500);
    }

    @Test
    @DisplayName("Error message is displayed on Register flow when password is less than 4 characters")
    public void errorMessageIsDisplayedWhenInvalidPasswordIsUsedForRegisterFlow() throws InterruptedException {
        System.out.println("This is test nr3");

        String randomEmail = RandomDataManager.generateRandomEmail();

        System.out.println(randomEmail);

        registerPage.fillInTheRegisterForm(RandomDataManager.generateFirstName(),
                RandomDataManager.generateLastName(), randomEmail, "Aa1", true);
        registerPage.clickTheContinueButton();

        Thread.sleep(500);

        String actualErrorMessage = driver.findElement(By.id("error-password")).getText();
        String expectedErrorMessageForInvalidPassword = "Password must be between 4 and 20 characters!";
        Assertions.assertEquals(expectedErrorMessageForInvalidPassword, actualErrorMessage);
    }

    @AfterEach
    public void afterEachTestCase() {
        DriverManager.getInstance().tearDown();
        System.out.println("The testcase execution has been finished");
    }

    @AfterAll
    public static void afterAllMethod() {
        System.out.println("The test suite execution is finished");
    }
}