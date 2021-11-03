package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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
    }

    public void setLift(boolean liftyBoi){
        if (liftyBoi){
            azure.liftyBoi.setPosition(1);
        } else {
            azure.liftyBoi.setPosition(0);
        }
    }
}
