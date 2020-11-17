import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ApplicationScoped
public class AppLifeCycleBean {

    private static final int NUM_OF_ITERATIONS = 2000;
 
    private static final int MILLISECONDS_TO_SLEEP = 2000;
 
    private static final Logger LOG = LogManager.getLogger(AppLifeCycleBean.class);

    void onStart(@Observes StartupEvent ev) throws InterruptedException {               
        LOG.info("The application is starting...");
 
        LOG.trace("Mensaje de trace");
        LOG.debug("Mensaje de debug");
        LOG.info("Mensaje de info");
        LOG.warn("Mensaje de warn");
        LOG.error("Mensaje de error");

        throw new RuntimeException();
    }

    void onStop(@Observes ShutdownEvent ev) {               
        LOG.info("The application is stopping...");
    }

}