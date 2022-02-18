package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@Autonomous(name = "TurnTesting")
public class TurnTesting extends Drivetrain {
    @Override
    public void runOpMode() throws InterruptedException {

        initialization();
        waitForStart();

        /*while(opModeIsActive()) {
            Orientation orientation = azure.imu.getAngularOrientation();
            telemetry.addData("First", orientation.firstAngle);
            telemetry.addData("Second", orientation.secondAngle);
            telemetry.addData("Third", orientation.thirdAngle);
            telemetry.update();*/
        //rotateNow(-90);

    }
}
