package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Robotics on 9/21/2017.
 */
@TeleOp(name = "Drive",group = "jv")
public class robotControl extends OpMode{
    robotConfig robot = new robotConfig();
    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {

        double right = -gamepad1.right_stick_y;
        double left = -gamepad1.left_stick_y;

        robot.topRight.setPower(right);
        robot.topLeft.setPower(left);
        robot.bottomRight.setPower(right);
        robot.bottomLeft.setPower(left);
    }
}
