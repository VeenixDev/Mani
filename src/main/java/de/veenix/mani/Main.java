package de.veenix.mani;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {

    public static void main(String[] args) {
        log.info("Starting Server");
        Server server = new Server(80, null);
        if(server.getSSocket() != null && !server.getSSocket().isClosed()) {
            log.info("Server started successfully and listens on port " + server.getPORT());
        } else {
            log.warn("Server stopped unexpectedly during startup.");
        }
    }
}
