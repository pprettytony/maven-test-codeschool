
import TestData.ExcelUtils;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


@Listeners(ScreenShotOnFailListener.class)
public class BookmarksTest extends TestBase {

    List<String> list = new ArrayList<>();
    String addedCourseId;

    @DataProvider
    public Object[][] ExcelData(ITestContext context) throws Exception{
        String textName = context.getName();
        Object[][] data = null;
        switch (textName.replace("test","")){
            case "1":
                data = ExcelUtils.getTableArray(1);
                return data;
            case "2":
                data = ExcelUtils.getTableArray(2);
                return data;
            case "3":
                data = ExcelUtils.getTableArray(3);
                return data;
            case "4":
                data = ExcelUtils.getTableArray(4);
                return data;
        }
        return data;
    }


    @Feature("Adding")
    @Story("Add our course to bookmarks")
    @Test(dataProvider = "ExcelData")
    public void addingToBookmarksTest(String rowNumber){
        app.getUserHelper().loginAs(app.getUser());
        Assert.assertTrue(app.getUserHelper().verifyAuth());
        app.getAccountHelper()
                .search(rowNumber);
        addedCourseId = app.getCourseHelper().addCourse().getId();
        list = app.getCourseHelper().idList();
        Assert.assertTrue(list.contains(addedCourseId));
    }

    @Feature("Removing")
    @Story("Remove our course from bookmark page")
    @Test
    public void removingFromBookmarksTest(){
        list = app.getCourseHelper().removeCourseFromBookmark(addedCourseId).idList();
        Assert.assertFalse(list.contains(addedCourseId));
    }

    @Feature("Removing")
    @Story("Remove our course from search page")
    @Test(dataProvider = "ExcelData")
    public void removingFromSearchTest(String search){
        app.getAccountHelper()
                .goToAccountPage().search(search);
        list = app.getCourseHelper().removeCourseFromSearch(addedCourseId).idList();
        Assert.assertFalse(list.contains(addedCourseId));
    }

    @Feature("Removing")
    @Story("Remove our course from bookmarks block in account page")
    @Test(description = "")
    public void removingFromAccountTest(){
        app.getAccountHelper().goToAccountPage();
        list = app.getCourseHelper().removeCourseFromSearch(addedCourseId).idList();
        Assert.assertFalse(list.contains(addedCourseId));
    }


}
