package Managers;

import Models.Course;

import java.util.List;

public interface ICourseHelper {
    Course addCourse();
    List<String> idList();
    CourseHelper removeCourseFromBookmark(String id);
    CourseHelper removeCourseFromSearch(String id);
}
