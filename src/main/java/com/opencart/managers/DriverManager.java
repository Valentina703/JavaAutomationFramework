package com.opencart.managers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class DriverManager {
    private static String webDriverType = ConfigReaderManager.getPropertyValue("browserType");
    private static DriverManager instance;
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(DriverManager.class);

    private DriverManager() {
        switch (webDriverType.toUpperCase()) {
            case "CHROME":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("ignore-certificate-errors");
                options.addArguments("--start-maximized");
                options.addArguments("--incognito");
                driver = new ChromeDriver(options);
                logger.log(Level.INFO, "The Chrome Driver is initiated");
                break;
            case "FIREFOX":
                driver = new FirefoxDriver();
                logger.log(Level.INFO, "The Firefox Driver is initiated");
                break;
            case "EDGE":
                driver = new EdgeDriver();
                logger.log(Level.INFO, "The Edge Driver is initiated");
                break;
            case "SAFARI":
                driver = new SafariDriver();
                logger.log(Level.INFO, "The Safari Driver is initiated");
                break;
            default:
                logger.log(Level.WARN, "There is no such a driver " + webDriverType);
        }
        int implicitWaitTime = Integer.parseInt(ConfigReaderManager.getPropertyValue("implicitWaiterValue"));
        int pageLoadTime = Integer.parseInt(ConfigReaderManager.getPropertyValue("pageLoadTimeout"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTime));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(pageLoadTime));

    }

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void tearDown() {
        driver.close();
        driver.quit();
        instance = null;
        driver = null;
        logger.log(Level.WARN, "The driver is null");
    }

    public void deleteCookies(){
        driver.manage().deleteAllCookies();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}