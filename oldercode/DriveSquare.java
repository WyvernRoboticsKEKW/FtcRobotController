package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Robotics on 9/20/2017.
 */
@Autonomous(name = "DriveSquare", group = "JV")
public class DriveSquare extends LinearOpMode {
    ProtoConfig robot = new ProtoConfig();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);
        waitForStart();
        robot.bottomLeft.setPower(1);
        robot.bottomRight.setPower(1);
        robot.topLeft.setPower(1);
        robot.topRight.setPower(1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.bottomLeft.setPower(1);
        robot.bottomRight.setPower(-1);
        robot.topLeft.setPower(1);
        robot.topRight.setPower(-1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.56)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.bottomLeft.setPower(1);
        robot.bottomRight.setPower(1);
        robot.topLeft.setPower(1);
        robot.topRight.setPower(1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.bottomLeft.setPower(1);
        robot.bottomRight.setPower(-1);
        robot.topLeft.setPower(1);
        robot.topRight.setPower(-1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.56)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.bottomLeft.setPower(1);
        robot.bottomRight.setPower(1);
        robot.topLeft.setPower(1);
        robot.topRight.setPower(1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.bottomLeft.setPower(1);
        robot.bottomRight.setPower(-1);
        robot.topLeft.setPower(1);
        robot.topRight.setPower(-1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.56)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.bottomLeft.setPower(1);
        robot.bottomRight.setPower(1);
        robot.topLeft.setPower(1);
        robot.topRight.setPower(1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.0)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.bottomLeft.setPower(1);
        robot.bottomRight.setPower(-1);
        robot.topLeft.setPower(1);
        robot.topRight.setPower(-1);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.56)) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
    }
}
