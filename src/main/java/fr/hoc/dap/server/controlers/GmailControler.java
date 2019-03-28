package fr.hoc.dap.server.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.services.GmailService;

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
public class GmailControler {
    /**
     * @RequestMapping for mapping web requests onto methods in request-handling with flexible method signatures.
     * @return the mapped request.
     * @throws Exception If there was an IO error during parsing.
     */
    @Autowired
    private GmailService gmailSrv;

    /**
     * The method used for creating the display of the next event. Any structural
     * modifications to the display of the app should be done
     * by overriding this method.
     * <p>
     * @param userKey please set for user in the app cmdline.
     * @return the unread email for a user.
     * @throws Exception If there was an IO error during parsing .
     */
    @RequestMapping("/email/nbunread")
    public Integer index(@RequestParam() final String userKey) throws Exception {
        // traitement des données
        Integer nbUnreadEmail = gmailSrv.displayMessageUnread(userKey);

        // renvoie des données
        return nbUnreadEmail;
    }
}
