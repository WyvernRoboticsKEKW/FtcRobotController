package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

public abstract class Drivetrain extends LinearOpMode {
    Azure azure;
    Pipeline pipeline;

    public void initialization() {
        azure = new Azure(hardwareMap);
    }

    public void setDrivePower(double lPower, double rPower) {
        azure.leftA.setPower(lPower);
        azure.leftB.setPower(lPower);
        azure.rightA.setPower(rPower);
        azure.rightB.setPower(rPower);
    }

    public void driveDistance(double distance) {
        // 1.48 motor rotations per wheel rotation
        int ticks = (int)(distance*1120*1.48/((4+(2/7))*Math.PI));

        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        azure.leftA.setTargetPosition(ticks);
        azure.leftB.setTargetPosition(ticks);
        azure.rightA.setTargetPosition(ticks);
        azure.rightB.setTargetPosition(ticks);

        setMode(DcMotor.RunMode.RUN_TO_POSITION);

        setDrivePower(.5, .5);

        while(opModeIsActive() && (azure.leftA.isBusy() || azure.leftB.isBusy() || azure.rightA.isBusy() || azure.rightB.isBusy())) {
            sleep(10);

            telemetry.addData("LeftA", azure.leftA.isBusy());
            telemetry.addData("LeftB", azure.leftB.isBusy());
            telemetry.addData("RightA", azure.rightA.isBusy());
            telemetry.addData("RightB", azure.rightB.isBusy());


            telemetry.addData("Current position", azure.leftA.getCurrentPosition());
            telemetry.addData("Target position", ticks);
            telemetry.addData("Current position", azure.leftB.getCurrentPosition());
            telemetry.addData("Target position", ticks);
            telemetry.addData("Current position", azure.rightA.getCurrentPosition());
            telemetry.addData("Target position", ticks);
            telemetry.addData("Current position", azure.rightB.getCurrentPosition());
            telemetry.addData("Target position", ticks);
            telemetry.update();
            // wait until the motors stop moving
        }

        setDrivePower(0, 0);

        setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setMode(DcMotor.RunMode runMode){
        azure.leftA.setMode(runMode);
        azure.leftB.setMode(runMode);
        azure.rightA.setMode(runMode);
        azure.rightB.setMode(runMode);
    }

    public void setCarousel(double power){
        azure.carousel.setPower(power*0.8);
    }

    public void setLift(double power){
        azure.arm.setPower(power);
    }

    public void setIntake(double power) {
        azure.intake.setPower(power);
    }

    public void rotateNow(double turnAngle){
        double angularDistance = 0;
        do {
            float firstAngle = -azure.imu.getAngularOrientation().firstAngle;
            turnAngle = ((turnAngle % 360) + 360) % 360;
            double turnVal = 1;

            if (turnAngle - firstAngle < 0) turnVal = -1;
            // counter clockwise

            angularDistance = Math.abs(turnAngle - firstAngle);
            if (angularDistance > 180) { // dealing with edge case
                turnVal *= -1;
                angularDistance = 360 - angularDistance; // calculating shorter angularDistance
            }

            turnVal = turnVal * angularDistance / 12;

            setDrivePower(turnVal, -turnVal); // use turnVal to determine direction
        } while(opModeIsActive() && angularDistance > 5);
    }

    public void autonomousCamera(){
        pipeline = new Pipeline();

        azure.camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                azure.camera.startStreaming(320,240, OpenCvCameraRotation.UPRIGHT);
                azure.camera.setPipeline(pipeline);
            }
            @Override
            public void onError(int errorCode)
            {
            }
        });
    }
    public void intakeArm(int armPosition){
        azure.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        azure.arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        switch (armPosition) {
            case 1:
                azure.arm.setTargetPosition(12);
                break;
            case 2:
                azure.arm.setTargetPosition(290);
                break;
            case 3:
                azure.arm.setTargetPosition(291); // changed from 960
                break;
            default:
                azure.arm.setTargetPosition(0);
        }
        azure.arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        azure.arm.setPower(1);

    }
    public void stopRecording(){
        azure.camera.stopRecordingPipeline();
        azure.camera.closeCameraDevice();
    }
}
