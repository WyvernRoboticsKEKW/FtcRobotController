package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="MecanumDrive_Record")
public class MecanumDrive_Record extends LinearOpMode {

    // Defining Robot and Constants
    private Robot robot = new Robot();
    private final double theta = Math.PI / 4;
    private final double trans_factor = 1.0;
    private final double turn_factor = 1.0;
    private boolean record_mode = false;

    private ElapsedTime time = new ElapsedTime();

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

            if (record_mode) {
                // placeholder
            }
        }
    }
}
