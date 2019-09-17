package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Autonomous(name="test intake")
public class Test extends LinearOpMode {
    DrogonConfig robot = new DrogonConfig();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap,telemetry);
        robot.autoinit();
        waitForStart();
        robot.intake0.setPosition(.44);
        robot.intake.setPower(.3);
        sleep(1000);
        robot.intake.setPower(0);
    }
}
