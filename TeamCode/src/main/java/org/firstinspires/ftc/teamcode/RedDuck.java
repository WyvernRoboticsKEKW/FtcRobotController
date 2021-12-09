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

        driveDistance(6);
        rotateNow(-90);
        driveDistance(24);
        azure.carousel.setPower(-0.5);
        sleep(2000);
        azure.carousel.setPower(0);
        driveDistance(-24);
        rotateNow(90);
        driveDistance(-5);
        driveDistance(24);
        if(currentPoint < .33){

        }

        stopRecording();
    }
}
