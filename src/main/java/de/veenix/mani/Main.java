package de.veenix.mani;

import de.veenix.mani.data.Config;
import de.veenix.mani.threads.ServerThread;
import de.veenix.mani.utils.Misc;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {

    public static void main(String[] args) {
        log.info("Starting Server");

        // Values provided by the config.json
        final int port = Config.getInstance().getConfigObj().getPort();
        final Class<? extends ServerThread> thread = Config.getInstance().getConfigObj().getDefaultThread().getThread();

        Server server = new Server(port, thread);

        if(server.getSSocket() != null && !server.getSSocket().isClosed()) {
            log.info("Server started successfully and listens on port " + server.getPORT());

            // Keep main thread running while server is not closed
            while(!server.getSSocket().isClosed()) {
                Misc.sleep(1000);
            }

            log.info("Server stopped, closing all threads");
            server.getAcceptThread().interrupt();

            for(Runnable queueThread : server.getExecutor().getQueue()) {
                server.getExecutor().remove(queueThread);
            }

            // Wait until all thread are executed
            while(server.getExecutor().getActiveCount() > 0) {
                Misc.sleep(5);
            }
            log.info("Stopped all threads");
        } else {
            log.warn("Server stopped unexpectedly during startup.");
        }
    }
}
