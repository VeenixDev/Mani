package de.veenix.mani.threads;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

@Log4j2
public class AcceptThread implements Runnable {

    @Getter @Setter
    private ThreadPoolExecutor executor;

    @Getter @Setter
    private ServerSocket serverSocket;

    @Getter @Setter
    private Class<? extends ServerThread> thread;

    public AcceptThread(Class<? extends ServerThread> thread, ThreadPoolExecutor executor, ServerSocket serverSocket) {
        this.executor = executor;
        this.serverSocket = serverSocket;
        this.thread = thread;
    }

    @Override
    public void run() {
        try {
            // Accepts every request that comes in until the server socket is closed
            while(serverSocket != null && !serverSocket.isClosed() && thread != null) {
                Socket client = serverSocket.accept();
                log.info("Client connected on port " + client.getLocalPort());

                ServerThread sThread = (ServerThread) thread.getConstructors()[0].newInstance(client);

                executor.execute(new Thread(sThread));
            }
            log.info("AcceptThread was stopped because of either the server closing or the thread changed to null");
        } catch (IOException | ClassCastException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
