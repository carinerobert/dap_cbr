package fr.hoc.dap.server.services;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

/**@author house httpTransport ok.
 * @param <StoredCredentials>
 * @throws IOException ?
 * @throws GeneralSecurityException ?
 * @return autorisations .
 * @param userKey .
 */
@Service
public class AdminService extends GoogleService {

    public DataStore<StoredCredential> getUsers() throws GeneralSecurityException, IOException {
        DataStore<StoredCredential> datas = getFlow().getCredentialDataStore();
        return datas;
    }

}
