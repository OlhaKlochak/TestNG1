package config;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
    public static WebDriver driver;
    @BeforeClass
    public void creat(){
        driver= config.ConfiguretionWebDriver.createDriver(config.WebDrivers.BONYGARSIA);
    }
    @AfterClass
    public void af() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}