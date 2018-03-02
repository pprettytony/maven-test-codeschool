package Managers;

import Models.Course;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class CourseHelper extends DriverBasedHelper implements ICourseHelper {

    public CourseHelper(AppManager manager) {
        super(manager.getDriver());
    }

    @Override
    public Course addCourse() {
        return pages.bookmarksPage.addToBookmarks();
    }

    @Override
    public List<String> idList() {
        List<String> list = new ArrayList<>();
        for(Course course: pages.bookmarksPage.getCoursesFromBookmarks()){
            list.add(course.getId());
        }
        return list;
    }

    @Override
    public CourseHelper removeCourseFromBookmark(String id) {
        pages.bookmarksPage.removeFromBookmarks(id);
        return this;
    }

    @Override
    public CourseHelper removeCourseFromSearch(String id) {
        pages.bookmarksPage.removeFromSearchList(id);
        return this;
    }


}
