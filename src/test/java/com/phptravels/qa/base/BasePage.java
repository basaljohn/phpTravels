package com.phptravels.qa.base;

import com.phptravels.qa.ui.utilities.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

    protected static WebDriver webDriver;

    protected void quit() {
        if(webDriver != null)
            webDriver.quit();
    }

    protected WebElement element(By by) {
        return this.webDriver.findElement(by);
    }

    protected List<WebElement> elements(By by) {
        return this.webDriver.findElements(by);
    }

    protected void enterText(By by, String text, boolean clearField) {
        waitTillPresence(by);
        if(clearField)
            clearField(by);
        element(by).sendKeys(text);
    }

    protected void clearField(By by) {
        element(by).clear();
    }

    protected void click(By by) {
        waitTillVisible(by);
        element(by).click();
    }

    protected void waitTillVisible(By by) {
        (new WebDriverWait(this.webDriver, Constants.EXPLICIT_WAIT)).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void waitTillPresence(By by) {
        (new WebDriverWait(this.webDriver, Constants.EXPLICIT_WAIT)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected void navigateTo(String url) {
        webDriver.get(url);
    }

    protected void pressTab() {
        Actions actions = new Actions(webDriver);
        actions.sendKeys(Keys.TAB).build().perform();
    }

    protected void enableField(String field) {
        ((JavascriptExecutor)webDriver).executeScript ("document.querySelector('"+field+"').removeAttribute('readonly',0);");
    }

    protected String getText(By by) {
        waitTillPresence(by);
        return element(by).getText();
    }

    protected String getText(WebElement webElement) {
        return webElement.getText();
    }
}
