package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Encoders extends LinearOpMode {
    HornTailConfig robot = new HornTailConfig();
    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();

        encoderDrive(0.3,30,30,10);

    }

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {
            int templ = (int)(Math.abs(leftInches) / leftInches);
            int tempr = (int)(Math.abs(rightInches) / rightInches);

            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.left2.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.right2.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            robot.left2.setTargetPosition(newLeftTarget);
            robot.right2.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            robot.left2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.right2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            robot.e.reset();
            robot.left2.setPower(Math.abs(speed));
            robot.right2.setPower(Math.abs(speed));
            robot.left1.setPower(templ*speed);
            robot.right1.setPower(tempr*speed);

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (robot.e.seconds() < timeoutS) &&
                    (robot.left2.isBusy() && robot.right2.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        robot.left2.getCurrentPosition(),
                        robot.right2.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.left2.setPower(0);
            robot.right2.setPower(0);
            robot.left1.setPower(0);
            robot.right1.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.left2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.right2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }
}
