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

    // define robot and constants (won't be constants in the future)
    private Argorok argorok = new Argorok();

    private final double theta = Math.PI / 4;
    private final double trans_factor = 1.0;
    private final double turn_factor = 1.0;

    @Override
    public void runOpMode() throws InterruptedException {
        final double LEFTCLAWOPEN    = 0;
        final double LEFTCLAWCLOSED  = 1;

        final double RIGHTCLAWOPEN   = 0;
        final double RIGHTCLAWCLOSED = 1;

        argorok.init(hardwareMap);
        waitForStart();
        while(opModeIsActive()){

            // rotate axes
            double x = Math.abs(Math.cos(theta)) * gamepad1.left_stick_x;
            double y = Math.abs(Math.sin(theta)) * (-gamepad1.left_stick_y);
            double x_output = trans_factor * ((x * Math.cos(theta)) + (y * Math.sin(theta)));
            double y_output = trans_factor * ((x * (-Math.sin(theta))) + (y * Math.cos(theta)));
            // get turn input
            double turn = turn_factor * (gamepad1.right_trigger - gamepad1.left_trigger);
            // apply outputs
            argorok.frontLeft.setPower(x_output + turn);
            argorok.backLeft.setPower(y_output + turn);
            argorok.frontRight.setPower(y_output - turn);
            argorok.backRight.setPower(x_output - turn);
            if(gamepad1.right_bumper){
                argorok.rightClaw.setPosition(RIGHTCLAWOPEN);
                argorok.leftClaw.setPosition(LEFTCLAWOPEN);
            } else if(gamepad1.left_bumper){
                argorok.rightClaw.setPosition(RIGHTCLAWCLOSED);
                argorok.leftClaw.setPosition(LEFTCLAWCLOSED);
            }
        }
    }
}
