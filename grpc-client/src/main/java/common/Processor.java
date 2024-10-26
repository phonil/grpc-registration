package common;

import java.util.List;

public interface Processor {

    boolean support(InfoType infoType);

    List<?> getData(GrpcClientCaller grpcClientCaller);
}
