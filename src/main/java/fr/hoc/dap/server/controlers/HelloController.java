package fr.hoc.dap.server.controlers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The HelloController is a SpringBoot tutorial.
 */

@RestController
public class HelloController {

    /**
     * @return message.
     */
    @RequestMapping("/hello")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    /**
     * Mapping web requests onto methods in request-handling with flexible method signatures.
     * @param theName please fill the field. .
     * @return message.
     */
    @RequestMapping("/bonjour")
    public String direBonjour(@RequestParam("name") final String theName) {
        return "Bonjour" + " " + theName + " !";
    }
}
