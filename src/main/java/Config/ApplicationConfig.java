package Config;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import jakarta.ws.rs.ApplicationPath;
import servisesImplementation.EtudiantServisesImplementation;

@ApplicationPath("/api") // Adjust the base path as needed
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig() {
        // Register resource and implementation classes
    	packages("myproject"); 
        register(EtudiantServisesImplementation.class);
        register(JacksonFeature.class);
    	packages("Config"); 

        register(CORSFilter.class);


    }
}