package fr.hoc.dap.server.services;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.people.v1.PeopleServiceScopes;

import fr.hoc.dap.server.controlers.Configuration;

/**
* The CalendarService programme implements an application (Dap cmdline)
* that displays mails & events from an user account on google.
* it could add, delete or access to the details of user too.
* @author house_Mecrob
* @version 1.0
* @since 2019-01-21
* public class Service extends Object provide the client view of a Web service.
* Service acts as a factory of the following : Proxies for a target service endpoint.
* Instances of Dispatch for dynamic message-oriented invocation of a remote operation.
* The ports available on a service can be enumerated using the getPorts method. Alternatively,
* you can pass a service endpoint interface to the unary getPort method and let the runtime select a compatible port.
* Handler chains for all the objects created by a Service can be set by means of a HandlerResolver.
* An Executor may be set on the service in order to gain better control over the threads
* used to dispatch asynchronous callbacks.
* For instance, thread pooling with certain parameters can be enabled by creating a ThreadPoolExecutor
* and registering it with the service.
*/
public class GoogleService {

    /**
     * .
     */
    @Autowired
    private Configuration conf;

    /**
     * list .
     */
    private static List<String> scopes;

    /** @param JsonFactorye pour autorisation */
    protected static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * @throws IOException If there was an IO error during parsing exception need to be treated.
     * @throws still treat the thread till a GeneralSecurityException occurred, treated with a specified message, 
     * wich is saved for later retrieval by the #getCause() method.
     * @return autorisations .
     * @param userKey .
     */
    public Credential getCredentials(final String userKey) throws IOException, GeneralSecurityException {

        GoogleAuthorizationCodeFlow flow = getFlow();

        //LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(PORT).build();
        //return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        return flow.loadCredential(userKey);
    }

    /**
     * @return strings.
     * @throws IOException If there was an IO error during parsing exception need to be treated.
     * @throws still treat the thread till a GeneralSecurityException occurred, treated with a specified message, 
     * wich is saved for later retrieval by the #getCause() method.
     */
    public GoogleAuthorizationCodeFlow getFlow() throws GeneralSecurityException, IOException {
        // gère les autorisations google.
        scopes = new ArrayList<String>();
        scopes.add(CalendarScopes.CALENDAR_READONLY);
        scopes.add(GmailScopes.GMAIL_READONLY);
        scopes.add(GmailScopes.GMAIL_LABELS);
        scopes.add(PeopleServiceScopes.CONTACTS_READONLY);
        scopes.add(PeopleServiceScopes.USER_EMAILS_READ);
        scopes.add(PeopleServiceScopes.USERINFO_PROFILE);
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        File in = new java.io.File(conf.getCredentialsFilePath());
        Reader targetReader = new FileReader(in);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, targetReader);
        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY,
                clientSecrets, scopes)
                        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(conf.getTokensDirectoryPath())))
                        .setAccessType("offline").build();
        return flow;

    }

    /**
     * @return the conf
     */
    public Configuration getConf() {
        return conf;
    }

}
