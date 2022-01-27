package de.veenix.mani.threads;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.net.Socket;

@Log4j2
public abstract class ServerThread implements Runnable {

    @Getter
    private final Socket client;

    @Getter
    private final String name;

    public ServerThread(Socket client, String name) {
        this.client = client;
        this.name = name;
        log.info("Created new ServerThread(" + name + ")");
    }
}
