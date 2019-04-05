package fr.hoc.dap.server.controlers;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

import fr.hoc.dap.server.services.AdminService;

@Controller
public class AdminControler {

    @Autowired
    private AdminService admSrv;

    @RequestMapping("/admin")
    public String admin(ModelMap model) throws GeneralSecurityException, IOException {
        model.addAttribute("maVar", "Bonjour, ");

        DataStore<StoredCredential> users = admSrv.getUsers();

        //TODO transform users to MAP

        String user = "{Id = userKey,token = Token,exp = Expire, refresh = Refresh}";

        user = user.substring(1, user.length() - 1); //remove curly brackets

        String[] keyValuePairs = user.split(","); //split the string to creat key-value pairs

        Map<String, String> map = new HashMap<>();

        for (String pair : keyValuePairs) //iterate over the pairs
        {
            String[] entry = pair.split("="); //split the pairs to get key and value 
            map.put(entry[0].trim(), entry[1].trim()); //add them to the hashmap and trim whitespaces
        }

        Map<String, StoredCredential> mapUsers = new HashMap<>();

        //List<DataStore<StoredCredential>> datas = new ArrayList<>();
        // datas.add(users);

        model.addAttribute("users", mapUsers);

        return "admin";
    }

}
