package de.veenix.mani.threads;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

@Log4j2
public class Test extends ServerThread {
    public Test(Socket client) {
        super(client, "Test");
    }

    @Override
    public void run() {
        log.info("Hewo");

        try {
            OutputStream writer = getClient().getOutputStream();

            writer.write("HTTP/1.1 200 OK\r\n".getBytes());
            writer.write("Server: Mani/0.1\r\n".getBytes());
            writer.write("Content-Type: text/html, text, plain\r\n".getBytes());
            writer.write(("Content-Length: 76\r\n").getBytes());
            writer.write("\r\n".getBytes());
            writer.write("<html><head><title>Test</title></head><body><span>Money</span></body></html>".getBytes());
        } catch (IOException exception) {
            log.error(exception);
        }
    }
}
