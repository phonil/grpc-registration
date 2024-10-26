import com.phonil.registration.CourseInfo;
import com.phonil.registration.Empty;
import com.phonil.registration.RegistrationServiceGrpc;
import com.phonil.registration.StudentInfo;
import io.grpc.ManagedChannel;

import java.util.List;

public class RegistrationServerCaller {

    ManagedChannel channel;
    RegistrationServiceGrpc.RegistrationServiceBlockingStub blockingStub;

    public RegistrationServerCaller(ManagedChannel channel) {
        this.channel = channel;
        this.blockingStub = RegistrationServiceGrpc.newBlockingStub(channel);
    }

    public List<StudentInfo> getAllStudentData() {
        return blockingStub.getAllStudentData(Empty.newBuilder().build())
                .getStudentInfoList();
    }

    public List<CourseInfo> getAllCourseData() {
        return blockingStub.getAllCourseData(Empty.newBuilder().build())
                .getCourseInfoList();
    }
}
