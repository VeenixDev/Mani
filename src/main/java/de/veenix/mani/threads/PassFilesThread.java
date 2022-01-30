package de.veenix.mani.threads;

import lombok.extern.log4j.Log4j2;

import java.net.Socket;

@Log4j2
public class PassFilesThread extends ServerThread {
    public PassFilesThread(Socket client) {
        super(client, "Pass Files");
    }

    @Override
    public void run() {
        log.fatal("Thread not implemented yet.");
    }
}
