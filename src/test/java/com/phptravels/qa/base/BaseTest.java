package com.phptravels.qa.base;

import com.phptravels.qa.ui.utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class BaseTest extends BasePage {

    @BeforeSuite
    @Parameters({ "browser", "wait", "url" })
    public void initFramework(ITestContext context, String browser, String wait, String url) {
        if(browser == null)
            throw new RuntimeException("Browser cannot be empty");
        if(url == null)
            throw new RuntimeException("URL cannot be empty");
        webDriver = loadDriver(browser, wait);
        navigateTo(url);
    }

    @AfterSuite
    public void tearDown() {
        quit();
    }

    public WebDriver loadDriver(String browser, String wait) {
        if(browser.equals("ie")){
            System.setProperty(Constants.IE_DRIVER, Constants.IE_DRIVER_DIR);
            webDriver = new InternetExplorerDriver();
        }else if(browser.equals("firefox")){
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--lang=en-GB");
            System.setProperty(Constants.FIREFOX_DRIVER, Constants.FIREFOX_DRIVER_DIR);
            webDriver = new FirefoxDriver(options);
        }else if(browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--lang=en-GB");
            System.setProperty(Constants.CHROME_DRIVER, Constants.CHROME_DRIVER_DIR);
            webDriver = new ChromeDriver(options);
        }
        webDriver.manage().timeouts().implicitlyWait(Integer.parseInt(wait), TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        return webDriver;
    }
}
