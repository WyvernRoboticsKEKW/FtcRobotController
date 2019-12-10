package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

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

    List<Integer> frontLeftPosition = new ArrayList<>();
    List<Integer> frontRightPosition = new ArrayList<>();
    List<Integer> backLeftPosition = new ArrayList<>();
    List<Integer> backRightPosition = new ArrayList<>();

    List<Double> lift = new ArrayList<>();
    List<Integer> liftPosition = new ArrayList<>();

    List<Double> leftClaw = new ArrayList<>();
    List<Double> rightClaw = new ArrayList<>();

    int index = 0;

    public Macro read(String path){
        Macro macro = new Macro();
        try {
            FileInputStream fileInputStream = new FileInputStream("/storage/emulated/0/" + path);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            while(dataInputStream.available() > 0){
                macro.frontLeft.add(dataInputStream.readDouble());
                macro.frontLeftPosition.add(dataInputStream.readInt());
                macro.frontRight.add(dataInputStream.readDouble());
                macro.frontRightPosition.add(dataInputStream.readInt());
                macro.backLeft.add(dataInputStream.readDouble());
                macro.backLeftPosition.add(dataInputStream.readInt());
                macro.backRight.add(dataInputStream.readDouble());
                macro.backRightPosition.add(dataInputStream.readInt());
                macro.lift.add(dataInputStream.readDouble());
                macro.liftPosition.add(dataInputStream.readInt());
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

    public void write(Macro macro, String path){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("/storage/emulated/0/"+path);
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            for(int i = 0; i < frontLeft.size(); i++){
                dataOutputStream.writeDouble(macro.frontLeft.get(i));
                dataOutputStream.writeInt(macro.frontLeftPosition.get(i));
                dataOutputStream.writeDouble(macro.frontRight.get(i));
                dataOutputStream.writeInt(macro.frontRightPosition.get(i));
                dataOutputStream.writeDouble(macro.backLeft.get(i));
                dataOutputStream.writeInt(macro.backLeftPosition.get(i));
                dataOutputStream.writeDouble(macro.backRight.get(i));
                dataOutputStream.writeInt(macro.backRightPosition.get(i));
                dataOutputStream.writeDouble(macro.lift.get(i));
                dataOutputStream.writeInt(macro.liftPosition.get(i));
                dataOutputStream.writeDouble(macro.leftClaw.get(i));
                dataOutputStream.writeDouble(macro.rightClaw.get(i));
            }
            dataOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void init(Argorok argorok){
        argorok.frontRight.setTargetPosition(frontLeftPosition.get(0));
        argorok.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        argorok.frontLeft.setTargetPosition(frontLeftPosition.get(0));
        argorok.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        argorok.backRight.setTargetPosition(frontLeftPosition.get(0));
        argorok.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        argorok.backLeft.setTargetPosition(frontLeftPosition.get(0));
        argorok.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        argorok.lift.setTargetPosition(frontLeftPosition.get(0));
        argorok.lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void execute(Argorok argorok,int delay){
        argorok.frontRight.setTargetPosition(frontRightPosition.get(index));
        argorok.frontRight.setPower(frontRight.get(index));
        argorok.frontLeft.setTargetPosition(frontLeftPosition.get(index));
        argorok.frontLeft.setPower(frontLeft.get(index));
        argorok.backRight.setTargetPosition(backRightPosition.get(index));
        argorok.backRight.setPower(backRight.get(index));
        argorok.backLeft.setTargetPosition(backLeftPosition.get(index));
        argorok.backLeft.setPower(backLeft.get(index));
        argorok.leftClaw.setPosition(leftClaw.get(index));
        argorok.rightClaw.setPosition(rightClaw.get(index));
        try{
            Thread.sleep(delay);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void executeLoop(Argorok argorok, int delay){
        while(index<frontRight.size()){
            execute(argorok,delay);
            index++;
        }
        index = 0;
    }

    public void record(Argorok argorok){
        frontLeft.add(argorok.frontLeft.getPower());
        frontLeftPosition.add(argorok.frontLeft.getCurrentPosition());
        frontRight.add(argorok.frontRight.getPower());
        frontRightPosition.add(argorok.frontRight.getCurrentPosition());
        backLeft.add(argorok.backLeft.getPower());
        backLeftPosition.add(argorok.backLeft.getCurrentPosition());
        backRight.add(argorok.backRight.getPower());
        backRightPosition.add(argorok.backRight.getCurrentPosition());
        lift.add(argorok.lift.getPower());
        liftPosition.add(argorok.lift.getCurrentPosition());
        leftClaw.add(argorok.leftClaw.getPosition());
        rightClaw.add(argorok.rightClaw.getPosition());
    }
}