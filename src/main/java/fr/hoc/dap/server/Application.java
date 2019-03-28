package fr.hoc.dap.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import fr.hoc.dap.server.controlers.Configuration;

/**
 * The server programme implements an application (Dap cmdline)
 * that displays mails & events from an user account on google.
 * it could add new user too !
 * @author house_Mecrob
 * @version 1.0
 * @since 2019-01-21
 */
@SpringBootApplication
public class Application {

    /**
     * @param args command line for Springboot app.
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);

    }

    /**
     * @see display Springboot App in the console .
     * @return app result if true.
     * @param ctx ()during parsing.
     */
    public CommandLineRunner commandLineRunner(final ApplicationContext ctx) {
        return args -> {

        };
    }

    /**
    * @Bean instantiate and configure default configuration for users.
    * @return configuration with default values.
    * @throws Exception If there was an IO error during parsing.
    */
    @Bean
    public Configuration createDefault() throws Exception {
        Configuration conf = new Configuration();
        //  conf.setCredentialsFilePath("/credentials_web.json");
        conf.setApplicationName("");

        return conf;
    }

}
