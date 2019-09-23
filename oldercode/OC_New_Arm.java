package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Robotics on 2/16/2018.
 */
@TeleOp(name="New Arm Movement")
public class OC_New_Arm extends OpMode {
    RobotConfigW robot = new RobotConfigW();
    int elevdir = 1;

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        robot.Elevator.setPower(elevdir*gamepad1.left_stick_y*0.3);
        robot.rightArm.setPosition(robot.rightArm.getPosition() + (0.036 * gamepad1.left_stick_x));
        robot.leftArm.setPosition(robot.leftArm.getPosition() - (0.036 * gamepad1.left_stick_x));
        double forward = gamepad1.right_stick_y;
        double turn = gamepad1.right_stick_x;
        double right = forward - turn;
        double left = forward + turn;
        robot.Left.setPower(left);
        robot.Right.setPower(right);
        if(gamepad1.right_bumper){
            robot.Right.setPower(right/2);
            robot.Left.setPower(left/2);
        }
        if(gamepad1.left_bumper){
            robot.colorArm.setPosition(robot.colorArm.getPosition() + 0.01);
        }else if(gamepad1.left_trigger > 0){
            robot.colorArm.setPosition(robot.colorArm.getPosition() - 0.01);
        }
        if (gamepad1.right_trigger > 0){
            if(elevdir == 1)
                elevdir = -1;
            else if(elevdir == -1)
                elevdir = 1;
        }
    }
}
