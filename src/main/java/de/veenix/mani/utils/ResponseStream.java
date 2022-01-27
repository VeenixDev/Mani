package de.veenix.mani.utils;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * Added write methods to make the code more readable
 */
public class ResponseStream extends BufferedOutputStream {

    public ResponseStream(OutputStream out) {
        super(out);
    }

    public ResponseStream(OutputStream out, int size) {
        super(out, size);
    }

    /**
     * Converts a string to a bytearray and writes it
     *
     * @param string The String you want to write
     * @throws IOException If an I/O error occurs
     */
    public void write(String string) throws IOException {
        this.write(string.getBytes());
    }

    /**
     * Converts a string to a bytearray and writes it
     *
     * @param string The String you want to write
     * @param charset The charset your string is saved in
     * @throws IOException If an I/O error occurs
     */
    public void write(String string, Charset charset) throws IOException {
        this.write(string.getBytes(charset));
    }
}
