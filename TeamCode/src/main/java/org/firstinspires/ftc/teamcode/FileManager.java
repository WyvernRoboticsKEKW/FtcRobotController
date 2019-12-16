package org.firstinspires.ftc.teamcode;

import java.io.File;

public class FileManager {
    public static void delete(String path){
        File file = new File("/storage/emulated/0/" + path);
        file.delete();
    }
}
