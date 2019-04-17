package fr.hoc.dap.server.controlers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class HelloController {
    //TODO cbr by Djer |JavaDoc| Ne documente pas l'annotation, mais ta méthode. L'anotation est deja JavaDocumentée (par Spring)
    /**
     * @RequestMapping for mapping web requests onto methods in request-handling with flexible method signatures.
     * @return the mapped request.
     */
    @RequestMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    //TODO cbr by Djer |JavaDoc| Ne documente pas l'annotation, mais ta méthode. L'anotation est deja JavaDocumentée (par Spring)
    /**
     * @RequestMapping for mapping web requests onto methods in request-handling with flexible method signatures.
     * @param theName please fill the field. .
     * @return /bonjour.
     */
    @RequestMapping("/bonjour")
    public String direBonjour(@RequestParam("name") final String theName) {
        return "Bonjour" + " " + theName + " !";
    }
}
