package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name ="BlueWareHouse")
public class BlueWareHouse extends Drivetrain {
    @Override
    public void runOpMode() throws InterruptedException {
        initialization();
        autonomousCamera();
        waitForStart();

        while(!pipeline.isReady()) sleep(100);

        double currentPoint = pipeline.getRectMidpointX();

        driveDistance(6); // drive forward
        rotateNow(90); // turn left
        driveDistance(24); // drive to the shipping hub
        rotateNow(0); // face the shipping hub
        if (currentPoint < .33) {
            intakeArm(1);
        } else if (currentPoint < .67) {
            intakeArm(2);
        } else {
            intakeArm(3);
        }
        sleep(1000); // hold the arm there for 1 sec
        azure.intake.setPower(1); // turn the surgical tubes to push out the preloaded box
        sleep(500); // keep spinning for half a second
        azure.intake.setPower(0); // stop spinning
        rotateNow(90); // turn to face the carousel
        driveDistance(48); // drive to the carousel
        rotateNow(0); // turn to go to the storage unit
        driveDistance(14); // drive to the storage unit
        rotateNow(90); // turn right
        driveDistance(5);

        stopRecording(); // turn off the robots camera
        }
    }
