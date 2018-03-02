package Pages;

import Managers.PageManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage extends AnyPage{


    public AccountPage(PageManager pages){
        super(pages);
    }

    @FindBy(xpath = "//a[@class = 'search-trigger']")
    WebElement openSearchField;

    @FindBy(id = "search-field")
    WebElement searchField;

    @FindBy(xpath = "//p[@data-search]")
    WebElement searchFieldResult;

    public AccountPage searchSomething(String search){

        Actions action = new Actions(driver);
        action.click(openSearchField).perform();
        wait.until(ExpectedConditions.elementToBeClickable(searchField));
        searchField.sendKeys(search);
        action.sendKeys(Keys.RETURN).perform();
        wait.until(ExpectedConditions.elementToBeClickable(openSearchField));
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
        wait.until(ExpectedConditions.visibilityOf(openSearchField));
    }
}
