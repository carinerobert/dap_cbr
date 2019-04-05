package fr.hoc.dap.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Global instance for run the application.
 */
@SpringBootApplication
abstract class Launcher {
    /**@author house log*/

    //private static final Logger LOG = LogManager.getLogger();

    /**
     * Methode main for run the application.
     * @param args arguments exceptions
     * @throws Exception .
     */
    public static void main(final String[] args) throws Exception {
        SpringApplication.run(Launcher.class, args);

        /*LOG.error("Ceci est une erreur :(");
        LOG.debug("Ceci devrait être un bug ;)");*/

        //        Configuration myConfig = new Configuration();
        //        LOG.info(myConfig.getApplicationName() + (""));
        //
        //        CalendarService moncalendrier = CalendarService.getInstance();
        //        moncalendrier.setConfig(myConfig);
        //        moncalendrier.nextEvent();
        //
        //        // People personne = People.getInstance();
        //        // personne.setConfig(myConfig);
        //        // personne.people();
        //
        //        GmailService mesmails = GmailService.getInstance();
        //        mesmails.setConfig(myConfig);
        //        mesmails.retrieveNbUnreadEmail();

    }

}
