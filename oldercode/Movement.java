package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.Random;

/**
 * Created by Robotics on 11/17/2017.
 */

@TeleOp(name = "Movement",group = "JV")


public class Movement extends OpMode {
    RobotConfigW robot = new RobotConfigW();
    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        //Right Drive is faster
        double forward = gamepad1.right_stick_y;
        double turn = gamepad1.right_stick_x;
        //Left drive is slower
        double forward2 = gamepad1.left_stick_y /2;
        double turn2 = gamepad1.left_stick_x / 2;
        //Right
        double right = forward - turn;
        double left = forward + turn;
        //Left
        double right2 = forward2 - turn2;
        double left2 = forward2 + turn2;

        robot.Left.setPower(left);
        robot.Right.setPower(right);
        robot.Left.setPower(left2);
        robot.Right.setPower(right2);

        colora(robot.colorArm);
        arm(robot.leftArm, robot.rightArm);
        eArm(robot.Elevator);

    }



    public void colora(Servo c) {

        if (gamepad2.b) {
            c.setPosition(c.getPosition() + 0.01);
        } else if (gamepad2.a) {
            c.setPosition(c.getPosition() - 0.01);

        }
    }

    public void arm(Servo ls, Servo rs) {

        if (gamepad2.right_bumper) {

            rs.setPosition(rs.getPosition() + 0.02);
            ls.setPosition(ls.getPosition() - 0.02);

        } else if (gamepad2.left_bumper) {
            ls.setPosition(ls.getPosition() + 0.02);
            rs.setPosition(rs.getPosition() - 0.02);

        }


    }

    public void eArm(DcMotor e) {
        double dir;
        float rt = gamepad2.left_trigger;
        float lt = gamepad2.right_trigger;
        if ((rt > 0) && (lt > 0)){
            e.setPower(0);
        }
        else if (rt > 0) {
            e.setPower(0.65);
        } else if (lt > 0){
            e.setPower(-0.65);
        }
        else
            e.setPower(0);

    }



}


