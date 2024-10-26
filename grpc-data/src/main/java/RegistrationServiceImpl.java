import com.phonil.registration.CourseResponse;
import com.phonil.registration.Empty;
import com.phonil.registration.RegistrationServiceGrpc;
import com.phonil.registration.StudentResponse;
import course.CourseList;
import io.grpc.stub.StreamObserver;
import student.StudentList;

import java.io.IOException;

public class RegistrationServiceImpl extends RegistrationServiceGrpc.RegistrationServiceImplBase {

    protected static StudentList studentList;
    protected static CourseList courseList;

    public RegistrationServiceImpl() {
//        try {
//            this.studentList = new StudentList("C:\\Users\\kkwas\\OneDrive\\desktop\\client-server\\grpc-data\\src\\main\\java\\student\\Students.txt");
//            this.courseList = new CourseList("C:\\Users\\kkwas\\OneDrive\\desktop\\client-server\\grpc-data\\src\\main\\java\\course\\Courses.txt");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void getAllStudentData(Empty request, StreamObserver<StudentResponse> responseObserver) {
        try {
            this.studentList = new StudentList("C:\\Users\\kkwas\\OneDrive\\desktop\\client-server\\grpc-data\\src\\main\\java\\student\\Students.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        StudentResponse response = StudentResponse.newBuilder()
                .addAllStudentInfo(this.studentList.getAllStudentRecords())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllCourseData(Empty requset, StreamObserver<CourseResponse> responseObserver) {
        try {
            this.courseList = new CourseList("C:\\Users\\kkwas\\OneDrive\\desktop\\client-server\\grpc-data\\src\\main\\java\\course\\Courses.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        CourseResponse response = CourseResponse.newBuilder()
                .addAllCourseInfo(this.courseList.getAllCourseRecords())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
