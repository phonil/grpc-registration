package common;

import com.phonil.registration.*;
import io.grpc.ManagedChannel;

import java.util.List;

public class GrpcClientCaller {

    private final RegistrationServiceGrpc.RegistrationServiceBlockingStub registrationServiceBlockingStub;
    private final AuthServiceGrpc.AuthServiceBlockingStub authServiceBlockingStub;

    public GrpcClientCaller(ManagedChannel channel) {
        this.registrationServiceBlockingStub = RegistrationServiceGrpc.newBlockingStub(channel);
        this.authServiceBlockingStub = AuthServiceGrpc.newBlockingStub(channel);
    }

    public List<StudentInfo> getAllStudentData() {
        return registrationServiceBlockingStub.getAllStudentData(Empty.newBuilder().build())
                .getStudentInfoList();
    }

    public List<CourseInfo> getAllCourseData() {
        return registrationServiceBlockingStub.getAllCourseData(Empty.newBuilder().build())
                .getCourseInfoList();
    }

    public boolean login(String loginId, String password) {
        return authServiceBlockingStub.login(LoginRequest.newBuilder()
                        .setLoginId(loginId)
                        .setPassword(password)
                        .build()).getSuccess();
    }
}
