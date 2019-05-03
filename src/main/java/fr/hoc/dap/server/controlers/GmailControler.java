package fr.hoc.dap.server.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.services.GmailService;

/** GmailController get the number of unread email(s) . */
@RestController
public class GmailControler {

    /**
     * return the nb of unread email(s). */
    @Autowired
    private GmailService gmailSrv;

    /**
    * @return the number of unread email for a user.
    * @throws Exception still treat the thread till a Exception occurred, treated with a specified message.
    * @param userKey get the nb of unread email(s).
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
