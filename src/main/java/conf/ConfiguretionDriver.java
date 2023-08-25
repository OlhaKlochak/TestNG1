package conf;


import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class ConfiguretionDriver {
    static WebDriver driver = null;
    static BrowserMobProxyServer server=null;
    static public WebDriver createDriver(conf.WebDrivers webDrivers) {

        switch (webDrivers) {
            case CHROME : createChrome();
            break;
            case CHROME_INCOGNITO : createChromeIncognito();
            break;
            case FIREFOX : createFireFox();
            break;
            case PROXYCHROME : createProxyChrome();
            break;
        //    case BONYGARSIA : createDriverBony();
        //    break;
        }
        return driver;
    }

//    private static void createDriverBony() {
//        ChromeOptions options = new ChromeOptions();
//
//        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("--start-maximized");
//        options.addArguments("--incognito");
//...

//chromeOptions.addArguments(<another-option>);

//...

//        driver = WebDriverManager.chromedriver().capabilities(options).create();
  //  }

    private static void createProxyChrome() {
////
        server = new BrowserMobProxyServer();
        server.setTrustAllServers(true);
        server.start();

        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(server);
        String hostIp = null;
        try {
            hostIp = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        seleniumProxy.setHttpProxy(hostIp + ":" + server.getPort());
        seleniumProxy.setSslProxy(hostIp + ":" + server.getPort());

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        capabilities.setAcceptInsecureCerts(true);
/////
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.merge(capabilities);//////
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
    }

    private static void createFireFox() {

    }

    private static void createChromeIncognito() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5,TimeUnit.SECONDS);
    }

    private static void createChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }


}



