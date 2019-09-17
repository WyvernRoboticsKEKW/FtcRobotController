package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Red Square",group="jv")
public class RedSquare extends LinearOpMode {
    DrogonConfig robot = new DrogonConfig();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap,telemetry);
        robot.autoinit();
        waitForStart();
        robot.arm1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.hook.setPosition(0);
        sleep(1001);
        robot.arm1.setPower(-.5);
        robot.arm2.setPower(-.5);
        sleep(500);
        robot.arm1.setPower(0);
        robot.arm1.setPower(0);
        robot.arm1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // robot.initOD();
        // boolean center = robot.detectGold();
        robot.encmotors(575.0, .2, 770.0, .2, -726.0, -.2, -567.0, -.2);
        robot.arm1.setPower(-.2);
        robot.arm2.setPower(-.2);
        sleep(299);
        robot.arm1.setPower(0);
        robot.arm2.setPower(0);
        robot.arm1.setPower(.3);
        robot.arm2.setPower(.3);
        sleep(351);
        robot.arm1.setPower(0);
        robot.arm2.setPower(0);
        sleep(500);
        robot.encmotors(166.0, 0.13562592820719238, 162.0, 0.13562592820719238, -277.0, -0.13562592820719238, -205.0, -0.13562592820719238);
        


        // boolean left = robot.detectGold();

        /*
        * /***CODE STRUCTURE DO NOT DELETE***\
        * \==================================/
        * /=THIS IS A VERY IMPORTANT COMMENT=\
        * \==================================/
        if(center||!left) {
            // angle to center
            if(!center) {
                // angle to right
            }
        }
        // forward movement
        if(center||!left) {
            // angle center to depot
            if(!center) {
                // angle right to depot
            }
        } else {
            // angle left to depot
        }

        // forward movement
        // dropoff
        robot.intake0.setPosition(.44);
        robot.intake.setPower(.3);
        sleep(1000);
        robot.intake.setPower(0);
        if(left){
            // angle left to crater
        }else if(center){
            // angle center to crater
        }else{
            // angle right to crater
        }
        // backward movement
        */
    }
}
