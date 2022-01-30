package de.veenix.mani.data;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ConfigObject implements Serializable {

    private int port = 80;
    private ThreadType defaultThread = ThreadType.PASS_FILE;
    private String workingDirectory = "httpFiles/";
}
