package com.opencart.stepdefinitions;

import com.opencart.managers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class Hook {
    @BeforeAll
    public static void theMethodExecutedBeforeAllTests(){
        System.out.println("The test execution started");
    }

    @Before
    public void beforeEachTest(){
        System.out.println("New test execution started");
    }

    @After
    public void afterEachTest(){
        DriverManager.getInstance().tearDown();
        System.out.println("The test execution has just finished");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("The test execution has been finished");
    }
}
