package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp(name="Doig Drive", group="jv")
public class DoigDrive extends LinearOpMode {
    DrogonConfig robot = new DrogonConfig();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap,telemetry);
        waitForStart();
        while(opModeIsActive()) {
            if(gamepad1.left_bumper) {
                robot.left1.setPower(-gamepad1.left_stick_y);
                robot.left2.setPower(-gamepad1.left_stick_y);
                robot.right1.setPower(-gamepad1.right_stick_y);
                robot.right2.setPower(-gamepad1.right_stick_y);
            }else{
                robot.left1.setPower(-.5*gamepad1.left_stick_y);
                robot.left2.setPower(-.5*gamepad1.left_stick_y);
                robot.right1.setPower(-.5*gamepad1.right_stick_y);
                robot.right2.setPower(-.5*gamepad1.right_stick_y);
            }
        }
    }

}
