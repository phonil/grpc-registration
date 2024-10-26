import auth.Auth;
import auth.AuthList;
import com.phonil.registration.AuthServiceGrpc;
import com.phonil.registration.LoginRequest;
import com.phonil.registration.LoginResponse;
import com.phonil.registration.StudentResponse;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class AuthServiceImpl extends AuthServiceGrpc.AuthServiceImplBase {

    private final AuthServerCaller authServerCaller;

    public AuthServiceImpl(AuthServerCaller authServerCaller) {
        this.authServerCaller = authServerCaller;
    }

    @Override
    public void login(LoginRequest loginRequest, StreamObserver<LoginResponse> responseObserver) {
        LoginResponse response = LoginResponse.newBuilder()
                .setSuccess(authServerCaller.login(loginRequest))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
