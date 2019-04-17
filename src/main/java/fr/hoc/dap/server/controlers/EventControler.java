package fr.hoc.dap.server.controlers;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.services.CalendarService;

/**
 * The eventController programme implements  a web application
 * that displays mails & events from an user account on google.
 * it could add, or delete user too.
 * @author house_Mecrob
 * @version 1.0
 * @since 2019-01-21
 */

//TODO cbr by Djer |JavaDoc| Documente TA classe, l'annotation est déja documentée (par Spring)
/**
 * Types that carry this annotation are treated as controllers where @RequestMapping methods exists.
 * The value may indicate a suggestion for a logical component name,
 * to be turned into a Spring bean in case of an autodetected component.
 */
@RestController
public class EventControler {

    //TODO cbr by Djer |JavaDoc| Les @xxxx ne sont pas pertinent pour javaDocumenter un attribut, seul la description (sans @ devant) est utile ici
    /**
     * @RequestMapping for mapping web requests onto methods in request-handling with flexible method signatures.
     * @return the mapped request.
     * @throws IOException If there was an IO error during parsing exception need to be treated.
     */
    @Autowired
    private CalendarService eventSrv;

    /**
     * The method used for creating the display of the next event. Any structural
     * modifications to the display of the app should be done
     * by overriding this method.
     * <p>
     * @param please set a number in the default value.
     * @param userKey please set for user in the app.
     * @return the next event for a user, as a string.
     * @throws IOException If there was an IO error during parsing exception need to be treated.
     * @throws GeneralSecurityException still treat the thread till a GeneralSecurityException occurred, treated with a specified message,
     * wich is saved for later retrieval by the #getCause() method.
     */
    @RequestMapping("/event/next")
    public String displayNextEvent(@RequestParam() final String userKey,
            @RequestParam(value = "nb", defaultValue = "3") final Integer nb)
            throws IOException, GeneralSecurityException {

        return eventSrv.displayNextEvent(nb, userKey);
    }

}
