package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name ="RedWareHouse")
public class RedWareHouse extends Drivetrain {
    @Override
    public void runOpMode() throws InterruptedException {
        initialization();
        autonomousCamera();
        waitForStart();

        while(!pipeline.isReady()) sleep(100);

        double currentPoint = pipeline.getRectMidpointX();

        driveDistance(6); // drive forward
        rotateNow(-90); // turn left
        driveDistance(24);
        rotateNow(0);
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
        rotateNow(-90);
        driveDistance(48);
        rotateNow(0);
        driveDistance(14);
        rotateNow(-90);
        driveDistance(5);

        stopRecording(); // turn off the robots camera
        }
    }

