package student;

import com.phonil.registration.StudentInfo;
import common.GrpcClientCaller;
import common.InfoType;
import common.Processor;

import java.util.List;

public class StudentProcessor implements Processor {

    @Override
    public boolean support(InfoType infoType) {
        return InfoType.STUDENT == infoType;
    }

    @Override
    public List<StudentInfo> getData(GrpcClientCaller grpcClientCaller) {
        return grpcClientCaller.getAllStudentData();
    }
}
