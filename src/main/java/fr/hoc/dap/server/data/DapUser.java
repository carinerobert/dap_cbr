package fr.hoc.dap.server.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Contains all the attributes to manage a DaP user.
 * @author MeCrob.
 */
@Entity
public class DapUser {

    /** DaP User Id. */
    @Id
    @GeneratedValue
    private Long id;
    /** DaP userKey (to store in Google Credential). */
    @Column(nullable = false, unique = true)
    private String userKey;
    /** Dap User login name . */
    private String loginName;

    /** @return the id */
    public Long getId() {
        return id;
    }

    /** @param newId the id to set */
    public void setId(final Long newId) {
        this.id = newId;
    }

    /** @return the userKey */
    public String getUserKey() {
        return userKey;
    }

    /** @param newUserKey the userKey to set */
    public void setUserKey(final String newUserKey) {
        this.userKey = newUserKey;
    }

    /** @return the loginName */
    public String getLoginName() {
        return loginName;
    }

    /** @param newLoginName the login name to set */
    public void setLoginName(final String newLoginName) {
        this.loginName = newLoginName;
    }

}
