package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Drive")
public class Drive extends Control {
    public Level zHeight = Level.GROUND;
    @Override
    public void init() {
        super.init();
       hraezlyr.cascadeMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
       hraezlyr.cascadeMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


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
        double verticalPower = gamepad2.right_stick_y;
        double noUnwind = hraezlyr.cascadeMotor1.getCurrentPosition();
        //double horizontalPower = gamepad2.left_stick_y;


        double R1 = gamepad1.right_trigger;
        double L1 = gamepad1.left_trigger;

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

        telemetry.addData("cascadeLevelHeight", zHeight);
        telemetry.addData("servoPos", hraezlyr.servoClaw.getPosition());
        telemetry.addData("triggerRight", R1);
        telemetry.addData("triggerLeft", L1);
        telemetry.addData("levelHeight", zHeight);
        telemetry.addData("resetIMU", resetIMU);
        telemetry.addData("dpadRight", dpadRight);
        telemetry.addData("noUnwind", noUnwind);




        telemetry.update();

        //System for cascade level system

        if(dpadRight){
            switch(zHeight) {//it go down if already up
                case GROUND:
                    zHeight = Level.LOW;
                    break;
                case LOW:
                    zHeight = Level.MEDIUM;
                    break;
                case MEDIUM:
                    zHeight = Level.HIGH;
                    break;
            }
            cascadeLift(zHeight, .5);
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
            cascadeLift(zHeight, .5);
        }
            if(noUnwind < 0){
                if(!dpadLeft) {
                    cascadeLiftManual(Math.max(verticalPower, 0));
                }
                else {
                    cascadeLiftManual(-.5);
                    hraezlyr.cascadeMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    hraezlyr.cascadeMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    hraezlyr.cascadeMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    hraezlyr.cascadeMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                }

            }
            else {
                cascadeLiftManual(verticalPower);
            }
           // hraezlyr.horizontalMotor.setPower(-horizontalPower);



        if(gamepad2.a){

            hraezlyr.servoClaw.setPosition(0);

        }

        if(gamepad2.b) {

            hraezlyr.servoClaw.setPosition(.75);
        }
    }



}
