package com.example.moviecollectionbackend;

import java.io.File;
import java.net.URL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeTest {

    private static final String CHROME_DRIVER_FILE_NAME = "chromedriver.exe";

    private ChromeDriver chromeDriver;

    @BeforeEach
    void setUp() {

        ChromeDriverService service = new ChromeDriverService.Builder()
            .usingDriverExecutable(new File(getChromeDriverFileName())).build();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--start-maximized");

        chromeDriver = new ChromeDriver(service, chromeOptions);
    }

    @AfterEach
    void tearDown() {
        chromeDriver.quit();
    }

    @Test
    void testLogin() throws InterruptedException {
        chromeDriver.get("http://localhost:8080/users/login");

        WebElement usernameInput = chromeDriver.findElement(By.name("loginUsername"));
        WebElement passwordInput = chromeDriver.findElement(By.name("loginPassword"));

        usernameInput.sendKeys("hristo");
        passwordInput.sendKeys("topsecret");

        passwordInput.submit();

        Thread.sleep(800);//ToDo да го заменя с по правилен начин

        WebElement welcomeMessage = chromeDriver.findElement(By.className("welcomeMessage"));
        String welcomeText = welcomeMessage.getText();

        Assertions.assertTrue(welcomeText.equals("Welcome: hristo"));
    }

    private String getChromeDriverFileName() {
        ClassLoader classLoader = ChromeTest.class.getClassLoader();
        URL driverUrl = classLoader.getResource(CHROME_DRIVER_FILE_NAME);
        if (driverUrl == null) {
            Assertions.fail("Unable to locale the chrome driver!");
        }
        return driverUrl.getFile();
    }
}
