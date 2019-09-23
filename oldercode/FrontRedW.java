package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by robotics on 10/19/2017.
 */

@Autonomous(name = "FrontRedW",group = "JV")

public class FrontRedW extends LinearOpMode {
    boolean blu;

    RobotConfigW robot = new RobotConfigW();
    VuforiaLocalizer vuforia;

    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    //private int color;
    int position; //what position the glyph goes into
    private static final double B = 10;
    private static final double A = 0.7;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        waitForStart();




        robot.leftArm.setPosition(1);
        robot.rightArm.setPosition(0);
        robot.colorArm.setPosition(1);
        sleep(3000);
        blu = colorTest();
        sleep(1000);

        jewel(blu);
        sleep(500);

        robot.colorArm.setPosition(0);

        if (!blu){

            drive(10,10);

        }

        drive(25, 25);
        drive(-21, 21);
        drive(-10, -10);

        robot.rightArm.setPosition(1);
        robot.leftArm.setPosition(0);


        /*//todo color sensor
        encoderDrive(A, B, -9, -9);
        encoderDrive(A, B, 24, -24);
        //todo scanner
        encoderDrive(A, B, 24, 24);
        encoderDrive(A, B, -15, 15);
        encoderDrive(A, B, -24, -24);
        encoderDrive(A, B, -10, 10);
        //the following statement is temporary
        position = 1;
        if(position == 1){
            encoderDrive(A, B, 5, 5);
            encoderDrive(A, B, -10, 10);
            encoderDrive(A, B, -6, -6);
        }else if(position == 2){
            encoderDrive(A, B, -6, -6);
        }else if(position == 3){
            encoderDrive(A, B, -5, -5);
            encoderDrive(A, B, -10, 10);
            encoderDrive(A, B, -6, -6);
        }*/

    }




    public void encoderDrive(double speed, double timeoutS , double leftInches, double rightInches) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.Left.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.Right.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            robot.Left.setTargetPosition(newLeftTarget);
            robot.Right.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            robot.Left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.Right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.Left.setPower(Math.abs(speed));
            robot.Right.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.Left.isBusy() && robot.Right.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d", robot.Left.getCurrentPosition(), robot.Right.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.Left.setPower(0);
            robot.Right.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.Left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.Right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }

    }

    private void drive(double lin, double rin){
        encoderDrive(A, B, lin, rin);
    }

    private boolean colorTest(){

        boolean blue;

        if(robot.sensor.blue() > robot.sensor.red()){

            blue = true;

        }else{

            blue = false;

        }

        return blue;

    }

    private void jewel(boolean blue){

        if(blue == false){

            drive(-5, -5);

        }else if(blue == true){

            drive(5, 5);

        }

    }

}
