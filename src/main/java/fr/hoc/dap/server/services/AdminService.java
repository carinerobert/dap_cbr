package fr.hoc.dap.server.services;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

/**
* The AdminService get datas from a user.
* @throws IOException If there was an IO error during parsing exception need to be treated.
* @throws still treat the thread till a GeneralSecurityException occurred, treated with a specified message,
* wich is saved for later retrieval by the #getCause() method.
* @return autorisations .
*/
@Service
public class AdminService extends GoogleService {

    /** display messages for dev in a file.*/
    private static final Logger LOG = LogManager.getLogger();

    /**
     * building datas for a user .
     * @return datas from a user .
     * @throws GeneralSecurityException while building secutity connection.
     * @throws IOException while building connection.
     */
    public DataStore<StoredCredential> getUsers() throws GeneralSecurityException, IOException {
        DataStore<StoredCredential> datas = getFlow().getCredentialDataStore();
        if (null == datas) {
            //cbr by Djer |log4J| Contextualise tes messages : "No users found uin DataStore"
            LOG.error("No User found in the DataStore !");
        }
        return datas;
    }

    /**
     * delete user.
     * @param user needded.
     * @return inforamtion of delete.
     * @throws IOException while building the connection.
     * @throws GeneralSecurityException while building the connection with security information.
     */
    public DataStore<StoredCredential> deleteUser(final String user) throws IOException, GeneralSecurityException {
        DataStore<StoredCredential> datas = getFlow().getCredentialDataStore().delete(user);

        return datas;
    }

    //    public void addUser(String user) throws IOException, GeneralSecurityException {
    //        getFlow().getCredentialDataStore().add(user);
    //    }

}
