import com.phonil.registration.CourseResponse;
import com.phonil.registration.Empty;
import com.phonil.registration.RegistrationServiceGrpc;
import com.phonil.registration.StudentResponse;
import io.grpc.stub.StreamObserver;

public class RegistrationServiceImpl extends RegistrationServiceGrpc.RegistrationServiceImplBase {

    private final RegistrationServerCaller registrationServerCaller;

    public RegistrationServiceImpl(RegistrationServerCaller registrationServerCaller) {
        this.registrationServerCaller = registrationServerCaller;
    }

    @Override
    public void getAllStudentData(Empty request, StreamObserver<StudentResponse> responseObserver) {
        StudentResponse response = StudentResponse.newBuilder()
                .addAllStudentInfo(registrationServerCaller.getAllStudentData())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllCourseData(Empty request, StreamObserver<CourseResponse> responseObserver) {
        CourseResponse response = CourseResponse.newBuilder()
                .addAllCourseInfo(registrationServerCaller.getAllCourseData())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
