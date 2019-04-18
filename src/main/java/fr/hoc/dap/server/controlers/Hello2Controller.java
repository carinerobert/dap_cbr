package fr.hoc.dap.server.controlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author house
 *
 */
@Controller
public class Hello2Controller {

    //TODO cbr by Djer |JavaDoc| Il manque la JavaDoc pour la méthode
    @RequestMapping("/hello2")
    public String hello(ModelMap model) {

        model.addAttribute("maVar", "ToTo");

        List<String> bestioles = new ArrayList<>();

        bestioles.add("Chien");
        bestioles.add("Zèbre");
        bestioles.add("Suricate");
        bestioles.add("MeCrob");
        bestioles.add("Troll");

        model.addAttribute("bebetes", bestioles);

        return "hello";
    }

}
