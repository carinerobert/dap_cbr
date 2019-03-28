package fr.hoc.dap.server.services;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

/**
 * The CalendarService programme implements an application (Dap cmdline)
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
public final class CalendarService extends GoogleService {
    /**@author house log*/

    private static final Logger LOG = LogManager.getLogger();

    /**
     * Build API client service.
     * @param userKey .
     * @return .
     * @throws IOException exceptions.
     * @throws GeneralSecurityException sécurité
     */
    public Calendar getService(final String userKey) throws IOException, GeneralSecurityException {
        NetHttpTransport httpTransport;
        Calendar service = null;

        try {
            LOG.info("getService ok"); // équivaut à logger.info("Message d'information");
        } catch (UncheckedIOException e) {
            LOG.error("fatal error");
        }
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        service = new Calendar.Builder(httpTransport, JSON_FACTORY, getCredentials(userKey))
                .setApplicationName(getConf().getApplicationName()).build();
        return service;

    }

    /**
     * Build API client service.
     * @return .
     * @throws IOException exceptions.
     * @throws GeneralSecurityException sécurité
     */
    public CalendarService getInstance() throws IOException, GeneralSecurityException {
        CalendarService service = null;
        return service;

    }

    /**
     * @author house.
     * @param nb .
     * @param userKey.
     * @param userKey recupereation user .
     * @throws IOException .
     * @throws GeneralSecurityException .
     * @return next event.
     */
    public String displayNextEvent(final Integer nb, final String userKey)
            throws IOException, GeneralSecurityException {
        /** DateTime List the next 10 events from the primary calendar.*/
        Calendar service = getService(userKey);
        DateTime now = new DateTime(System.currentTimeMillis());

        /**
         * @throws IOException .
         * @throws GeneralSecurityException .
         */
        Events events = service.events().list("primary").setMaxResults(1).setTimeMin(now).setOrderBy("startTime")
                .setSingleEvents(true).execute();

        /**
         * List Event list event .
         */
        List<Event> items = events.getItems();
        String rdv;

        if (items.isEmpty()) {
            rdv = "No upcoming event found !";
        } else {
            rdv = "Next event : ";
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                rdv = rdv + event.getSummary() + "(" + start + ") / ";
                System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }

        return rdv;
    }

}
