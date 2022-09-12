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

        while (!pipeline.isReady()) sleep(100);

        double currentPoint = pipeline.getRectMidpointY() / 240;

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
        sleep(870);
        azure.leftB.setPower(0);
        azure.leftA.setPower(0);
        azure.rightB.setPower(0);
        azure.rightA.setPower(0);
        rotateNow(-40);
        sleep(60);
        azure.leftB.setPower(-.5);
        azure.leftA.setPower(-.5);
        azure.rightB.setPower(-.5);
        azure.rightA.setPower(-.5);
        sleep(1010);
        setCarousel(-1);
        sleep(3300);
        setCarousel(0);
        sleep(100);
        azure.leftB.setPower(.5);
        azure.leftA.setPower(.5);
        azure.rightB.setPower(.5);
        azure.rightA.setPower(.5);
        sleep(1710);
        azure.leftB.setPower(0);
        azure.leftA.setPower(0);
        azure.rightB.setPower(0);
        azure.rightA.setPower(0);
        sleep(100);
        setIntake(-.8);
        sleep(600);
        azure.leftB.setPower(-.6);
        azure.leftA.setPower(-.6);
        azure.rightB.setPower(-.6);
        azure.rightA.setPower(-.6);
        sleep(650);
        azure.leftB.setPower(0);
        azure.leftA.setPower(0);
        azure.rightB.setPower(0);
        azure.rightA.setPower(0);
        sleep(100);
        rotateNow(35);
        sleep(400);
        azure.leftB.setPower(.6);
        azure.leftA.setPower(.6);
        azure.rightB.setPower(.6);
        azure.rightA.setPower(.6);
        sleep(900);
        azure.leftB.setPower(0);
        azure.leftA.setPower(0);
        azure.rightB.setPower(0);
        azure.rightA.setPower(0);

        stopRecording(); // turn off the robots camera*/
        /*azure.leftB.setPower(.5);
        azure.leftA.setPower(.5);
        azure.rightB.setPower(.5);
        azure.rightA.setPower(.5);
        sleep(300);
        azure.leftB.setPower(0);
        azure.leftA.setPower(0);
        azure.rightB.setPower(0);
        azure.rightA.setPower(0);
        rotateNow(33);
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

         */
    }
}
