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

        driveDistance(6); // go forward
        // make camera recognize which level he duck is on the barcode
        rotateNow(90); // go right
        driveDistance(24); // move forward
        rotateNow(0); // turn left
        // drop preload box on correct level
        rotateNow(-90);//turn all the way on the left
        driveDistance(36); // move to the carousel
        rotateNow(90); // turn to the direction of the stoage unit
        driveDistance(24); // move into the storage unit

        if(currentPoint < .33){

        }

        stopRecording();
    }
}
