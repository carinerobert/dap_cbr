package fr.hoc.dap.server.controlers;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * The configuration class get details of each user's account by admin controler.
 */
public class Configuration {

    /**
     * name of the app.
     **/
    protected static final String APPLICATION_NAME = "House Of Code *}";
    /** where to find credentials for the app. */
    private static final String CREDENTIALS_FILE_PATH = System.getProperty("user.home") + "/dap/credentials_web.json";
    /** TOKENS_DIRECTORY_PATH indicate where to stock tokens. */
    private static final String TOKENS_DIRECTORY_PATH = System.getProperty("user.home") + "/dap/tokens";
    /** retrieve authorization callback from an OAuth2 service provider.*/
    private static final String O_AUTH_2_CALLBACK_URL = "/oAuth2Callback";

    /** Retrieve the app name. **/
    private String applicationName;

    /**retrieve credentials .*/
    private String credentialsFilePath;

    /** retrieve tokens from a file. */
    private String tokensDirectoryPath;
    /** retrieve authorization callback from an OAuth2 service provider.*/
    private String oAuth2CallbackUrl;

    /**
     * Config initialization.
     */
    @Autowired
    public Configuration() {
        init();
    }

    /**
     * Initialize config, add all values needed.
     */
    private void init() {
        this.applicationName = APPLICATION_NAME;
        this.credentialsFilePath = CREDENTIALS_FILE_PATH;
        this.tokensDirectoryPath = TOKENS_DIRECTORY_PATH;
        this.oAuth2CallbackUrl = O_AUTH_2_CALLBACK_URL;
    }

    /**
     * @return the name of the app choosen in config.
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * @param newApplicationName the applicationName to set, or needed to be changed.
     */
    public void setApplicationName(final String newApplicationName) {
        this.applicationName = newApplicationName;
    }

    /**
     * @return  A JSON Object needed.
     */
    public String getCredentialsFilePath() {
        return credentialsFilePath;
    }

    /** @param newCredentialsFilePath set a new JSON Object needed. */
    public void setCredentialsFilePath(final String newCredentialsFilePath) {
        this.credentialsFilePath = newCredentialsFilePath;
    }

    /** @return tokens.*/
    public String getTokensDirectoryPath() {
        return tokensDirectoryPath;
    }

    /** @param newTokensDirectoryPath new tokens.*/
    public void setTokensDirectoryPath(final String newTokensDirectoryPath) {
        // cbr by Djer |POO| Pourrais devenir "Zero Conf" comme les autes attributs
        this.tokensDirectoryPath = newTokensDirectoryPath;
    }

    //    /** @return the authorization callback from an OAuth 2 service provider.*/
    //    public String getOAuth2CallbackUrl() {
    //        //cbr by Djer |Design Patern| Pourrais devenir "Zero Conf" comme les autres attributs
    //        return oAuth2Callback;
    //    }

    /**
     * @return the oAuth2CallbackUrl
     */
    public String getoAuth2CallbackUrl() {
        return oAuth2CallbackUrl;
    }

    /**
     * @param oAuth the oAuth2CallbackUrl to set
     */
    //    public void setoAuth2CallbackUrl(final String oAuth) {
    //        this.oAuth2CallbackUrl = oAuth2CallbackUrl;
    //    }
}
