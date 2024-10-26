package course;

import com.phonil.registration.CourseInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String courseId;
    protected String professorLastName;
    protected String courseName;
    protected ArrayList<String> preSubjects;

    public Course(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
        this.courseId = stringTokenizer.nextToken();
        this.professorLastName = stringTokenizer.nextToken();
        this.courseName = stringTokenizer.nextToken();
        this.preSubjects = new ArrayList<String>();
        while (stringTokenizer.hasMoreTokens()) {
            this.preSubjects.add(stringTokenizer.nextToken());
        }
    }

    public boolean match(String studentId) {
        return this.courseId.equals(studentId);
    }

    public String getProfessorLastName() {
        return this.professorLastName;
    }

    public ArrayList<String> getPreSubjects() {
        return this.preSubjects;
    }

    public String toString() {
        String stringReturn = this.courseId + " " + this.professorLastName + " " + this.courseName;
        for (int i = 0; i < this.preSubjects.size(); i++) {
            stringReturn = stringReturn + " " + this.preSubjects.get(i).toString();
        }
        return stringReturn;
    }

    /// =============================================

    public CourseInfo toCourseInfo() {
        return CourseInfo.newBuilder()
                .setCourseId(this.courseId)
                .setProfessorLastName(this.professorLastName)
                .setCourseName(this.courseName)
                .addAllPreSubjects(this.preSubjects)
                .build();
    }

}
