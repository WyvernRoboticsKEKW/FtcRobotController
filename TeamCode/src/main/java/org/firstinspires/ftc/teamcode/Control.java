package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class Control extends OpMode {
    public static final int TICKS_PER_INCH = (int)(537.6 * (2*Math.PI * 2));
    public boolean isStopRequested = false;
    Hraezlyr hraezlyr;
    enum Level {
        GROUND,
        LOW,
        MEDIUM,
        HIGH,
    }

    @Override
    public void init() {
        hraezlyr = new Hraezlyr(hardwareMap);
    }
    @Override
    public void stop(){
        if(isStopRequested) return;

        isStopRequested = true;

    }
    //538 ticks per rotation
    public void cascadeLift(Level zHeight, double power){
        switch(zHeight){
            case GROUND:
                hraezlyr.cascadeMotor1.setTargetPosition(100);
                hraezlyr.cascadeMotor2.setTargetPosition(100);
                // all target positions are not accurate to the motor
                break;
            case LOW:
                hraezlyr.cascadeMotor1.setTargetPosition(3500);
                hraezlyr.cascadeMotor2.setTargetPosition(3500);
                break;
            case MEDIUM:
                hraezlyr.cascadeMotor1.setTargetPosition(200);
                hraezlyr.cascadeMotor2.setTargetPosition(200);
                break;
            case HIGH:
                hraezlyr.cascadeMotor1.setTargetPosition(300);
                hraezlyr.cascadeMotor2.setTargetPosition(300);
                break;
        }
        hraezlyr.setCascadeMotorsMode(DcMotor.RunMode.RUN_TO_POSITION);
        hraezlyr.cascadeMotor1.setPower(power);
        hraezlyr.cascadeMotor2.setPower(power);
    }
    public void cascadeLiftManual(double power){

        hraezlyr.setCascadeMotorsMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        hraezlyr.cascadeMotor1.setPower(power);
        hraezlyr.cascadeMotor2.setPower(power);

    }
    public void sleep (long mill){
        try {
            Thread.sleep(mill);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
    public double constrainAngle (double angle){

        return((angle % 360 + 360) % 360);
    }
    public double constrainTuring (double angle) {
        if(angle > .5){
            angle = .5;
        }
        return(angle);
    }
}



