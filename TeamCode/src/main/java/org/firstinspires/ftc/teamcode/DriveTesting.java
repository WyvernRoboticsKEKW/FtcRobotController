package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@Autonomous(name = "DriveTesting")
public class DriveTesting extends Drivetrain {
    @Override
    public void runOpMode() throws InterruptedException {

        initialization();
        waitForStart();

        driveDistance(2);
    }
}