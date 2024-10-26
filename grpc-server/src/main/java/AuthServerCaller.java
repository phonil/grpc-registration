import com.phonil.registration.AuthServiceGrpc;
import com.phonil.registration.LoginRequest;
import io.grpc.ManagedChannel;

public class AuthServerCaller {

    ManagedChannel channel;
    AuthServiceGrpc.AuthServiceBlockingStub blockingStub;

    public AuthServerCaller(ManagedChannel channel) {
        this.channel = channel;
        this.blockingStub = AuthServiceGrpc.newBlockingStub(channel);
    }

    public boolean login(LoginRequest loginRequest) {
        return blockingStub.login(loginRequest)
                .getSuccess();
    }
}
