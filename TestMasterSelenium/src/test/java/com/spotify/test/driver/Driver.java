package com.spotify.test.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {

    public static WebDriver driver;

    private static final Logger logger = LogManager.getLogger(Driver.class);

    @BeforeAll
    public static void beforeAll(){
        logger.info("*** BeforeAll ***");
    }

    @BeforeEach
    public void beforeEach(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-translate");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        //driver.manage().deleteAllCookies();
        driver.get("https://open.spotify.com/");

        logger.info("*** Before ***");


    }

    @AfterEach
    public void afterEach(){
        if (driver != null){
            driver.quit();
        }

        logger.info("*** After ***");

    }

    @AfterAll
    public static void afterAll(){
        logger.info("*** AfterAll ***");
    }

}
