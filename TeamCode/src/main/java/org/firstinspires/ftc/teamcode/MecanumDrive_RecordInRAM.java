package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;


@TeleOp(name="Mecanum Drive RAM Record")

public class MecanumDrive_RecordInRAM extends LinearOpMode {

    private Robot robot = new Robot();
    private final double theta = Math.PI / 4;
    private final double trans_factor = 1.0;
    private final double turn_factor = 1.0;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();

        double x_output;
        double y_output;
        double turn;

        Gamepad.GamepadCallback callback = new Gamepad.GamepadCallback() {
            double x;
            double y;

            @Override
            public void gamepadChanged(Gamepad gamepad) {

                x = Math.abs(Math.cos(theta)) * gamepad1.left_stick_x;
                y = Math.abs(Math.sin(theta)) * (-gamepad1.left_stick_y);

            }
        };

        while(opModeIsActive()){
            // Axis Rotation
            double x = callback.x;
            double y = callback.y;
            x_output = trans_factor * ((x * Math.cos(theta)) + (y * Math.sin(theta)));
            y_output = trans_factor * ((x * (-Math.sin(theta))) + (y * Math.cos(theta)));
            // Get Turn Input
            turn = turn_factor * (gamepad1.right_trigger - gamepad1.left_trigger);
            // Apply Outputs
            robot.frontLeft.setPower(x_output + turn);
            robot.backLeft.setPower(y_output + turn);
            robot.frontRight.setPower(y_output - turn);
            robot.backRight.setPower(x_output - turn);
        }
    }
}
