package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name ="BlueDuck")
public class BlueDuck extends Drivetrain {
    @Override
    public void runOpMode() throws InterruptedException {
        initialization();
        autonomousCamera();
        waitForStart();

        while(!pipeline.isReady()) sleep(100);

        double currentPoint = pipeline.getRectMidpointX();

        driveDistance(6); // move forward
        /*rotateNow(90); // turn towards the carousel
        driveDistance(24); // go to he carousel
        // raise arm to spin carousel
        azure.carousel.setPower(.5); // move the wheel to spin carousel
        sleep(2000); // continue for 2 seconds
        azure.carousel.setPower(0); // stop moving wheel
        rotateNow(-90); // go left
        driveDistance(48); // move forward
        rotateNow(0); // turn right*/
        rotateNow(-90); // turn to the left
        driveDistance(14); // drive in front of the shipping hub
        rotateNow(0); // turn to face the shipping hub
        if(currentPoint < .33){ // put arm in correct position
            intakeArm(1);
        } else if(currentPoint < .67) {
            intakeArm(2);
        } else {
            intakeArm(3);
        }
        sleep(1000); // hold the arm there for 1 sec
        azure.intake.setPower(1); // turn the surgical tubes to push out the preloaded box
        sleep(500); // keep spinning for half a second
        azure.intake.setPower(0); // stop spinning
        rotateNow(90);//turn all the way on the right
        driveDistance(36); // move to the carousel
        rotateNow(-90); // turn to the direction of the storage unit
        driveDistance(24); // move into the storage unit

        stopRecording(); // turn off the robots camera
        }
    }
