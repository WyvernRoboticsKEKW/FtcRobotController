package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class AutoGuts extends Control {

    ColorDetection colorDetection;

    @Override
    public void loop() {
        requestOpModeStop();
    }

    // TODO: fix ticks per inch to be accurate
    public static final double TICKS_PER_INCH = 200;

    @Override
    public void init() {
        super.init();
        colorDetection = new ColorDetection();
    }

    public void driveEncoder(double x, double y, double power) {


        int topLeftPos = hraezlyr.topLeft.getCurrentPosition();
        int topRightPos = hraezlyr.topRight.getCurrentPosition();
        int bottomLeftPos = hraezlyr.bottomLeft.getCurrentPosition();
        int bottomRightPos = hraezlyr.bottomRight.getCurrentPosition();

        double imuAngle = hraezlyr.getHeading();

        // rotates by 45 to line up with mecanum wheels


        // calculates given x and y into robots perspective of x and y also converts to ticks
        int x_output = (int) (((x * (Math.cos(Math.toRadians(imuAngle)))) + (y * Math.sin(Math.toRadians(imuAngle)))) * TICKS_PER_INCH);
        int y_output = (int) (((x * (-Math.sin(Math.toRadians(imuAngle)))) + (y * Math.cos(Math.toRadians(imuAngle)))) * TICKS_PER_INCH);


        topLeftPos += x_output;
        bottomRightPos += x_output;
        topRightPos += y_output;
        bottomLeftPos += y_output;

        hraezlyr.topLeft.setTargetPosition(topLeftPos);
        hraezlyr.topRight.setTargetPosition(topRightPos);
        hraezlyr.bottomLeft.setTargetPosition(bottomLeftPos);
        hraezlyr.bottomRight.setTargetPosition(bottomRightPos);

        hraezlyr.setMotorsMode(DcMotor.RunMode.RUN_TO_POSITION);

        hraezlyr.topLeft.setPower(power);
        hraezlyr.topRight.setPower(power);
        hraezlyr.bottomLeft.setPower(power);
        hraezlyr.bottomRight.setPower(power);




        while (hraezlyr.topLeft.isBusy() || hraezlyr.topRight.isBusy() || hraezlyr.bottomLeft.isBusy() || hraezlyr.bottomRight.isBusy()) {
            sleep(10);
           hraezlyr.setMotorsMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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

    public void turn(double power, double angle) {
        double angularDistance = 0;
        do {
            double initialAngle = hraezlyr.getHeading();
            angle = constrainAngle(angle);
            double turnVal = 1;

            if (angle - initialAngle < 0) turnVal = -1;
            //counter clockwise
            angularDistance = Math.abs(angle - initialAngle);
            if (angularDistance > 180) { // dealing with edge cse
                turnVal *= -1;
                angularDistance =  360 - angularDistance; // calculating shorter angularDistance
            }
            turnVal = turnVal * angularDistance / .35;
            hraezlyr.topLeft.setPower(turnVal);
            hraezlyr.topRight.setPower(-turnVal);
            hraezlyr.bottomLeft.setPower(turnVal);
            hraezlyr.bottomRight.setPower(-turnVal);
        }
        while (!isStopRequested && angularDistance > 0.08);
    }



    public void closeClaw(boolean clawClose){

        double close = 0;
        if (clawClose) close = 1;
        if (!clawClose) close = 0;

        hraezlyr.servoClaw.setPosition(close);
    }

}
