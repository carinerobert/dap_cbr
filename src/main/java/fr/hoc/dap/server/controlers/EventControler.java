package fr.hoc.dap.server.controlers;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.services.CalendarService;

/**
 * The eventController displays events from an user account on google.
**/
@RestController
public class EventControler {

    /**
     * for mapping web requests onto methods in request-handling with flexible method signatures.
     * @return the mapped request.
     * @throws IOException If there was an IO error during parsing exception need to be treated.
     */
    @Autowired
    private CalendarService eventSrv;

    /**
     * The method used for creating the display of the next event. Any structural
     * modifications to the display of the app should be done
     * by overriding this method.
     * @param loginName please set for user in the app.
     * @return the next event for a user, as a string.
     * @throws IOException If there was an IO error during parsing exception need to be treated.
     * @throws GeneralSecurityException still treat the thread till a GeneralSecurityException occurred,
     * treated with a specified message,
     * @param nb value by default.
     * wich is saved for later retrieval by the #getCause() method.
     */
    @RequestMapping("/event/next")
    public String displayNextEvent(@RequestParam() final String loginName,
            @RequestParam(value = "nb", defaultValue = "3") final Integer nb)
            throws IOException, GeneralSecurityException {

        return eventSrv.displayNextEvent(nb, loginName);
    }

}
