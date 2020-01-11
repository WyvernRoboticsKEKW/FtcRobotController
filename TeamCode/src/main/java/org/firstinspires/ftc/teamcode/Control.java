package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

public class Control {

    private Argorok argorok;

    private double theta_adjustment = 0;
    public double trans_factor = 1;
    public double turn_factor = 1;

    private final double LEFTCLAWOPEN    = 0.53;
    private final double LEFTCLAWCLOSED  = 0;

    private final double RIGHTCLAWOPEN   = 0.53;
    private final double RIGHTCLAWCLOSED = 0;

    private final int RETRACTEDPOSITION = -9;
    private final int EXTENDEDPOSITION = -136;

    Control(Argorok argorok){
        this.argorok = argorok;
    }

    public void init(HardwareMap hwmap) {
        argorok.init(hwmap);
        argorok.womp.setTargetPosition(RETRACTEDPOSITION);
        argorok.womp.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        argorok.womp.setPower(0.7);
    }

    public void runMecanum(double x, double y, double turn, String mode) {
        double imuTheta = argorok.imu.getAngularOrientation(AxesReference.INTRINSIC,
                AxesOrder.ZYX,
                AngleUnit.RADIANS).firstAngle + theta_adjustment;
        double theta;
        switch (mode){
            case "auto":
                theta = imuTheta + (3*Math.PI/4);
                break;
            case "field":
                theta = imuTheta - (Math.PI/4);
                break;
            default:
                theta = -Math.PI/4;
        }
        double x_output = trans_factor * ((x * Math.cos(theta)) + (y * Math.sin(theta)));
        double y_output = trans_factor * ((x * (-Math.sin(theta))) + (y * Math.cos(theta)));
        // Get Turn Input
        turn *= turn_factor;
        // Apply Outputs
        argorok.frontLeft.setPower(y_output + turn);
        argorok.backLeft.setPower(x_output + turn);
        argorok.frontRight.setPower(x_output - turn);
        argorok.backRight.setPower(y_output - turn);
    }

    public void runClamp(boolean clamped){
        if(!clamped){
            argorok.rightClaw.setPosition(RIGHTCLAWOPEN);
            argorok.leftClaw.setPosition(LEFTCLAWOPEN);
        } else {
            argorok.rightClaw.setPosition(RIGHTCLAWCLOSED);
            argorok.leftClaw.setPosition(LEFTCLAWCLOSED);
        }
    }

    public void runVWOMP(boolean vwomped){
        argorok.womp.setTargetPosition(vwomped?EXTENDEDPOSITION:RETRACTEDPOSITION);
    }

    public void liftPower(double power){
        argorok.lift.setPower(power);
    }

    public void resetHeading() {
        theta_adjustment = -argorok.imu.getAngularOrientation(
                                    AxesReference.INTRINSIC,
                                    AxesOrder.ZYX,
                                    AngleUnit.RADIANS).firstAngle;
    }

    // VUFORIA CARP

    VuforiaTrackables targetsSkyStone = null;

    VuforiaTrackable stoneTarget = null;

    public void vuforiaInit(){
        argorok.vuforiaInit();
        targetsSkyStone = argorok.vuforia.loadTrackablesFromAsset("Skystone");
        targetsSkyStone.activate();
        stoneTarget = targetsSkyStone.get(0);
    }

    public boolean isStoneVisible() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ((VuforiaTrackableDefaultListener)stoneTarget.getListener()).isVisible();
    }
}
