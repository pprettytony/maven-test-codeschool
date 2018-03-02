package Pages;

import Managers.PageManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AnyPage{

    public LoginPage(PageManager pages){
        super(pages);
    }

    @FindBy(xpath = "//a[@class = 'search-trigger']")
    public WebElement searchField;

    @FindBy(id = "user_login")
    WebElement loginField;

    @FindBy(id = "user_password")
    WebElement passwordField;

    @FindBy(xpath = "//button")
    WebElement loginButton;

    public LoginPage inputUsername(String username){
        loginField.clear();
        loginField.sendKeys(username);
        return this;
    }

    public LoginPage inputPassword(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    public void loginButtonClick(){
        loginButton.click();
        wait.until(ExpectedConditions
                .elementToBeClickable(searchField));
    }
}
