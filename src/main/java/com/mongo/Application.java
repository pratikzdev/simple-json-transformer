package com.mongo;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Application {

    private static Launcher launcher = new Launcher();
    public static void main(String[] args) throws JsonProcessingException {
        launcher.run(args);
    }

    static void setLauncher(Launcher launcher) {
        Application.launcher = launcher;
    }
}
