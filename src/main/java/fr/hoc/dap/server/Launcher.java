//package fr.hoc.dap.server;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
///**
// * Global instance for run the application.
// */
//@SpringBootApplication
//// cbr by Djer |Spring| Cette classe n'est plus utile, tu peux la supprimer (de plus c'est un peu "hasardeux d'avoir
// 2 @SpringBootApplication dnas un même projet)
//abstract class Launcher {
//    /**@author */
//
//    private static final Logger LOG = LogManager.getLogger();
//
//    /**
//     * Methode main for run the application.
//     * @param args arguments exceptions
//     * @throws Exception .
//     */
//    public static void main(final String[] args) throws Exception {
//        SpringApplication.run(Launcher.class, args);
//
//        LOG.error("Ceci est une erreur :(");
//        LOG.debug("Ceci devrait être un bug ;)");
//
//        //        Configuration myConfig = new Configuration();
//        //        LOG.info(myConfig.getApplicationName() + (""));
//        //
//        //        CalendarService moncalendrier = CalendarService.getInstance();
//        //        moncalendrier.setConfig(myConfig);
//        //        moncalendrier.nextEvent();
//        //
//        //        // People personne = People.getInstance();
//        //        // personne.setConfig(myConfig);
//        //        // personne.people();
//        //
//        //        GmailService mesmails = GmailService.getInstance();
//        //        mesmails.setConfig(myConfig);
//        //        mesmails.retrieveNbUnreadEmail();
//
//    }
//
//}
