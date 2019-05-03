package fr.hoc.dap.server.data;

import org.springframework.data.repository.CrudRepository;

/**
 * Manage access for dap's users in the database.
 *  @author MeCrob
 */
public interface DapUserRepository extends CrudRepository<DapUser, Long> {

    /**
    * .
    * @param userKey .
    * @return .
    */
    DapUser findByUserKey(String userKey);

}
