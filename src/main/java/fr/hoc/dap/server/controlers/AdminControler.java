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
 * The configuration programme implements a web application
 * that displays users details account on google services.
 * @author house_Mecrob
 * @version 1.0
 * @since 2019-01-21
 */
@Controller
public class AdminControler {
    /**@author display errors.*/
    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    private AdminService admSrv;

    @RequestMapping("/admin")
    public String admin(ModelMap model) throws GeneralSecurityException, IOException {
        DataStore<StoredCredential> users = admSrv.getUsers();
        Map<String, StoredCredential> mapUsers = new HashMap<>();
        Set<String> keys = users.keySet();
        for (String key : keys) {
            StoredCredential value = users.get(key);
            mapUsers.put(key, value);
            LOG.warn("Trying to store a NULL AccessToken for user : " + keys);

        }

        model.addAttribute("users", mapUsers);
        return "admin";
    }

    @RequestMapping
    public String deleteUser(final String userkey) throws IOException, GeneralSecurityException {

        DataStore<StoredCredential> user = admSrv.deleteUser(userkey);
        LOG.warn("Trying to delete unkown user : " + user);

        return "redirect:/admin";
    }

}
