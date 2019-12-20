package org.firstinspires.ftc.teamcode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Macro {

    List<Double> frontLeft = new ArrayList<>();
    List<Double> frontRight = new ArrayList<>();
    List<Double> backLeft = new ArrayList<>();
    List<Double> backRight = new ArrayList<>();

    List<Double> lift = new ArrayList<>();

    List<Double> leftClaw = new ArrayList<>();
    List<Double> rightClaw = new ArrayList<>();

    int index = 0;

    public static Macro readMacro(String path){
        Macro macro = new Macro();
        try {
            FileInputStream fileInputStream = new FileInputStream("/storage/emulated/0/" + path);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            while(dataInputStream.available() > 0){
                macro.frontLeft.add(dataInputStream.readDouble());
                macro.frontRight.add(dataInputStream.readDouble());
                macro.backLeft.add(dataInputStream.readDouble());
                macro.backRight.add(dataInputStream.readDouble());
                macro.lift.add(dataInputStream.readDouble());
                macro.leftClaw.add(dataInputStream.readDouble());
                macro.rightClaw.add(dataInputStream.readDouble());
            }
            dataInputStream.close();
            fileInputStream.close();
        } catch(IOException e){
            e.printStackTrace();
        }
        return macro;
    }

    public void read(String path){
        try {
            FileInputStream fileInputStream = new FileInputStream("/storage/emulated/0/" + path);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            while(dataInputStream.available() > 0){
                frontLeft.add(dataInputStream.readDouble());
                frontRight.add(dataInputStream.readDouble());
                backLeft.add(dataInputStream.readDouble());
                backRight.add(dataInputStream.readDouble());
                lift.add(dataInputStream.readDouble());
                leftClaw.add(dataInputStream.readDouble());
                rightClaw.add(dataInputStream.readDouble());
            }
            dataInputStream.close();
            fileInputStream.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void write(Macro macro, String path){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("/storage/emulated/0/"+path);
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            for(int i = 0; i < frontLeft.size(); i++){
                dataOutputStream.writeDouble(macro.frontLeft.get(i));
                dataOutputStream.writeDouble(macro.frontRight.get(i));
                dataOutputStream.writeDouble(macro.backLeft.get(i));
                dataOutputStream.writeDouble(macro.backRight.get(i));
                dataOutputStream.writeDouble(macro.lift.get(i));
                dataOutputStream.writeDouble(macro.leftClaw.get(i));
                dataOutputStream.writeDouble(macro.rightClaw.get(i));
            }
            dataOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void execute(Argorok argorok,int delay){
        int size = frontLeft.size();
        argorok.frontRight.setPower(frontRight.get(index));
        argorok.frontLeft.setPower(frontLeft.get(index));
        argorok.backRight.setPower(backRight.get(index));
        argorok.backLeft.setPower(backLeft.get(index));
        argorok.lift.setPower(lift.get(index));
        argorok.leftClaw.setPosition(leftClaw.get(index));
        argorok.rightClaw.setPosition(rightClaw.get(index));
        try{
            Thread.sleep(delay);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void executeLoop(Argorok argorok, int[] delays){
        while(index<frontRight.size()){
            execute(argorok,delays[index % delays.length]);
            index++;
        }
        index = 0;
        argorok.frontRight.setPower(0);
        argorok.frontLeft.setPower(0);
        argorok.backRight.setPower(0);
        argorok.backLeft.setPower(0);
        argorok.lift.setPower(0);
    }

    public void record(Argorok argorok){
        frontLeft.add(argorok.frontLeft.getPower());
        frontRight.add(argorok.frontRight.getPower());
        backLeft.add(argorok.backLeft.getPower());
        backRight.add(argorok.backRight.getPower());
        lift.add(argorok.lift.getPower());
        leftClaw.add(argorok.leftClaw.getPosition());
        rightClaw.add(argorok.rightClaw.getPosition());
    }
}