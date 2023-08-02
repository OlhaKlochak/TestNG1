import Page.Page;
import config.BaseClass;
import data.DataTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestA extends BaseClass {

    private static Page page;
    By course = new By.ByXPath("//button[@data-dropdown-trigger='coursesMenu']");




    @BeforeClass
    public void test1() {

        driver.get("https://ithillel.ua/");
        page = new Page(driver);
    }

    @Test()
    public void test2() {
        driver.findElement(course).isEnabled();

    }
    @Test
            (dataProvider = "getCert", dataProviderClass = DataTest.class)

    public void iWriteCertificate(String certificateNumber, String expected) throws Exception {
        driver.get("https://certificate.ithillel.ua/");
        page.sendCertificate(certificateNumber);
        Assert.assertEquals(Boolean.valueOf(expected), page.validationCertificare());

    }
}