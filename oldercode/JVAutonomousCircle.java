package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Robotics on 9/27/2017.
 */
@Autonomous(name="Cirlce Around", group="JV")
@Disabled
public class JVAutonomousCircle extends LinearOpMode {
    robotConfig robot = new robotConfig();
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        robot.topLeft.setPower(.5);
        robot.topRight.setPower(.5);
        robot.bottomLeft.setPower(.5);
        robot.bottomRight.setPower(.5);
        while (opModeIsActive() && (runtime.seconds() < 1)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        runtime.reset();
        robot.topLeft.setPower(.5);
        robot.topRight.setPower(-.5);
        robot.bottomLeft.setPower(.5);
        robot.bottomRight.setPower(-.5);
        while (opModeIsActive() && (runtime.seconds() < .65)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        runtime.reset();
        robot.topLeft.setPower(.5);
        robot.topRight.setPower(.5);
        robot.bottomLeft.setPower(.5);
        robot.bottomRight.setPower(.5);
        while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        runtime.reset();
        robot.topLeft.setPower(-.5);
        robot.topRight.setPower(.5);
        robot.bottomLeft.setPower(-.5);
        robot.bottomRight.setPower(.5);
        while (opModeIsActive() && (runtime.seconds() < .65)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        runtime.reset();
        robot.topLeft.setPower(.5);
        robot.topRight.setPower(.5);
        robot.bottomLeft.setPower(.5);
        robot.bottomRight.setPower(.5);
        while (opModeIsActive() && (runtime.seconds() < 1)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        runtime.reset();
        robot.topLeft.setPower(-.5);
        robot.topRight.setPower(.5);
        robot.bottomLeft.setPower(-.5);
        robot.bottomRight.setPower(.5);
        while (opModeIsActive() && (runtime.seconds() < .65)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        runtime.reset();
        robot.topLeft.setPower(.5);
        robot.topRight.setPower(.5);
        robot.bottomLeft.setPower(.5);
        robot.bottomRight.setPower(.5);
        while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        runtime.reset();
        robot.topLeft.setPower(-.5);
        robot.topRight.setPower(.5);
        robot.bottomLeft.setPower(-.5);
        robot.bottomRight.setPower(.5);
        while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        runtime.reset();
        robot.topLeft.setPower(.5);
        robot.topRight.setPower(.5);
        robot.bottomLeft.setPower(.5);
        robot.bottomRight.setPower(.5);
        while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        runtime.reset();
        robot.topLeft.setPower(-.5);
        robot.topRight.setPower(.5);
        robot.bottomLeft.setPower(-.5);
        robot.bottomRight.setPower(.5);
        while (opModeIsActive() && (runtime.seconds() < .65)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        runtime.reset();
        robot.topLeft.setPower(.5);
        robot.topRight.setPower(.5);
        robot.bottomLeft.setPower(.5);
        robot.bottomRight.setPower(.5);
        while (opModeIsActive() && (runtime.seconds() < 1)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        runtime.reset();
        robot.topLeft.setPower(-.5);
        robot.topRight.setPower(.5);
        robot.bottomLeft.setPower(-.5);
        robot.bottomRight.setPower(.5);
        while (opModeIsActive() && (runtime.seconds() < .65)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        runtime.reset();robot.topLeft.setPower(.5);
        robot.topRight.setPower(.5);
        robot.bottomLeft.setPower(.5);
        robot.bottomRight.setPower(.5);
        while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        runtime.reset();robot.topLeft.setPower(-.5);
        robot.topRight.setPower(.5);
        robot.bottomLeft.setPower(-.5);
        robot.bottomRight.setPower(.5);
        while (opModeIsActive() && (runtime.seconds() < .65)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        runtime.reset();robot.topLeft.setPower(.5);
        robot.topRight.setPower(.5);
        robot.bottomLeft.setPower(.5);
        robot.bottomRight.setPower(.5);
        while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        runtime.reset();robot.topLeft.setPower(.5);
        robot.topRight.setPower(-.5);
        robot.bottomLeft.setPower(.5);
        robot.bottomRight.setPower(-.5);
        while (opModeIsActive() && (runtime.seconds() < .65)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        runtime.reset();robot.topLeft.setPower(.5);
        robot.topRight.setPower(.5);
        robot.bottomLeft.setPower(.5);
        robot.bottomRight.setPower(.5);
        while (opModeIsActive() && (runtime.seconds() < 1.5)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        runtime.reset();

    }

}
