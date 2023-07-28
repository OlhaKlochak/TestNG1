import Page.Page;
import config.BaseClass;
import data.DataTest;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestA extends BaseClass {

    private static Page page;
    By course = new By.ByXPath("//button[@data-dropdown-trigger='coursesMenu']");
    //By searchField = new By.ByName("certificate");



    @BeforeClass
    public void test1() {

        driver.get("https://ithillel.ua/");
        page = new Page(driver);
    }

    @Test(priority = 1)
    public void test2() {
        driver.findElement(course).isEnabled();

    }

    @Test
    public void test3() {
        driver.get("https://certificate.ithillel.ua/");
    }

    @Test
            (dataProvider = "getCert", dataProviderClass = DataTest.class)

    public void iWriteCertificate(String certificateNumber) {
       page.sendCertificate(certificateNumber);
    }




        @Test(dependsOnMethods = "iWriteCertificate")

        public void checkCertificateValidation (String expected) throws Exception {
            Assert.assertEquals(Boolean.valueOf(expected), page.validationCertificare());

        }
    }