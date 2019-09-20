package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/*
 * This program is taking advantage of the fact that the
 * mecanum wheels can be translated into vectors angled
 * 45 degrees (pi over 4 radians would be more correct)
 * This means that we can simply use axis rotation math
 * to translate the input axis to the output axis, where
 * the input axis refers to the gamepad input, and the
 * output axis represents the powers of the respective
 * mecanum wheels.
 *
 * -- Samuel Heidenreich
 *
 */

@TeleOp(name="Mecanum Drive")
public class MecanumDrive extends LinearOpMode {

    private Robot robot = new Robot();
    private double theta = Math.PI / 4;
    private double trans_factor = 1.0;
    private double turn_factor = 1.0;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        while(opModeIsActive()){

            double x = Math.cos(theta) * gamepad1.left_stick_x;
            double y = Math.sin(theta) * (-gamepad1.left_stick_y);

            double x_output = trans_factor * ((x * Math.cos(theta)) + (y * Math.sin(theta)));
            double y_output = trans_factor * ((x * (-Math.sin(theta))) + (y * Math.cos(theta)));

            double turn = turn_factor * gamepad1.right_stick_x;

            robot.frontLeft.setPower(y_output + turn);
            robot.backLeft.setPower(x_output + turn);
            robot.frontRight.setPower(x_output - turn);
            robot.backRight.setPower(y_output - turn);

        }
    }
}
