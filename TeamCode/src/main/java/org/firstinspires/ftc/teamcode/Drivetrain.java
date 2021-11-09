package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public abstract class Drivetrain extends LinearOpMode {
    Azure azure;

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
        int ticks = (int)(distance*1120*1.48*(4+(2/7)*Math.PI));

        int leftAStart = azure.leftA.getCurrentPosition();
        int leftBStart = azure.leftB.getCurrentPosition();
        int rightAStart = azure.rightA.getCurrentPosition();
        int rightBStart = azure.rightB.getCurrentPosition();

        azure.leftA.setTargetPosition(leftAStart+ticks);
        azure.leftB.setTargetPosition(leftBStart+ticks);
        azure.rightA.setTargetPosition(rightAStart+ticks);
        azure.rightB.setTargetPosition(rightBStart+ticks);

        setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        setDrivePower(.5, .5);

        while(azure.leftA.isBusy() || azure.leftB.isBusy() || azure.rightA.isBusy() || azure.rightB.isBusy()) {
            sleep(10);
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

    public void setLift(boolean liftyBoi){
        if (liftyBoi){
            azure.liftyBoi.setPosition(1);
        } else {
            azure.liftyBoi.setPosition(0);
        }
    }
    public void rotateNow(double turnAngle){
        float firstAngle = azure.imu.getAngularOrientation().firstAngle;
        turnAngle = ((turnAngle%360)+360)%360;
        double turnVal = 1;

        if(turnAngle-firstAngle < 0) turnVal = -1;
        // counter clockwise
        if(Math.abs(turnAngle-firstAngle)>180) turnVal *= -1;

    }

    /*class ConvertToGreyPipeline extends OpenCvPipeline
    {
        Mat grey = new Mat();

        @Override
        public Mat processFrame(Mat input)
        {
            Imgproc.cvtColor(input, grey, Imgproc.COLOR_RGB2GRAY);
            return grey;
        }
    }*/
}
