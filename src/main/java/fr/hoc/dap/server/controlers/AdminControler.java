package fr.hoc.dap.server.controlers;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

import fr.hoc.dap.server.services.AdminService;

@Controller
public class AdminControler {

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
        }

        model.addAttribute("users", mapUsers);
        return "admin";
    }

    @RequestMapping(value = "/delete_user", method = RequestMethod.DELETE)
    public String handleDeleteUser(@RequestParam(name = "user") String user)
            throws IOException, GeneralSecurityException {

        admSrv.deleteUser(user);

        return "redirect:/admin";
    }

    //    @RequestMapping(value = "/add_user", method = RequestMethod.GET)
    //    public String addUser(@RequestParam(name = "user") String personId)
    //            throws IOException, GeneralSecurityException {
    //
    //        admSrv.addUser(personId);
    //
    //        return "redirect:/admin";
    //    }
}
