package fr.hoc.dap.server.services;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

/**
* The GmailService programme implements an application (Dap cmdline)
* that displays mails & events from an user account on google.
* it could add new user too !
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
@Service
public final class GmailService extends GoogleService {

    /**
     * @param userKey .
     * @return .
     * @throws GeneralSecurityException cannot connect to google sever.
     * @throws IOException if the credentials.json file cannot be found.
     */
    //@Autowired
    public Gmail getService(final String userKey) throws GeneralSecurityException, IOException {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        Gmail service = new Gmail.Builder(httpTransport, JSON_FACTORY, getCredentials(userKey))
                .setApplicationName(getConf().getApplicationName()).build();

        return service;
    }

    /**
     * @param userKey DaP user ID
     * @param userId Google User ID
     * @param query .
     * @return .
     * @throws IOException if the credentials.json file cannot be found.
     * @throws GeneralSecurityException cannot connect to google sever.
     */
    public int listMessagesMatchingQuery(final String userKey, final String userId, final String query)
            throws IOException, GeneralSecurityException {
        ListMessagesResponse response = getService(userKey).users().messages().list(userId).setQ(query).execute();

        List<Message> messages = new ArrayList<Message>();
        while (response.getMessages() != null) {
            messages.addAll(response.getMessages());
            if (response.getNextPageToken() != null) {
                String pageToken = response.getNextPageToken();
                response = getService(userKey).users().messages().list(userId).setQ(query).setPageToken(pageToken)
                        .execute();
            } else {
                break;
            }
        }

        return messages.size();
    }

    /**
     * @param userKey DaP user ID
     * @throws Exception .
     * @return .
     */
    public Integer displayMessageUnread(final String userKey) throws Exception {

        String userId = "me";
        //        // Print the labels in the user's account.
        String query = "is:unread in:inbox";

        Integer nbUnreadEmail = listMessagesMatchingQuery(userKey, userId, query);

        return nbUnreadEmail;

    }

}
