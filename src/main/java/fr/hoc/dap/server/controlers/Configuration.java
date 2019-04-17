package fr.hoc.dap.server.controlers;

import org.springframework.beans.factory.annotation.Autowired;

//TODO cbr by Djer |JavaDoc| Documente cette classe **specifique** sans parler du "programe globale"
/**
 * The configuration programme implements a web application
 * that displays mails & events from an user account on google.
 * it could add new user too !
 * You could find details of each user's account by admin controler
 * display on your browser.
 * @author house_Mecrob
 * @version 1.0
 * @since 2019-01-21
 */
public class Configuration {

    /**
     * //TODO cbr by Djer |JavaDoc| Il manque la "description" (première ligne de la javaDoc)
     * //TODO cbr by Djer |JavaDoc| les @PAram ne sont pas pertinents ici, il n'y a pas de paramètre sur ton attribut ! 
     * @param indicate app name.
     * @param JsonFactory neede to be treat. If value = null <code>null</code> an exception occur.
     * @param TOKENS_DIRECTORY_PATH indicate where to stock tokens.
     **/
    protected static final String APPLICATION_NAME = "House Of Code *}";

    /** @param ma porte d'entrée pour autorisation */
    private static final String CREDENTIALS_FILE_PATH = System.getProperty("user.home") + "/dap/credentials_web.json";
    /**
     * TOKENS_DIRECTORY_PATH oups.
     */
    private static final String TOKENS_DIRECTORY_PATH = System.getProperty("user.home") + "/dap/tokens";

    //TODO cbr by Djer |JavaDoc| Cette JavaDoc n'est pas/plus juste.
    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     *(Variable).*/
    private static String applicationName;

    //TODO cbr by Djer |JavaDoc| Pas utile d'indique que c'est une variable cela est deja visible car non "static final" (et javaDoc extrait automatiquement cette information)
    //TODO cbr by Djer |POO| Pourquoi static, cela ne semble pas nécéssaire. Deplus cela est risqué, si jamais tu créé deux configurations différentes (ce qui serait TRES étrange) les données de la deuxième vont écraser la première
    /**
     * variable .
     */
    private static String credentialsFilePath;

    //TODO cbr by Djer |JavaDoc| Pas utile d'indique que c'est une variable cela est deja visible car non "static final" (et javaDoc extrait automatiquement cette information)
    //TODO cbr by Djer |POO| Pourquoi static, cela ne semble pas nécéssaire.
    /**
     * variable .
     */
    private static String tokensDirectoryPath;

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
        //TODO cbr by Djer |POO| Tu pourras remplacer "Configuration" par "this" une fois le static des attributs supprimé.
        Configuration.applicationName = APPLICATION_NAME;
        Configuration.credentialsFilePath = CREDENTIALS_FILE_PATH;
        Configuration.tokensDirectoryPath = TOKENS_DIRECTORY_PATH;
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
        //TODO cbr by Djer |POO| Tu pourras remplacer "Configuration" par "this" une fois le static des attributs supprimé.
        Configuration.applicationName = newApplicationName;
    }

    /**
     * @return  A JSON Object needed.
     */
    public String getCredentialsFilePath() {
        return credentialsFilePath;
    }

    /**
     * @param newCredentialsFilePath set a new JSON Object needed.
     */
    public void setCredentialsFilePath(final String newCredentialsFilePath) {
        //TODO cbr by Djer |POO| Tu pourras remplacer "Configuration" par "this" une fois le static des attributs supprimé.
        Configuration.credentialsFilePath = newCredentialsFilePath;
    }

    /**
     * @return tokens.
     */
    public String getTokensDirectoryPath() {
        return tokensDirectoryPath;
    }

    /**
     * @param newTokensDirectoryPath new tokens.
     */
    public void setTokensDirectoryPath(final String newTokensDirectoryPath) {
        //TODO cbr by Djer |POO| Pourrais devenir "Zero Conf" comme les autes attributs
        Configuration.tokensDirectoryPath = newTokensDirectoryPath;
    }

    /***
     * @return the authorization callback from an OAuth 2 service provider.
     */
    //TODO cbr by Djer |POO| le "o" de "oAuth" devrait être en majuscule pour respecter le CamelCase
    public String getoAuth2CallbackUrl() {
        //TODO cbr by Djer |Design Patern| Pourrais devenir "Zero Conf" comme les autes attributs
        return "/oAuth2Callback";
    }
}
