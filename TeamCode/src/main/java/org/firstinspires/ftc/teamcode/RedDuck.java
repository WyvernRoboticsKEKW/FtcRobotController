package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name ="RedDuck")
public class RedDuck extends Drivetrain {
    @Override
    public void runOpMode() throws InterruptedException {
        initialization();
        autonomousCamera();
        waitForStart();

        while(!pipeline.isReady()) sleep(100);

        double currentPoint = pipeline.getRectMidpointX();

        driveDistance(6); // move forward
        rotateNow(-90); // turn towards the carousel
        driveDistance(24); // go to he carousel
        // raise arm to spin carousel
        azure.carousel.setPower(-.5); // move the wheel to spin carousel
        sleep(2000); // continue for 2 seconds
        azure.carousel.setPower(0); // stop moving wheel
        rotateNow(90); // go right
        driveDistance(48); // move forward
        rotateNow(0); // turn left
        if(currentPoint < .33){

        } else if(currentPoint < .67) {

        } else {

        }
        rotateNow(-90);//turn all the way on the left
        driveDistance(36); // move to the carousel
        rotateNow(90); // turn to the direction of the storage unit
        driveDistance(24); // move into the storage unit

        if(currentPoint < .33){

        }

        stopRecording();
    }
}
