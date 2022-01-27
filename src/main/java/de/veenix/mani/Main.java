package de.veenix.mani;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {

    public static void main(String[] args) {
        log.info("Starting Server");
        //TODO: Update default server thread otherwise the server can't start
        Server server = new Server(80, null);
        if(server.getSSocket() != null && !server.getSSocket().isClosed()) {
            log.info("Server started successfully and listens on port " + server.getPORT());
            while(!server.getSSocket().isClosed()) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            log.info("Server stopped, closing all threads");
            server.getAcceptThread().interrupt();

            for(Runnable thread : server.getExecutor().getQueue()) {
                server.getExecutor().remove(thread);
            }

            log.info("Stopped all threads in queue");
        } else {
            log.warn("Server stopped unexpectedly during startup.");
        }
    }
}
