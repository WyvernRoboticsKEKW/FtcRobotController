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

@TeleOp(name = "Tank_Movement",group = "JV")


public class Tank_Movement extends OpMode {
    RobotConfigW robot = new RobotConfigW();
    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        double left = gamepad1.left_stick_y;
        double right = gamepad1.right_stick_y;

        if (gamepad1.right_bumper) {

            robot.Left.setPower(right / 2);
            robot.Right.setPower(left / 2);

            colora(robot.colorArm);
            arm();
            eArm(robot.Elevator);

        }
        else {
            robot.Left.setPower(right);
            robot.Right.setPower(left);

            colora(robot.colorArm);
            arm();
            eArm(robot.Elevator);
        }
    }




    public void colora(Servo c) {

        if (gamepad2.b) {
            c.setPosition(c.getPosition() + 0.01);
        } else if (gamepad2.a) {
            c.setPosition(c.getPosition() - 0.01);

        }
    }

    public void arm() {

        if (gamepad2.right_bumper) {

            robot.rightArm.setPosition(robot.rightArm.getPosition() + 0.02);
            robot.leftArm.setPosition(robot.leftArm.getPosition() - 0.02);
            //robot.uRightArm.setPower(-0.35);
            //if(robot.uLeftArm.getPosition() >= 0.5)
              //  robot.uLeftArm.setPosition(robot.uLeftArm.getPosition() + 0.02);
            //else
               // robot.uLeftArm.setPosition(0.5);

        } else if (gamepad2.left_bumper) {
            robot.rightArm.setPosition(robot.rightArm.getPosition() - 0.02);
            robot.leftArm.setPosition(robot.leftArm.getPosition() + 0.02);
           // robot.uRightArm.setPower(0.35);
           // if(robot.uLeftArm.getPosition() >= 0.5)
               // robot.uLeftArm.setPosition(robot.uLeftArm.getPosition() - 0.02);
          //  else
               // robot.uLeftArm.setPosition(0.5);

        }


    }

    public void eArm(DcMotor e) {
        //double dir;
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


