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
 * The CalendarService programme implements a web application.
 * Provide the client view of a Web service.
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

    /** display messages for dev in a file.*/
    private static final Logger LOG = LogManager.getLogger();

    /**
     * Build API client service.
     * @param userKey get user as a string.
     * @return return userkey as a string.
     * @throws IOException If there was an IO error during parsing exception need to be treated.
     * @throws GeneralSecurityException still tread the thread till a GeneralSecurityException occurred.
     */
    public Calendar buildService(final String userKey) throws IOException, GeneralSecurityException {
        NetHttpTransport httpTransport;
        Calendar service = null;

        try {
            LOG.info("Google Calendar service built for user : " + userKey);
        } catch (UncheckedIOException e) {
            LOG.error(userKey + " : Error while building Google Calendar Service " + e);
        }
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        service = new Calendar.Builder(httpTransport, JSON_FACTORY, getCredentials(userKey))
                .setApplicationName(getConf().getApplicationName()).build();
        return service;

    }

    /**
     * Build API client service.
     * @return string of the instance .
     * @throws IOException If there was an IO error during parsing exception need to be treated.
     * @throws GeneralSecurityException Still treat the thread till a GeneralSecurityException occurred,
     * treated with a specified message, wich is saved for later retrieval by the #getCause() method.
     */
    //    public CalendarService getInstance() throws IOException, GeneralSecurityException {
    //        CalendarService service = null;
    //        return service;
    //
    //    }
    /**
    *display nextEvent for a user.
    * @param nb the next event for a user, as a string.
    * @param userKey recupereation user .
    * @throws IOException If there was an IO error during parsing exception need to be treated.
    * @throws GeneralSecurityException still treat the thread till a GeneralSecurityException occurred.
    * @return textual representation of next event(s).
    */
    public String displayNextEvent(final Integer nb, final String userKey)
            throws IOException, GeneralSecurityException {
        /** DateTime List the next 10 events from the primary calendar.*/
        Calendar service = buildService(userKey);
        DateTime now = new DateTime(System.currentTimeMillis());

        /**
        * @throws IOException If there was an IO error during parsing exception need to be treated.
        * @throws still tread the thread till a GeneralSecurityException occurred.
         */
        Events events = service.events().list("primary").setMaxResults(1).setTimeMin(now).setOrderBy("startTime")
                .setSingleEvents(true).execute();

        /**
         * List Event return the event as a list.
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
                // System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }

        return rdv;
    }

}
