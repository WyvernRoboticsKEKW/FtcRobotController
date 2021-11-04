package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

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

        azure.leftA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        azure.leftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        azure.rightA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        azure.rightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        setDrivePower(.5, .5);

        while(azure.leftA.isBusy() || azure.leftB.isBusy() || azure.rightA.isBusy() || azure.rightB.isBusy()) {
            sleep(10);
        }

        setDrivePower(0, 0);

        azure.leftA.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        azure.leftB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        azure.rightA.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        azure.rightB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setLift(boolean liftyBoi){
        if (liftyBoi){
            azure.liftyBoi.setPosition(1);
        } else {
            azure.liftyBoi.setPosition(0);
        }
    }
}
