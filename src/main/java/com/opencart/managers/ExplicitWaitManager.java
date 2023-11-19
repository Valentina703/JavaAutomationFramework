package com.opencart.managers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWaitManager {
    private int explicitWaitTime = Integer.parseInt(ConfigReaderManager.getPropertyValue("explicitWaitTime"));
    private static WebDriverWait waitObject = new WebDriverWait(DriverManager.getInstance().getDriver(), Duration.ofSeconds(10));
    private static Logger logger = LogManager.getLogger(ExplicitWaitManager.class);

    public static void waitTillTheElementIsClickable(WebElement element) {
        waitObject.until(ExpectedConditions.elementToBeClickable(element));
        logger.log(Level.INFO, "Element is clickable" + element.getAccessibleName());
    }

    public static void waitTillTheElementIsVisible(WebElement element) {
        waitObject.until(ExpectedConditions.visibilityOf(element));
        logger.log(Level.INFO, "Element is visible" + element.getAccessibleName());
    }
}
