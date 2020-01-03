package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.lang.reflect.Array;
import java.security.Policy;
import java.util.ArrayList;
import java.util.List;

@Disabled
public class ArgoTransformation {

    private static final double XCONST = 53.66459627329192546586;
    private static final double YCONST = 65.45454545454545454545;

    private static double[] getDeltas(double targetX, double targetY, double globalX, double globalY, double orientation) {
        double deltaX = targetX*Math.cos(orientation) + targetY*Math.sin(orientation) - (globalX*Math.cos(orientation) + globalY*Math.sin(orientation));
        double deltaY = -targetX*Math.sin(orientation) + targetY*Math.cos(orientation) - (-globalX*Math.sin(orientation) + globalY*Math.cos(orientation));

        double[] ret = {deltaX,deltaY};

        return ret;
    }

    public static int[] findMovement(double targetX, double targetY, double globalX, double globalY, double orientation){

        double[] deltas = getDeltas(targetX, targetY, globalX, globalY, orientation);

        int[] xMovement = {(int)(XCONST*deltas[0]),(int)(XCONST*deltas[0]),(int)(XCONST*deltas[0]),(int)(XCONST*deltas[0])};
        int[] yMovement = {(int)(YCONST*deltas[1]),-(int)(YCONST*deltas[1]),-(int)(YCONST*deltas[1]),(int)(YCONST*deltas[1])};
        int[] ret = {0,0,0,0};
        for(int i = 0; i < 4; i++){
            ret[i] = xMovement[i] + yMovement[i];
        }
        return ret;
    }

    public static void runMovement(Argorok argorok, int[] encoders, double power) {
        argorok.frontLeft.setTargetPosition(encoders[0]+argorok.frontLeft.getCurrentPosition());
        argorok.frontRight.setTargetPosition(encoders[1]+argorok.frontRight.getCurrentPosition());
        argorok.backLeft.setTargetPosition(encoders[2]+argorok.backLeft.getCurrentPosition());
        argorok.backRight.setTargetPosition(encoders[3]+argorok.backRight.getCurrentPosition());

        argorok.frontLeft.setPower(power);
        argorok.frontRight.setPower(power);
        argorok.backLeft.setPower(power);
        argorok.backRight.setPower(power);

        argorok.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        argorok.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        argorok.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        argorok.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(argorok.frontLeft.isBusy()||argorok.frontRight.isBusy()||argorok.backLeft.isBusy()||argorok.backRight.isBusy()){
               try{Thread.sleep(5);}catch(Exception e){e.printStackTrace();}
        }

        argorok.frontLeft.setPower(0);
        argorok.frontRight.setPower(0);
        argorok.backLeft.setPower(0);
        argorok.backRight.setPower(0);

        argorok.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        argorok.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        argorok.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        argorok.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(argorok.frontLeft.isBusy()&&argorok.frontRight.isBusy()&&argorok.backLeft.isBusy()&&argorok.backRight.isBusy()){
            try{Thread.sleep(5);}catch(Exception e){e.printStackTrace();}
        }

        argorok.frontLeft.setPower(0);
        argorok.frontRight.setPower(0);
        argorok.backLeft.setPower(0);
        argorok.backRight.setPower(0);


        argorok.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        argorok.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        argorok.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        argorok.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public static void goTo(Argorok argorok,double power, double targetX, double targetY, double globalX, double globalY, double orientation){
        runMovement(argorok,findMovement(targetX,targetY,globalX,globalY,orientation),power);
    }

}
