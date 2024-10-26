import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8085)
                .usePlaintext()
                .build();

        Server grpcServer = ServerBuilder
                .forPort(8084)
                .addService(new RegistrationServiceImpl(new RegistrationServerCaller(channel)))
                .addService(new AuthServiceImpl(new AuthServerCaller(channel)))
                .build();

        grpcServer.start();
        System.out.println("Server's ready!");
        grpcServer.awaitTermination();
    }
}
