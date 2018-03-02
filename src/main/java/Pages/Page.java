package Pages;

import Managers.PageManager;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
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
}
