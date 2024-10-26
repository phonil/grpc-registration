import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcData {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server grpcData = ServerBuilder
                .forPort(8085)
                .addService(new RegistrationServiceImpl())
                .addService(new AuthServiceImpl())
                .build();

        grpcData.start();
        System.out.println("Data's ready!");
        grpcData.awaitTermination();
    }
}
