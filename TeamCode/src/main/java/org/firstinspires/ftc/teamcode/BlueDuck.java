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

        while (!pipeline.isReady()) sleep(100);

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
        sleep(200);
        azure.leftB.setPower(0);
        azure.leftA.setPower(0);
        azure.rightB.setPower(0);
        azure.rightA.setPower(0);
        rotateNow(-80);
        azure.leftB.setPower(-.5);
        azure.leftA.setPower(-.5);
        azure.rightB.setPower(-.5);
        azure.rightA.setPower(-.5);
        sleep(1000);
        azure.leftB.setPower(0);
        azure.leftA.setPower(0);
        azure.rightB.setPower(0);
        azure.rightA.setPower(0);
        sleep(100);
        rotateNow(-15);
        sleep(460);
        azure.leftB.setPower(-.2);
        azure.leftA.setPower(-.2);
        azure.rightB.setPower(-.2);
        azure.rightA.setPower(-.2);
        sleep(400);
        azure.leftB.setPower(0);
        azure.leftA.setPower(0);
        azure.rightB.setPower(0);
        azure.rightA.setPower(0);
        sleep(200);
        setCarousel(-1);
        azure.leftA.setPower(-.1);
        azure.leftB.setPower(-.1);
        azure.rightB.setPower(-.1);
        azure.rightA.setPower(-.1);
        sleep(2000);
        setCarousel(0);
        rotateNow(-10);
        sleep(200);
        azure.leftB.setPower(.6);
        azure.leftA.setPower(.6);
        azure.rightB.setPower(.6);
        azure.rightA.setPower(.6);
        sleep(1500);
        azure.leftB.setPower(0);
        azure.leftA.setPower(0);
        azure.rightB.setPower(0);
        azure.rightA.setPower(0);
        setIntake(1);
        sleep(600);
        setIntake(0);



        stopRecording(); // turn off the robots camera*/
    }
}