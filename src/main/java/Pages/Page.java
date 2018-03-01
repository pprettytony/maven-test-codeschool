package Pages;

import Managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public abstract class Page {
    WebDriver driver;
    WebDriverWait wait;
    PageManager pages;



    public Page(PageManager pages){
        this.pages = pages;
        driver = pages.getDriver();
        wait = new WebDriverWait(driver,10);
    }



    public WebDriver getDriver() {
        return driver;
    }

    public Page ensurePageLoader(){
        return this;
    }

    public boolean waitPageLoader(){
        try{
            ensurePageLoader();
            return true;
        } catch (TimeoutException e){
            return false;
        }
    }

    @Step("finding element on page")
    public String getDynamicXpath(String xpath, String id) {
        if(id != null){
            return xpath.replace("set_your_id", id);
        } else {
            return xpath;
        }
    }

    public WebElement getWebElement(String xpath){
        return driver.findElement(By.xpath(xpath));
    }

    @Step("waiting")
    public void setWaiter(String xpath, BookmarksPage.Expected conditions, String id){
        switch (conditions){
            case elementToBeClickable:
                wait.until(ExpectedConditions
                        .elementToBeClickable(By.xpath(getDynamicXpath(xpath, id))));
                break;
            case invisibilityOf:
                wait.until(ExpectedConditions.invisibilityOf(getWebElement(getDynamicXpath(xpath, id))));
                break;
            case pageLoaded:
                wait.until((ExpectedCondition<Boolean>) driver -> ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"));
                break;
            case visibilityOf:
                wait.until(ExpectedConditions
                        .visibilityOf(getWebElement(getDynamicXpath(xpath, id))));
        }
    }
}
