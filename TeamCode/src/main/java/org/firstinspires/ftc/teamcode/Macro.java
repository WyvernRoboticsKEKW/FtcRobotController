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

    private List<Float> x      = new ArrayList<>();
    private List<Float> y      = new ArrayList<>();
    private List<Float> turn   = new ArrayList<>();
    private List<Float> lift   = new ArrayList<>();
    private List<Boolean> claw = new ArrayList<>();

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
                lift.add(inputStream.readFloat());
                claw.add(inputStream.readBoolean());
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

    public void reset () {
        index = 0;
    }

    public void execute () {
        control.runMecanum(x.get(index),y.get(index),turn.get(index),mode);
        control.runClamp(claw.get(index));
        control.liftPower(lift.get(index));
        index++;
    }

    public void executeReverse () {
        int length = x.size();
        index--;
        control.runMecanum(-x.get(length - index),-y.get(length - index),-turn.get(length - index),mode);
        control.runClamp(claw.get(length - index));
        control.liftPower(-lift.get(length - index));
        index+=2;
    }

    public boolean isFinished () {
        return index >= x.size();
    }

    public void executeLoop () {
        while(index < x.size()){
            execute();
            try {
                Thread.sleep(27);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void executeReverseLoop() {
        while(index < x.size()){
            executeReverse();
            try {
                Thread.sleep(27);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void record(float x, float y, float turn, float lift, boolean claw) {
        this.x.add(x);
        this.y.add(y);
        this.turn.add(turn);
        this.lift.add(lift);
        this.claw.add(claw);
    }

    public void save(String path) {
        try {
            OutputStream out = new FileOutputStream("/storage/emulated/0/" + path);
            DataOutputStream dataOut = new DataOutputStream(out);
            for (int i = 0; i < x.size(); i++) {
                dataOut.writeFloat(x.get(i));
                dataOut.writeFloat(y.get(i));
                dataOut.writeFloat(turn.get(i));
                dataOut.writeFloat(lift.get(i));
                dataOut.writeBoolean(claw.get(i));
            }
            dataOut.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
