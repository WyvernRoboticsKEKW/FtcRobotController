package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name ="Driver control period")
public class Drive extends Drivetrain {
    @Override
    public void runOpMode() throws InterruptedException {
        initialization();
        waitForStart();
        while (opModeIsActive()) {
            setDrivePower(-gamepad1.left_stick_y, -gamepad1.right_stick_y);

            setLift((gamepad2.right_trigger-gamepad2.left_trigger)*0.7);

            if (gamepad2.right_bumper) {
                setIntake(1);
                // lift go up
            } else if (gamepad2.left_bumper) {
                setIntake(-1);
            } else {
                setIntake(0);
            }
        }
    }
}