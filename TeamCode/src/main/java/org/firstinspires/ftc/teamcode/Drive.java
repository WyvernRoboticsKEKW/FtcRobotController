package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name ="Driver control period")
public class Drive extends Drivetrain {
    @Override
    public void runOpMode() throws InterruptedException {
        initialization();
        azure.arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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
            
            if (gamepad2.y){
                setCarousel(1);
            } else if (gamepad2.a) {
                setCarousel(-1);
            } else if (gamepad2.b){
                setCarousel(0);
            }

        }
    }
}