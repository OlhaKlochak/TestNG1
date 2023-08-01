package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

public class Page {
   @FindBy (xpath = "//button[@data-dropdown-trigger='coursesMenu']")
   WebElement course;

   public Page(WebDriver driver) {
      this.driver = driver;
      PageFactory.initElements(driver,this);
   }

   static WebDriver driver;

   @FindBy(name = "certificate")
   WebElement searchField;
   @FindBy(id = "certificateCheckForm")
   WebElement formCert;
 //  WebDriver driver;

//   public Page(WebDriver driver) {
//      this.driver = driver;
//     PageFactory.initElements(driver,this);


   public void sendCertificate(String certNumber){
      searchField.sendKeys(certNumber);
      searchField.sendKeys(Keys.ENTER);
   }

   public Boolean validationCertificare() throws Exception {
      boolean result;
      int i=0;
      while (true){
         if(driver.getCurrentUrl().contains("view")) {
            result=true;
            break;

         }
         if (formCert.getAttribute("class").contains("invalid")){
            result=false;
            break;
         }


         Thread.sleep(1000);

         i++;
         if (i>10){
            throw new Exception("out of time");
         }

      }


      return result;
   }
}


