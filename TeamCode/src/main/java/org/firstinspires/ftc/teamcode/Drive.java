package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Drive")
public class Drive extends Control {

    @Override
    public void init() {
        super.init();
        // Commented this out for testing, but you don't need this if you are doing this in Hraezlyr anyways
/*
        hraezlyr.cascadeMotor1.setPower(0);
        hraezlyr.cascadeMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hraezlyr.cascadeMotor2.setPower(0);
        hraezlyr.cascadeMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);*/
    }

    @Override
    public void start() {
        super.start();
        hraezlyr.resetIMU();
    }

    @Override
    public void loop() {
        double leftX = gamepad1.left_stick_x;
        double leftY = gamepad1.left_stick_y;
        //double rightX = gamepad1.right_stick_x;
        double rightX = 0;
        double verticalPower = gamepad2.right_stick_y / 1.5;
        //double horizontalPower = gamepad2.left_stick_y;
        Level zHeight = Level.GROUND;

        double R1 = gamepad1.right_trigger / 2;
        double L1 = gamepad1.left_trigger / 2;

        boolean start = gamepad1.start;
        boolean DpadUp = gamepad2.dpad_up;
        boolean DpadDown = gamepad2.dpad_down;
        boolean dpadLeft = gamepad2.dpad_left;
        boolean dpadRight = gamepad2.dpad_right;



        double IMUangle = hraezlyr.getHeading();

        double rightTurn = 0;
        double leftTurn = 0;
        double resetIMU = 0;

        // changes power based on direction of turn to turn robot instead of strafe

        if (start) {
            resetIMU = hraezlyr.resetIMU();
        }

        // angle of controller stick
        if(L1 > 0){
            rightX = -L1;
        }
        if(R1 > 0){
            rightX = R1;
        }
        double angle = Math.toDegrees(Math.atan2(leftY, leftX));
        angle = angle - IMUangle + resetIMU;


        rightX = constrainTuring(rightX);
        // scope orientation

        double power = Math.max(-1, Math.min(1, Math.sqrt((leftX * leftX) + (leftY * leftY))));

        // topLeftPower and bottomRightPower
        double powerGroup1 = ((Math.sin(Math.toRadians(angle))) - (Math.cos(Math.toRadians(angle)))) * power;
        // topRightPower and bottomLeftPower
        double powerGroup2 = ((Math.sin(Math.toRadians(angle))) + (Math.cos(Math.toRadians(angle)))) * power;




        // Power for drivetrain
        hraezlyr.topLeft.setPower(powerGroup1 - rightX);
        hraezlyr.topRight.setPower(powerGroup2 + rightX);
        hraezlyr.bottomLeft.setPower(powerGroup2 - rightX);
        hraezlyr.bottomRight.setPower(powerGroup1 + rightX);

        telemetry.addData("B", gamepad2.b);
        telemetry.addData("A", gamepad2.a);
        telemetry.addData("servoPos", hraezlyr.servoClaw.getPosition());
        telemetry.addData("triggerRight", R1);
        telemetry.addData("triggerLeft", L1);
        telemetry.addData("levelHeight", zHeight);
        telemetry.addData("resetIMU", resetIMU);
        telemetry.addData("dPadright", dpadRight);




        telemetry.update();

        //System for cascade level system

        if(dpadRight){
            if(zHeight == Level.GROUND) zHeight = Level.LOW;
            if(zHeight == Level.LOW) zHeight = Level.MEDIUM;
            zHeight = Level.HIGH;
            cascadeLift(zHeight);
        }
        if(dpadLeft){
            switch(zHeight) {//it go down if already up
                case HIGH:
                    zHeight = Level.MEDIUM;
                    break;
                case MEDIUM:
                    zHeight = Level.LOW;
                    break;
                case LOW:
                    zHeight = Level.GROUND;
                    break;
            }
        }

            cascadeLiftManual(verticalPower);
           // hraezlyr.horizontalMotor.setPower(-horizontalPower);



        if(gamepad2.a){

            hraezlyr.servoClaw.setPosition(0);

        }

        if(gamepad2.b) {

            hraezlyr.servoClaw.setPosition(1);
        }
    }



}
