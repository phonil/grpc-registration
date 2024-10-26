package auth;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AuthList {
    ArrayList<Auth> vAuth;

    public AuthList(String sAuthFileName) throws FileNotFoundException, IOException {
        BufferedReader objStudentFile = new BufferedReader(new FileReader(sAuthFileName));
        this.vAuth = new ArrayList<Auth>();
        while (objStudentFile.ready()) {
            String authInfo = objStudentFile.readLine();
            if (!authInfo.equals("")) {
                this.vAuth.add(new Auth(authInfo));
            }
        }
        objStudentFile.close();
    }

    public Auth getAuth(String loginId) {
        for (int i = 0; i < this.vAuth.size(); i++) {
            Auth objAuth = (Auth) this.vAuth.get(i);
            if (objAuth.match(loginId)) {
                return objAuth;
            }
        }
        return null;
    }
}
