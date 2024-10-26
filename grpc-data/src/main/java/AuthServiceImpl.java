import auth.Auth;
import auth.AuthList;
import com.phonil.registration.AuthServiceGrpc;
import com.phonil.registration.LoginRequest;
import com.phonil.registration.LoginResponse;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class AuthServiceImpl extends AuthServiceGrpc.AuthServiceImplBase {

    protected static AuthList authList;

    @Override
    public void login(LoginRequest loginRequest, StreamObserver<LoginResponse> responseObserver) {
        try {
            this.authList = new AuthList("C:\\Users\\kkwas\\OneDrive\\desktop\\client-server\\grpc-data\\src\\main\\java\\auth\\Auths.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean success = false;
        Auth auth = authList.getAuth(loginRequest.getLoginId());
        if (auth != null) {
            if (loginRequest.getPassword().equals(auth.getPassword()))
                success = true;
        }
        LoginResponse response = LoginResponse.newBuilder()
                .setSuccess(success)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
