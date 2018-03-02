package Pages;

import Managers.PageManager;
import Models.Course;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static Pages.BookmarksPage.Expected.*;

public class BookmarksPage extends AnyPage{

    public BookmarksPage(PageManager pages) {
        super(pages);
    }

    enum Expected{
        elementToBeClickable,
        visibilityOf,
        invisibilityOf,
        pageLoaded
    }

    private String addedCourseWithIdLocator = "//a[contains(@class,'is-bookmarked') and @data-bookmark-id = 'set_your_id']";
    private String notAddedCourseWithIdLocator = "//a[not(contains(@class,'is-bookmarked')) and @data-bookmark-id = 'set_your_id']";
    private String addedCourseInSearchLocator = "//a[@data-bookmark-id = 'set_your_id']";
    private String addedCourseInBookmarksLocator = "//li[@data-bookmark-id = 'set_your_id']";

    @FindBy(xpath = "//a[@class = 'search-trigger']")
    public WebElement searchField;

    @FindBy(xpath = "(//a[contains(@class,'bookmark') and not(contains(@class,'is-bookmarked'))])[1]")
    public WebElement firstNotAddedCourse;

    @FindBy(xpath = "(//a[@data-bookmark-id])")
    List<WebElement> courseIdFromBookmarksList;

    @Step("Adding a first not added course")
    public Course addToBookmarks(){
        Course course = new Course(firstNotAddedCourse.getAttribute("data-bookmark-id"));
        Actions actions = new Actions(driver);
        actions.click(firstNotAddedCourse).perform();
        setWaiter(addedCourseWithIdLocator, elementToBeClickable, course.getId());
        return course;

    }

    @Step("Remove course from bookmarks")
    public void removeFromBookmarks(String id){
        setWaiter(null, pageLoaded, null);
        //wait.until(ExpectedConditions.visibilityOf(searchField));
        Actions actions = new Actions(driver);
        WebElement elem = getWebElement(getDynamicXpath(addedCourseInSearchLocator, id));
        actions.moveToElement(elem)
                .click(elem)
                .perform();
        setWaiter(addedCourseInBookmarksLocator, invisibilityOf, id);
    }

    @Step("Remove course from search page")
    public void removeFromSearchList(String id){
        Actions actions = new Actions(driver);
        WebElement elem = driver.findElement(By.xpath(getDynamicXpath(addedCourseInSearchLocator, id)));
        actions.click(elem).perform();
        setWaiter(notAddedCourseWithIdLocator, elementToBeClickable, id);
    }

    @Step("get bookmarks page")
    public void goToBookmarks(){
        driver.get("https://www.codeschool.com/account/bookmarks");
        setWaiter(null, pageLoaded, null);
    }

    @Step("get list of courses")
    public List<Course> getCoursesFromBookmarks(){
        List<Course> list = new ArrayList<>();
        goToBookmarks();
        for (WebElement item: courseIdFromBookmarksList) {
            list.add(new Course(item.getAttribute("data-bookmark-id")));
        }
        return list;
    }
}