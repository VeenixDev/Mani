package de.veenix.mani.utils;

import lombok.extern.log4j.Log4j2;

import java.util.Date;

@Log4j2
public class Misc {

    public static void sleep(int millis) {
        long start = new Date().getTime();
        try {
            Thread.sleep(millis);
        } catch (InterruptedException exception) {
            log.error("The sleep was interrupted " + (new Date().getTime() - (start + millis)) + "ms before ending");
        }
    }
}
