package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.lang.annotation.Annotation;

/**
 * Created by Robotics on 9/13/2018.
 */
@TeleOp(name="DriveOldW",group="jv")
public class Drive_Old extends OpMode {

    HornTailConfig robot = new HornTailConfig();


    @Override
    public void init() {

        robot.init(hardwareMap);

    }

    @Override
    public void loop() {

        /*double left = -gamepad1.left_stick_y;
        double right = -gamepad1.right_stick_y;

        robot.left1.setPower(left);
        robot.left2.setPower(left);
        robot.right1.setPower(right);
        robot.right2.setPower(right);*/


        double drive = -gamepad1.left_stick_y-(.5*gamepad1.right_stick_y);
        double turn = gamepad1.left_stick_x+(.5*gamepad1.right_stick_x);
        double left = drive + turn;
        double right = drive - turn;

        robot.setMotors(left, right);

        if(gamepad1.dpad_up){



        }

    }

}
