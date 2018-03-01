package Pages;

import Managers.PageManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static Pages.BookmarksPage.Expected.elementToBeClickable;
import static Pages.BookmarksPage.Expected.visibilityOf;

public class AccountPage extends AnyPage{


    public AccountPage(PageManager pages){
        super(pages);
    }

    String openedSearchFieldLocator = "//a[@class = 'search-trigger']";
    String searchFieldLocator = "id('search-field')";

    @FindBy(xpath = "//a[@class = 'search-trigger']")
    WebElement openedSearchField;

    @FindBy(id = "search-field")
    WebElement searchField;

    @FindBy(xpath = "//p[@data-search]")
    WebElement searchFieldResult;

    public AccountPage searchSomething(String search){

        Actions action = new Actions(driver);
        action.click(openedSearchField).perform();
        setWaiter(searchFieldLocator, visibilityOf, null);
        searchField.sendKeys(search);
        action.sendKeys(Keys.RETURN).perform();
        setWaiter(openedSearchFieldLocator, elementToBeClickable, null);
        //wait.until(ExpectedConditions.elementToBeClickable(openedSearchField));
        return this;

    }
    public boolean verifyAccountPage(){
        return driver.getCurrentUrl().equals("https://www.codeschool.com/account");
    }
    public String searchingResult(){
        return searchFieldResult.getText();
    }
    public void goToAccountPage(){
        driver.get("https://www.codeschool.com/account");
        setWaiter(openedSearchFieldLocator, elementToBeClickable, null);
        //wait.until(ExpectedConditions.visibilityOf(openedSearchField));
        //setWaiter(null, pageLoaded, null);
    }
}
