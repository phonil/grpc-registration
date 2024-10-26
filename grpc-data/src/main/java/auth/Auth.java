package auth;

import java.io.Serializable;
import java.util.StringTokenizer;

public class Auth implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String loginId;
    protected String password;
    protected String personId;

    public Auth(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
        this.loginId = stringTokenizer.nextToken();
        this.password = stringTokenizer.nextToken();
        this.personId = stringTokenizer.nextToken();
    }

    public boolean match(String loginId) {
        return this.loginId.equals(loginId);
    }

    public String toString() {
        return this.loginId + " " + this.password + " " + this.personId;
    }

    public String getPassword() {
        return this.password;
    }
}
