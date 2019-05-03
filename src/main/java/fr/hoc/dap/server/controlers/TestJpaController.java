package fr.hoc.dap.server.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.data.DapUser;
import fr.hoc.dap.server.data.DapUserRepository;

/**
 * For testing Jpa's entity.
 *@author MeCrob
 */
@RestController
public class TestJpaController {

    /**
    * .
    */
    @Autowired
    private DapUserRepository dapUserRepo;

    /**Create a new Dap's user with a userkey
     * calling example : /test/createDapUser?loginName=Djer&userKey=DjerPerso.
     * @param loginName login Name
     * @param userKey key for user
     * @return saved user.
     * */
    @RequestMapping("/test/createDapUser")
    public DapUser createDapUser(@RequestParam final String loginName, @RequestParam final String userKey) {

        DapUser monUser = new DapUser();
        monUser.setLoginName(loginName);
        monUser.setUserKey(userKey);

        DapUser savedUser = dapUserRepo.save(monUser);

        return savedUser;
    }

    /**Create a new Dap's user with a userkey
     * calling example : /test/createDapUser?loginName=Djer&userKey=DjerPerso.
     * @param userKey key for user
     * @return saved user.
     * */
    @RequestMapping("/test/loadDapUser")
    public DapUser loadDapUser(@RequestParam final String userKey) {

        DapUser loadedUser = dapUserRepo.findByUserKey(userKey);

        return loadedUser;
    }
}
