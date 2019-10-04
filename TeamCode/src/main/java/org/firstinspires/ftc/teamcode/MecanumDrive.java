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

    // Defining Robot and Constants
    private Robot robot = new Robot();
    private final double theta = Math.PI / 4;
    private final double trans_factor = 1.0;
    private final double turn_factor = 1.0;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        while(opModeIsActive()){
            // Axis Rotation
            double x = Math.abs(Math.cos(theta)) * gamepad1.left_stick_x;
            double y = Math.abs(Math.sin(theta)) * (-gamepad1.left_stick_y);
            double x_output = trans_factor * ((x * Math.cos(theta)) + (y * Math.sin(theta)));
            double y_output = trans_factor * ((x * (-Math.sin(theta))) + (y * Math.cos(theta)));
            // Get Turn Input
            double turn = turn_factor * (gamepad1.right_trigger - gamepad1.left_trigger);
            // Apply Outputs
            robot.frontLeft.setPower(x_output + turn);
            robot.backLeft.setPower(y_output + turn);
            robot.frontRight.setPower(y_output - turn);
            robot.backRight.setPower(x_output - turn);
        }
    }
}
