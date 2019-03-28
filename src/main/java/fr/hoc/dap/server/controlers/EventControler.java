package fr.hoc.dap.server.controlers;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.services.CalendarService;

/**
 * The eventController programme implements an application (Dap cmdline)
 * that displays mails & events from an user account on google.
 * it could add new user too !
 * @author house_Mecrob
 * @version 1.0
 * @since 2019-01-21
 */

/**
 * Types that carry this annotation are treated as controllers where @RequestMapping methods exists.
 * The value may indicate a suggestion for a logical component name,
 * to be turned into a Spring bean in case of an autodetected component.
 */
@RestController
public class EventControler {

    /**
     * @RequestMapping for mapping web requests onto methods in request-handling with flexible method signatures.
     * @return the mapped request.
     * @throws Exception If there was an IO error during parsing.
     */
    @Autowired
    private CalendarService eventSrv;

    /**
     * The method used for creating the display of the next event. Any structural
     * modifications to the display of the app should be done
     * by overriding this method.
     * <p>
     * @param nb please set the default value.
     * @param userKey please set for user in the app cmdline.
     * @return the next event for a user.
     * @throws IOException If there was an IO error during parsing .
     * @throws GeneralSecurityException If an error occurred.
     */
    @RequestMapping("/event/next")
    public String displayNextEvent(@RequestParam() final String userKey,
            @RequestParam(value = "nb", defaultValue = "3") final Integer nb)
            throws IOException, GeneralSecurityException {

        return eventSrv.displayNextEvent(nb, userKey);
    }

}
