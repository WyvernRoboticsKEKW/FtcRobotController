package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.robotConfig;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;


@Autonomous(name = "BackBlueW", group = "JV")

public class BackBlueW extends LinearOpMode {

    RobotConfigW robot = new RobotConfigW();
    VuforiaLocalizer vuforia;

    private ElapsedTime runtime = new ElapsedTime();
    private ElapsedTime et = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 1120;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);

    //private int color; //red = 0 blue = 1
    private int position; //what position the glyph goes into
    boolean blu;
    private static final double B = 10;
    private static final double A = 0.7;
    @Override
    public void runOpMode() throws InterruptedException {
        //int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        //VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);


        //parameters.vuforiaLicenseKey = "AWVzqr7/////AAAAmaZAP7c7xk0XlQDSP/xd/VFZwA5JI6iF0bD/ky2ie+tZKGH1fiLfIaumSy2x58nYyjtrQn8EB4he0dpIEmx7gOcKJqzCTClJgml1WA9aE2JhvwAa39LHuEwUzLvaBn4r+Xxo2+XXAJI50O+icTAXd8k6BlIcVhGOPNLTFTNH/Y4LXQDm7RB4Nj6Vrbm74OuOP9lHBD6z2z48izkYL7UiXKCYdKIx7tpXiw3lAXjNuje6uh79Gju5Nx8glaTwldVuiPqHkkfYJl79iCTv1Jze3n6Hy7SXTp2RV64fftLSOXSPBdI9E0pfLFT1oOiZDpKn48h3nf9wyUbfOUhz7aY5hrq2C/AxYvHu5Jsh44umisvH";


        //parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        //this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);


        //VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        //VuforiaTrackable relicTemplate = relicTrackables.get(0);
        //relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
        robot.init(hardwareMap);

        waitForStart();
        //relicTrackables.activate();

        position = 1;

        /*while(opModeIsActive()){

            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

            if (vuMark != RelicRecoveryVuMark.UNKNOWN){

                if(vuMark == RelicRecoveryVuMark.LEFT){

                    position = 1;

                }else if(vuMark == RelicRecoveryVuMark.CENTER){

                    position = 2;

                }else if(vuMark == RelicRecoveryVuMark.RIGHT){

                    position = 3;

                }
                telemetry.addData("VuMark: %s", vuMark);
                telemetry.update();
                break;

            }

        }*/

        robot.leftArm.setPosition(1);
        robot.rightArm.setPosition(0);
        //todo pickup glyph
        //scanning color and knock off jewel
        robot.colorArm.setPosition(0);
        sleep(3000);
        blu = colorTest();
        sleep(1000);

        jewel(blu);
        sleep(500);
        robot.colorArm.setPosition(1);

        if (!blu){

            drive(4, 4);

        }

        drive(-1, -1);

        //sleep(1000);

        et.reset();
        /*while(et.time() <= 0.1){

            robot.Elevator.setPower(0.5);

        }*/



        drive(-11, -11);
        et.reset();

        drive(10, -10);
        drive(-30, -30);
        robot.rightArm.setPosition(1);
        robot.leftArm.setPosition(0);
        drive(4,4);
        /*if (position == 1){

            encoderDrive(A, B, -5, -5);
            encoderDrive(A, B, 10, -10);
            encoderDrive(A, B, -6, -6);

        }else if (position == 2){

            encoderDrive(A, B, -6, -6);

        }else if (position == 3){

            encoderDrive(A, B, -5, -5);
            encoderDrive(A, B, -10, 10);
            encoderDrive(A, B, -6, -6);

        }*/

        /*int position;
        robot.init(hardwareMap);
        robot.topLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.topRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.bottomLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.bottomRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();
        encoderDrive(0.7, 6, 6, 10);
        encoderDrive(0.7, 25, -25, 10);
        encoderDrive(0.7, 6, 6, 10);
        encoderDrive(0.7, -6, -6, 10);
        encoderDrive(0.7, 25, -25, 10);

        position = 1;
        if (position == 1){
            encoderDrive(0.7, -20, 20, 10);
            encoderDrive(0.7, 8, 8, 10);
            encoderDrive(0.7, 19, -19, 10);
            encoderDrive(0.7, 12, 12, 10);}
        if (position == 2){
            encoderDrive(0.7, 12, 12, 10);}
        if (position == 3){
            encoderDrive(0.7, 20, -20, 10);
            encoderDrive(0.7, 8, 8, 10);
            encoderDrive(0.7, -18, 18, 10);
            encoderDrive(0.7, 12, 12, 10);}*/
    }




    public void encoderDrive(double speed, double timeoutS,
                             double leftInches, double rightInches
    ) {
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
    private void drive(double lin, double rin) {
        encoderDrive(A, B, lin, rin);
    }

    private boolean colorTest() {

        boolean blue = false;

        if (robot.sensor.blue() > robot.sensor.red()) {
            blue = true;

        }

        return blue;

    }

    private void jewel(boolean blue) {

        if (blue) {

            drive(-5, -5);

        } else if (!blue) {

            drive(5, 5);

        }

    }

}
