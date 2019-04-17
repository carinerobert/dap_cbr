package fr.hoc.dap.server.services;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

//TODO cbr by Djer |JavaDoc| Documente uniquement ta classe.
/**
* The configuration programme implements a web application
* that displays users details account on google services.
* @author house_Mecrob
* @version 1.0
* @since 2019-01-21
* @param <StoredCredentials> create a map.
* @throws IOException If there was an IO error during parsing exception need to be treated.
* @throws still treat the thread till a GeneralSecurityException occurred, treated with a specified message, 
* wich is saved for later retrieval by the #getCause() method.
* @return autorisations .
* @param userKey .
*/
@Service
public class AdminService extends GoogleService {

    /**@author display errors.*/
    private static final Logger LOG = LogManager.getLogger();

    //TODO cbr by Djer |JavaDoc| Il manque la JavaDoc pour cette méthode
    public DataStore<StoredCredential> getUsers() throws GeneralSecurityException, IOException {
        DataStore<StoredCredential> datas = getFlow().getCredentialDataStore();
        if (null == datas) {
            //TODO cbr by Djer |log4J| Contextualise tes messages : "No users found uin DataStore"
            LOG.error("no data avaible");
        }
        return datas;
    }

    //TODO cbr by Djer |JavaDoc| Il manque la JavaDoc pour cette méthode
    public DataStore<StoredCredential> deleteUser(String user) throws IOException, GeneralSecurityException {
        DataStore<StoredCredential> datas = getFlow().getCredentialDataStore().delete(user);

        return datas;
    }

    //    public void addUser(String user) throws IOException, GeneralSecurityException {
    //        getFlow().getCredentialDataStore().add(user);
    //    }

}
