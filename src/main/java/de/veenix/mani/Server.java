package de.veenix.mani;

import de.veenix.mani.threads.AcceptThread;
import de.veenix.mani.threads.ServerThread;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Log4j2
public class Server {

    @Getter
    private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    @Getter
    private final int PORT;

    @Getter
    private ServerSocket sSocket = null;

    @Getter
    private Thread acceptThread = null;

    public Server(int port, ServerThread serverThread) {
        PORT = port;

        try {
            sSocket = new ServerSocket(PORT);
        } catch (IOException exception) {
            log.fatal("An error occurred while creating a Server");
        }

        if(serverThread != null) {
            AcceptThread thread = new AcceptThread(serverThread.getClass(), executor, sSocket);
            acceptThread = new Thread(thread);
        } else {
            log.error("Couldn't start AcceptThread for the server, serverThread is null");
            try {
                sSocket.close();
            } catch (IOException e) {
                log.fatal("Couldn't stop the server after an error");
            }
        }
    }

    public void close() {
        try {
            acceptThread.interrupt();
            sSocket.close();
        } catch (IOException exception) {
            log.error("Couldn't close server");
        }
    }
}
