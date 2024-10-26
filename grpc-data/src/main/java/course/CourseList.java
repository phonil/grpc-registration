package course;

import com.phonil.registration.CourseInfo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseList {

    protected ArrayList<Course> vCourse;

    public CourseList(String sStudentFileName) throws FileNotFoundException, IOException {
        BufferedReader objStudentFile = new BufferedReader(new FileReader(sStudentFileName));
        this.vCourse = new ArrayList<Course>();
        while (objStudentFile.ready()) {
            String courseInfo = objStudentFile.readLine();
            if (!courseInfo.equals("")) {
                this.vCourse.add(new Course(courseInfo));
            }
        }
        objStudentFile.close();
    }

    public List<CourseInfo> getAllCourseRecords() {
        List<Course> courses = this.vCourse;
        return courses.stream()
                .map(Course::toCourseInfo)
                .collect(Collectors.toList());
    }
}
