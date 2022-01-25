package de.veenix.mani;

import lombok.Getter;

public class Server {

    @Getter
    private final int PORT;

    public Server(int port) {
        PORT = port;
    }
}
