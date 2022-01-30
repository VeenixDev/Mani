package de.veenix.mani.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.veenix.mani.utils.FileUtils;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.*;

@Log4j2
public class Config {

    @Getter
    private final File configurationFile;

    @Getter
    private final ConfigObject configObj;

    @Getter
    private static final Config instance = new Config();

    private Config() {
        File tempFile = FileUtils.getFile("config.json");
        ConfigObject tempCfgObj = null;

        try {
            if (tempFile == null) {
                log.info("Creating config file with default settings");
                tempFile = new File("config.json");

                // Create default working directory
                File workingDirectory = new File("httpFiles");
                workingDirectory.mkdir();

                // Creating Gson and writing to the config file
                Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
                ConfigObject data = new ConfigObject();
                FileWriter writer = new FileWriter(tempFile);

                gson.toJson(data, writer);
                writer.flush();
                writer.close();
            }

            tempCfgObj = new Gson().fromJson(new FileReader(tempFile), ConfigObject.class);
        } catch (IOException exception) {
            log.error("Couldn't create configuration file!");
        }

        configObj = tempCfgObj;
        configurationFile = tempFile;

        log.info("Loaded Config");
    }

}
