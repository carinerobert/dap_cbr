package fr.hoc.dap.server.controlers;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

import fr.hoc.dap.server.services.AdminService;

//TODO cbr by Djer |JavaDoc| Documente cette classe **specifique** sans parler du "programe globale". ladocumentation "général" de ton appli pourais se trovuer dnas le README.md
/**
 * The configuration programme implements a web application
 * that displays users details account on google services.
 * @author house_Mecrob
 * @version 1.0
 * @since 2019-01-21
 */
@Controller
public class AdminControler {
    //TODO cbr by Djer |JavaDoc| Ne met pas de "@Author" ici tu essayes de préiciser l'auteur de cette attribut, ce qui n'est pas très utile
    /**@author display errors.*/
    private static final Logger LOG = LogManager.getLogger();

    //TODO cbr by Djer |JavaDoc| Il manque la Javadoc pour documenter cette attribut
    @Autowired
    private AdminService admSrv;

    //TODO cbr by Djer |JavaDoc| Il manque la Javadoc pour documenter cette méthode
    //TODO cbr by Djer |Audit Code| prend en comptel a remarque de CheckStyle ("pourrait être final")
    @RequestMapping("/admin")
    public String admin(ModelMap model) throws GeneralSecurityException, IOException {
        DataStore<StoredCredential> users = admSrv.getUsers();
        Map<String, StoredCredential> mapUsers = new HashMap<>();
        Set<String> keys = users.keySet();
        for (String key : keys) {
            StoredCredential value = users.get(key);
            mapUsers.put(key, value);
          //TODO cbr by Djer |Log4J| Le message de cette log est faux. Ici tu peux indiquer (en Info ou Debug) qu'un "user à été convertie " + key
            LOG.warn("Trying to store a NULL AccessToken for user : " + keys);

        }

        model.addAttribute("users", mapUsers);
        return "admin";
    }

    //TODO cbr by Djer |JavaDoc| Il manque la Javadoc pour documenter cette attribut
    @RequestMapping
    public String deleteUser(final String userkey) throws IOException, GeneralSecurityException {

        DataStore<StoredCredential> user = admSrv.deleteUser(userkey);
      //TODO cbr by Djer |Log4J| Le message de cette log est ambigüe "User " + userkey " deleted" serait mieux
        LOG.warn("Trying to delete unkown user : " + user);

        return "redirect:/admin";
    }

}
