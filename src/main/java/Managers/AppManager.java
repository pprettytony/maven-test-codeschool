package Managers;

import Drivers.Driver;
import Models.User;
import Utils.PropertyLoader;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


public class AppManager implements IAppManager{

    private UserHelper userHelper;
    private AccountHelper accountHelper;
    private WebDriver driver;
    private CourseHelper courseHelper;
    private User user;
    private String username;
    private String password;

    private String baseUrl;
    private Capabilities capabilities;



    public AppManager(){
        try {
            username = PropertyLoader.loadProperty("username");
            password = PropertyLoader.loadProperty("password");
            baseUrl = PropertyLoader.loadProperty("base.url");
            capabilities = PropertyLoader.loadCapabilities();
        } catch (IOException e) {
            e.printStackTrace();
        }
        user = new User(username, password);
        driver = new Driver().setupDriver(capabilities);
        driver.manage().window().maximize();
        driver.get(baseUrl);
        userHelper = new UserHelper(this);
        accountHelper = new AccountHelper(this);
        courseHelper = new CourseHelper(this);
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public AccountHelper getAccountHelper() {
        return accountHelper;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public CourseHelper getCourseHelper() {
        return courseHelper;
    }

    public User getUser() {
        return user;
    }

    @Override
    public void stop() {
        driver.quit();
    }
}
