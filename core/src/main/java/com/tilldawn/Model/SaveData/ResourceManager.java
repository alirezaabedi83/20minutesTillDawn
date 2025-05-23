package com.tilldawn.Model.SaveData;

import java.io.*;

public class ResourceManager {
    private static final String SAVE_PATH = "/home/alireza/untilDawn/core/src/main/java/com/tilldawn/Model/SaveData/users.dat";

    public static void save(Object data) throws IOException {
        new File(SAVE_PATH).getParentFile().mkdirs();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_PATH))) {
            out.writeObject(data);
        }
    }

    public static Object load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE_PATH))) {
            return in.readObject();
        }
    }
}
