package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class AutoGuts extends Control {

    ColorDetection colorDetection;

    @Override
    public void loop() {
        requestOpModeStop();
    }

    // TODO: fix ticks per inch to be accurate
    public static final double TICKS_PER_INCH = ((28*19.2) / (4*Math.PI));

    @Override
    public void init() {
        super.init();
        colorDetection = new ColorDetection();
    }

    public void driveEncoder(double x, double y, double power) {
        double imuAngle = -hraezlyr.getHeading();

        // rotates by 45 to line up with mecanum wheels
        imuAngle += 45;

        // calculates given x and y into robots perspective of x and y also converts to ticks
        int x_output = (int) (((x * (Math.cos(Math.toRadians(imuAngle)))) + (y * Math.sin(Math.toRadians(imuAngle)))) * TICKS_PER_INCH);
        int y_output = (int) (((x * (-Math.sin(Math.toRadians(imuAngle)))) + (y * Math.cos(Math.toRadians(imuAngle)))) * TICKS_PER_INCH);

        hraezlyr.setMotorsMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        hraezlyr.topLeft.setTargetPosition(x_output);
        hraezlyr.topRight.setTargetPosition(y_output);
        hraezlyr.bottomLeft.setTargetPosition(y_output);
        hraezlyr.bottomRight.setTargetPosition(x_output);

        hraezlyr.setMotorsMode(DcMotor.RunMode.RUN_TO_POSITION);

        hraezlyr.topLeft.setPower(power);
        hraezlyr.topRight.setPower(power);
        hraezlyr.bottomLeft.setPower(power);
        hraezlyr.bottomRight.setPower(power);
        ElapsedTime elapsedTime = new ElapsedTime();
        while (hraezlyr.topLeft.isBusy() || hraezlyr.topRight.isBusy() || hraezlyr.bottomLeft.isBusy() || hraezlyr.bottomRight.isBusy()) {
            sleep(10);
            if(elapsedTime.seconds() > 3) break;
        }

    }

    public void autoDrive(double power, double angle, double turn) {
        double powerGroup1 = 0;
        double powerGroup2 = 0;

        powerGroup2 = ((-Math.sin(angle)) + (Math.cos(angle))) * power;
        powerGroup1 = ((Math.cos(angle) + Math.sin(angle))) * power;

        hraezlyr.topLeft.setPower(powerGroup1 + turn);
        hraezlyr.topRight.setPower(powerGroup2 - turn);
        hraezlyr.bottomLeft.setPower(powerGroup2 + turn);
        hraezlyr.bottomRight.setPower(powerGroup1 - turn);

    }

    public void turn(double reducePower, double angle) {
        double angularDistance = 0;
        do {
            double initialAngle = hraezlyr.getHeading();

            double turnVal = 1;

            if (angle - initialAngle < 0) turnVal = -1;
            //counter clockwise
            angularDistance = Math.abs(angle - initialAngle);
            if (angularDistance > 180) { // dealing with edge cse
                turnVal *= -1;
                angularDistance =  360 - angularDistance; // calculating shorter angularDistance
            }

            if(turnVal > 1) turnVal = 1;
            if(turnVal < -1) turnVal = -1;
            hraezlyr.topLeft.setPower(-turnVal / reducePower);
            hraezlyr.topRight.setPower(turnVal / reducePower);
            hraezlyr.bottomLeft.setPower(-turnVal / reducePower);
            hraezlyr.bottomRight.setPower(turnVal / reducePower);
            telemetry.addData("angularDistance", angularDistance);
            telemetry.update();
        }
        while (isStopRequested || angularDistance > 10);
    }
    public void returnTo0(double power){
        double currentAngle =  hraezlyr.getHeading();
        double angularDistance = Math.abs(currentAngle);
        do{
           currentAngle =  hraezlyr.getHeading();
           angularDistance = Math.abs(currentAngle);
           if(currentAngle > 0){
               simpleMove(power,-power,-power,power);
           }
           if(currentAngle < 0){
               simpleMove(-power,power,power,-power);
           }
           telemetry.addData("angularDistance",angularDistance);
           telemetry.update();


        }
        while(isStopRequested || angularDistance >= 15);
    }
    public void simpleMove(double FL, double FR, double BR, double BL){
        hraezlyr.topLeft.setPower(FL);
        hraezlyr.topRight.setPower(FR);
        hraezlyr.bottomRight.setPower(BR);
        hraezlyr.bottomLeft.setPower(BL);
    }



    public void closeClaw(boolean clawClose){

        double close = 0;
        if (clawClose) close = .75;
        if (!clawClose) close = 0;

        hraezlyr.servoClaw.setPosition(close);
    }

}
