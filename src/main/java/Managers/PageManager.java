package Managers;

import Pages.AccountPage;
import Pages.BookmarksPage;
import Pages.LoginPage;
import Pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageManager {

    WebDriver driver;
    BookmarksPage bookmarksPage;
    AccountPage accountPage;
    LoginPage loginPage;

    public PageManager(WebDriver driver){
        this.driver = driver;
        loginPage = initElements(new LoginPage(this));
        accountPage = initElements(new AccountPage(this));
        bookmarksPage = initElements(new BookmarksPage(this));
    }
    private <T extends Page> T initElements(T page){
        PageFactory.initElements(driver, page);
        return page;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
