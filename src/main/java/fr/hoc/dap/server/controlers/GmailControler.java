package fr.hoc.dap.server.controlers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.services.GmailService;

/**
 * The eventController programme implements a web application.
 * that displays mails & events from an user account on google.
 * it could add, delete or access to the details of user too.
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
public class GmailControler {
    //TODO cbr by Djer |JavaDoc| Les @xxxx ne sont pas pertinent pour javaDocumenter un attribut, seul la description (sans @ devant) est utile ici
    /**
     * @RequestMapping for mapping web requests onto methods in request-handling with flexible method signatures.
     * @return the string needed for mapping.
     * @throws IOException If there was an IO error during parsing exception need to be treated.
     */
    @Autowired
    private GmailService gmailSrv;

    /**
     * The method used for creating the display of the next event. Any structural
     * modifications to the display of the app should be done
     * by overriding this method.
     * <p>
     * //TODO cbr by Djer |JavaDoc| Le "format" d'une ligne de JavaDoc avec parametre est @paramType **Contexte** Ta description à toi que tu veux (ici tu as une virgule ne trop après le "contexte" (nom du param que tu souhaites décrire))
     * @param userKey, please set for user in the app.
     * @return the number of unread email for a user.
     * @throws still treat the thread till a Exception occurred, treated with a specified message, 
     * wich is saved for later retrieval by the #getCause() method.
     */
    @RequestMapping("/email/nbunread")
    public Integer index(@RequestParam() final String userKey) throws Exception {
        // traitement des données
        Integer nbUnreadEmail = gmailSrv.displayMessageUnread(userKey);

        // renvoie des données
        return nbUnreadEmail;
    }
}
