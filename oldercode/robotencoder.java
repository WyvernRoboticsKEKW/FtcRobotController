package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.robotcontroller.external.samples.PushbotAutoDriveByGyro_Linear.COUNTS_PER_INCH;

/**
 * Created by Robotics on 9/28/2017.
 */

@Autonomous(name = "BananaShelfGlasses",group = "JV")
@Disabled
public class robotencoder extends LinearOpMode{
    robotConfig banana = new robotConfig();
    private ElapsedTime runtime = new ElapsedTime();
    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);




    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = banana.topLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = banana.topRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            banana.topLeft.setTargetPosition(newLeftTarget);
            banana.topRight.setTargetPosition(newRightTarget);
            banana.bottomLeft.setTargetPosition(newLeftTarget);
            banana.bottomRight.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            banana.topLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            banana.topRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            banana.bottomLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            banana.bottomRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            banana.topLeft.setPower(Math.abs(speed));
            banana.topRight.setPower(Math.abs(speed));
            banana.bottomLeft.setPower(Math.abs(speed));
            banana.bottomRight.setPower(Math.abs(speed));


            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (banana.topLeft.isBusy() && banana.topRight.isBusy() && banana.bottomLeft.isBusy() && banana.bottomRight.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d", banana.topLeft.getCurrentPosition(), banana.topRight.getCurrentPosition());
                telemetry.addData("Path2",  "Running at %7d :%7d", banana.bottomLeft.getCurrentPosition(), banana.bottomRight.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            banana.topLeft.setPower(0);
            banana.topRight.setPower(0);
            banana.bottomLeft.setPower(0);
            banana.bottomRight.setPower(0);

            // Turn off RUN_TO_POSITION
            banana.topLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            banana.topRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            banana.bottomLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            banana.bottomRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }


    @Override
    public void runOpMode() throws InterruptedException {
        banana.init(hardwareMap);
        waitForStart();
        encoderDrive(0.7,6.0,6.0,10);
        encoderDrive(0.7,16.0,-16.0,10);
        encoderDrive(0.7,9.0,9.0,10);
        encoderDrive(0.7,-16.0,16.0,10);
        encoderDrive(0.7,6.0,6.0,10);
        encoderDrive(0.7,-16.0,16.0,10);
        encoderDrive(0.7,9.0,9.0,10);
        encoderDrive(0.7,-32.0,32.0,10);
        encoderDrive(0.7,9.0,9.0,10);
        encoderDrive(0.7,-16.0,16.0,10);
        encoderDrive(0.7,6.0,6.0,10);
        encoderDrive(0.7,-16.0,16.0,10);
        encoderDrive(0.7,9.0,9.0,10);
        encoderDrive(0.7,16.0,-16.0,10);





    }
}
