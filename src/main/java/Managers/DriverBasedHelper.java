package Managers;

import Managers.PageManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverBasedHelper {
    public PageManager pages;
    WebDriver driver;
    WebDriverWait wait;

    public DriverBasedHelper(WebDriver driver) {
        this.driver = driver;
        pages = new PageManager(driver);
        wait = new WebDriverWait(driver, 10);
    }
}
