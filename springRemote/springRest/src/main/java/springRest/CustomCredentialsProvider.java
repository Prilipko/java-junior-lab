package springRest;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;

/**
 * Created by Alexander Prilipko on 23.03.2017.
 */
public class CustomCredentialsProvider extends BasicCredentialsProvider {

    public void setCredentials(UsernamePasswordCredentials credentials) {
        this.setCredentials(AuthScope.ANY, credentials);
    }
}
