package StudentPortal;

import java.io.IOException;

public interface Portal {
    public void signUp() throws IOException;

    public void signIn() throws IOException;

    public void SignOut();
}
