package course;

import com.phonil.registration.CourseInfo;
import common.GrpcClientCaller;
import common.InfoType;
import common.Processor;

import java.util.List;

public class CourseProcessor implements Processor {

    @Override
    public boolean support(InfoType infoType) {
        return InfoType.COURSE == infoType;
    }

    @Override
    public List<CourseInfo> getData(GrpcClientCaller grpcClientCaller) {
        return grpcClientCaller.getAllCourseData();
    }
}
