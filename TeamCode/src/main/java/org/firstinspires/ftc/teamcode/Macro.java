package org.firstinspires.ftc.teamcode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Macro {

    private Control control;

    private String name;

    private String mode = "robot";

    private int index = 0;

    private List<Float> x = new ArrayList<>();    // list of x inputs
    private List<Float> y = new ArrayList<>();    // list of y inputs
    private List<Float> turn = new ArrayList<>(); // list of turn inputs

    Macro (Control control,String name) {
        this.control = control;
        this.name = name;
    }

    Macro (Control control, String path,String name){
        this.control = control;
        this.name = name;
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

    public String getMode() {
        return mode;
    }

    public String getName() {
        return name;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void execute () {
        control.runMecanum(x.get(index),y.get(index),turn.get(index),mode);
        index++;
    }

    public void executeLoop () {
        while(index < x.size()){
            execute();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void record(float x, float y, float turn) {
        this.x.add(x);
        this.y.add(y);
        this.turn.add(turn);
    }

    public void save(String path) {
        try {
            OutputStream out = new FileOutputStream("/storage/emulated/0/" + path);
            DataOutputStream dataOut = new DataOutputStream(out);
            for (int i = 0; i < x.size(); i++) {
                dataOut.writeFloat(x.get(i));
                dataOut.writeFloat(y.get(i));
                dataOut.writeFloat(turn.get(i));
            }
            dataOut.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
