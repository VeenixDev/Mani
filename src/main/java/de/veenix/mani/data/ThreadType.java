package de.veenix.mani.data;

import de.veenix.mani.threads.PassFilesThread;
import de.veenix.mani.threads.ServerThread;
import lombok.Getter;

@Getter
public enum ThreadType {
    PASS_FILE("Pass File", PassFilesThread.class);

    private final String name;
    private final Class<? extends ServerThread> thread;

    ThreadType(String name, Class<? extends ServerThread> thread) {
        this.name = name;
        this.thread = thread;
    }
}
