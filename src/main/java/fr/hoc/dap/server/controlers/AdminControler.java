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

/**
 * Controller class for dap server administration.
 */
@Controller
public class AdminControler {

    /** display messages for dev in a file.*/
    private static final Logger LOG = LogManager.getLogger();

    /** SpringBoot App automation.
     */
    @Autowired
    private AdminService admSrv;

    /**@return user's mapping.
     * @param model of nmapping.
     * @throws GeneralSecurityException if error occuring when connect to server.
     * @throws IOException if error occuring when trying to connect.
     */
    @RequestMapping("/admin")
    public final String admin(final ModelMap model) throws GeneralSecurityException, IOException {
        DataStore<StoredCredential> users = admSrv.getUsers();
        Map<String, StoredCredential> mapUsers = new HashMap<>();
        Set<String> keys = users.keySet();
        for (String key : keys) {
            StoredCredential value = users.get(key);
            mapUsers.put(key, value);
            // cbr by Djer |Log4J| Le message de cette log est faux. Ici tu peux indiquer
            //(en Info ou Debug) qu'un "user à été convertie " + key
            LOG.info("Converted for : " + keys + " ok");

        }

        model.addAttribute("users", mapUsers);
        return "admin";
    }

    /**
     * @return the <code>admin</code> page.
     * @param userkey had to be deleted.
     * @throws GeneralSecurityException if error occuring when connect to server.
     * @throws IOException if error occuring when trying to connect.
     */
    @RequestMapping
    public String deleteUser(final String userkey) throws IOException, GeneralSecurityException {

        DataStore<StoredCredential> user = admSrv.deleteUser(userkey);
        // cbr by Djer |Log4J| Le message de cette log est ambigüe "User " + userkey " deleted" serait mieux
        LOG.warn(user + " deleted");

        return "redirect:/admin";
    }

}
