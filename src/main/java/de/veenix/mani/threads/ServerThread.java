package de.veenix.mani.threads;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.net.Socket;

@Log4j2
public abstract class ServerThread implements Runnable {

    @Getter
    private Socket client;


    public ServerThread(Socket client) {
        this.client = client;
        log.info("Created new ServerThread");
    }
}
