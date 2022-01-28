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

        if (currentPoint < .33) {
            intakeArm(1);
        } else if (currentPoint < .67) {
            intakeArm(2);
        } else {
            intakeArm(3);
        }
        azure.leftB.setPower(.5);
        azure.leftA.setPower(.5);
        azure.rightB.setPower(.5);
        azure.rightA.setPower(.5);
        sleep(300);
        azure.leftB.setPower(0);
        azure.leftA.setPower(0);
        azure.rightB.setPower(0);
        azure.rightA.setPower(0);
        rotateNow(-33);
        azure.leftB.setPower(.5);
        azure.leftA.setPower(.5);
        azure.rightB.setPower(.5);
        azure.rightA.setPower(.5);
        sleep(1200);
        azure.leftB.setPower(0);
        azure.leftA.setPower(0);
        azure.rightB.setPower(0);
        azure.rightA.setPower(0);
        sleep(100);
        setIntake(1);
        sleep(500);
        setIntake(0);

        stopRecording(); // turn off the robots camera
        }
    }

