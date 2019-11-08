package org.firstinspires.ftc.teamcode;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Macro {

    private Control control;

    private List<Float> x = new ArrayList<>();    // list of x inputs
    private List<Float> y = new ArrayList<>();    // list of y inputs
    private List<Float> turn = new ArrayList<>(); // list of turn inputs

    Macro (Control control) {
        this.control = control;
    }

    Macro (Control control, String path){
        try {
            DataInputStream inputStream = new DataInputStream(new FileInputStream("/storage/emulated/0/" + path));
            while (inputStream.available() > 0) {
                x.add(inputStream.readFloat());
                y.add(inputStream.readFloat());
                turn.add(inputStream.readFloat());
            }
            inputStream.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void execute ()

}
