package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

public class Control {

    private Argorok argorok;

    private double theta_adjustment = 0;
    public double trans_factor = 1;
    public double turn_factor = 1;

    private final double LEFTCLAWOPEN    = 0.53;
    private final double LEFTCLAWCLOSED  = 0;

    private final double RIGHTCLAWOPEN   = 0.53;
    private final double RIGHTCLAWCLOSED = 0;

    Control(Argorok argorok){
        this.argorok = argorok;
    }

    public void init(HardwareMap hwmap) {
        argorok.init(hwmap);
        argorok.womp.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        argorok.womp.setPower(0);
        argorok.womp.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void runMecanum(double x, double y, double turn, String mode) {
        double imuTheta = argorok.getHeading();
        double theta;
        switch (mode){
            case "auto":
                theta = imuTheta + Math.PI/2 + theta_adjustment;
                break;
            case "field":
                theta = imuTheta - (Math.PI/2) + theta_adjustment;
                break;
            default:
                theta = -Math.PI/2;
        }
        double x_output = ((x * Math.cos(theta)) + (y * Math.sin(theta)));
        double y_output = ((x * (-Math.sin(theta))) + (y * Math.cos(theta)));
        double right_vector = trans_factor * (x_output * 0.928 + y_output * 0.590);
        double left_vector = trans_factor * (x_output * -0.928 + y_output * 0.590);

        // Get Turn Input
        turn *= turn_factor;
        // Apply Outputs
        argorok.frontLeft.setPower(left_vector + turn);
        argorok.backLeft.setPower(right_vector + turn);
        argorok.frontRight.setPower(right_vector - turn);
        argorok.backRight.setPower(left_vector - turn);
    }

    public void runMecanumOfTheAncientGods(double x, double y, double turn, String mode){
        double imuTheta = argorok.getHeading();
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

    public void autoRunMecanumOfTheAncients(double x, double y, double turn, int delay) throws InterruptedException{
        runMecanum(x,y,turn,"auto");
        Thread.sleep(delay);
        argorok.frontLeft.setPower(0);
        argorok.backLeft.setPower(0);
        argorok.frontRight.setPower(0);
        argorok.backRight.setPower(0);
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

    public void runVWOMP(double power){
        argorok.womp.setPower(power);
    }

    public void liftPower(double power){
        argorok.lift.setPower(power);
    }

    public void resetHeading() {
        theta_adjustment = -argorok.getHeading();
    }

    public void runFlüp(boolean up){
        if(up){
            argorok.leftFlüp.setPosition(0);
            argorok.rightFlüp.setPosition(1);
        } else {
            argorok.leftFlüp.setPosition(1);
            argorok.rightFlüp.setPosition(0);
        }
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

    public void tfodInit(){
        argorok.TFODInit();
        argorok.tfod.activate();
    }
    public boolean isStoneVisible() {
        return ((VuforiaTrackableDefaultListener)stoneTarget.getListener()).isVisible();
    }
    public void autoRunMecanum(double x, double y, double turn, int delay) throws InterruptedException{
        runMecanum(x,y,turn,"field");
        Thread.sleep(delay);
        argorok.frontLeft.setPower(0);
        argorok.backLeft.setPower(0);
        argorok.frontRight.setPower(0);
        argorok.backRight.setPower(0);
    }

    public void autoLift(double power,int delay) throws InterruptedException{
        argorok.lift.setPower(power);
        Thread.sleep(delay);
        argorok.lift.setPower(0);
    }
    public void autoCloseClamp(){
        runClamp(true);
    }
    public void autoOpenClamp(){
        runClamp(false);
    }
    public void setAllZero(){
        argorok.womp.setPower(0);
        argorok.rightClaw.setPosition(RIGHTCLAWOPEN);
        argorok.leftClaw.setPosition(LEFTCLAWOPEN);
        argorok.lift.setPower(0);
        argorok.frontLeft.setPower(0);
        argorok.backLeft.setPower(0);
        argorok.frontRight.setPower(0);
        argorok.backRight.setPower(0);
        argorok.rightFlüp.setPosition(1);
        argorok.leftFlüp.setPosition(0);
    }
    public void turn(double angle, double power) {
        double angleConstant = 0.7;
        power = Math.abs(power);
        double initialAng = argorok.getHeading();
        runMecanum(0,0,-(angle - initialAng)*power/Math.abs(angle - initialAng),"field");
        double currentAng = initialAng;
        while(Math.abs(((angle - currentAng)%(Math.PI*2)+Math.PI*2)%(Math.PI*2)) > (angleConstant*power)){
            currentAng = argorok.getHeading();
        }
        setAllZero();
    }
    public void moveToStone(){
        //TODO Move Until Touch Sensor Says Stop
    }
    public void switchCamera(boolean tfodBool){
        CameraName cameraName = argorok.vuforia.getCameraName();
        if(cameraName == argorok.hwmap.get(WebcamName.class, "Webcam 1")){
            argorok.vuforiaParameters.cameraName = null;
        }else{
            argorok.vuforiaParameters.cameraName = argorok.hwmap.get(WebcamName.class, "Webcam 1");
        }
        argorok.vuforia = ClassFactory.getInstance().createVuforia(argorok.vuforiaParameters);
        if(tfodBool){
            int tfodMonitorViewId = argorok.hwmap.appContext.getResources().getIdentifier("tfodMonitorViewId", "id", argorok.hwmap.appContext.getPackageName());
            TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
            tfodParameters.minimumConfidence = 0.8;
            argorok.tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, argorok.vuforia);
            argorok.tfod.loadModelFromAsset(argorok.TFOD_MODEL_ASSET, argorok.LABEL_FIRST_ELEMENT, argorok.LABEL_SECOND_ELEMENT);
        }
    }
    public double getAcceleration(){
        Acceleration acc = argorok.imu.getAcceleration();
        double xacc = acc.xAccel;
        double yacc = acc.yAccel;
        return Math.sqrt((xacc * xacc) + (yacc * yacc));
    }

    public boolean accSpikeY(double threshold) throws InterruptedException{
            return argorok.imu.getAcceleration().yAccel >= threshold;
    }

    public boolean accSpikeX(double threshold) throws InterruptedException{
        return argorok.imu.getAcceleration().xAccel >= threshold;
    }

    public void encoder(int x, int y, double turn, double power) throws InterruptedException{
        int frontLeft = argorok.frontLeft.getCurrentPosition();
        int frontRight = argorok.frontRight.getCurrentPosition();
        int backLeft = argorok.backLeft.getCurrentPosition();
        int backRight = argorok.backRight.getCurrentPosition();
        double imuTheta = argorok.getHeading();
        double theta = imuTheta - (Math.PI/2);
        double x_output = ((x * Math.cos(theta)) + (y * Math.sin(theta)));
        double y_output = ((x * (-Math.sin(theta))) + (y * Math.cos(theta)));
        double right_vector = (x_output * 0.928 + y_output * 0.590);
        double left_vector = (x_output * -0.928 + y_output * 0.590);
        frontLeft += left_vector;
        backRight += left_vector;
        frontRight += right_vector;
        backLeft += right_vector;
        argorok.frontLeft.setTargetPosition(frontLeft);
        argorok.frontRight.setTargetPosition(frontRight);
        argorok.backLeft.setTargetPosition(backLeft);
        argorok.backRight.setTargetPosition(backRight);
        argorok.setMotorsMode(DcMotor.RunMode.RUN_TO_POSITION);

        double x_outputp = ((x * Math.cos(theta)) + (y * Math.sin(theta)));
        double y_outputp = ((x * (-Math.sin(theta))) + (y * Math.cos(theta)));
        double right_vectorp = (x_outputp * 0.928 + y_outputp * 0.590);
        double left_vectorp = (x_outputp * -0.928 + y_outputp * 0.590);
        argorok.frontLeft.setPower(left_vectorp);
        argorok.frontRight.setPower(right_vectorp);
        argorok.backLeft.setPower(right_vectorp);
        argorok.backRight.setPower(left_vectorp
        );

        while(argorok.backRight.isBusy() || argorok.backLeft.isBusy() || argorok.frontRight.isBusy() || argorok.frontLeft.isBusy()){
            Thread.sleep(10);
        }

        argorok.setMotorsMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        runMecanum(0,0,0,"robot");

        turn(turn,power);
    }

    public int voltAdjust(double milliIn, double power) throws InterruptedException{
        Thread.sleep(50);
        int milli = (int)(1.25*1000*milliIn/(argorok.vs.getVoltage()/*\*power*/)/2.5)/37;
//        int milliInt = (int)(milli - (376.5*argorok.vs.getVoltage())+4923.6);

        return milli;
    }

    public void voltageRunMecanum(double x, double y, double turn, double inch) throws InterruptedException{
        autoRunMecanum(x,y,turn,voltAdjust(inch,Math.sqrt((x*x)+(y*y))));
    }
}