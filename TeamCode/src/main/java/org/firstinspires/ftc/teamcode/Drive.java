package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name ="Driver control period")
public class Drive extends Drivetrain {
    @Override
    public void runOpMode() throws InterruptedException {
        initialization();
        waitForStart();
        while(opModeIsActive()) {
            setDrivePower(-gamepad1.left_stick_y, -gamepad1.right_stick_y);

            if(gamepad2.right_trigger > 0.5) {
                setLift(true);
                // lift go up
            } else if (gamepad2.left_trigger > 0.5) {
                setLift(false);
                // lift go down
            }
        }
    }
}
