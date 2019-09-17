package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@Disabled
@TeleOp(name="Drogon Drive", group = "jv")
public class DrDrive extends LinearOpMode {

    DrogonConfig robot = new DrogonConfig();
    double df;
    double tf;

    @Override
    public void runOpMode() throws InterruptedException {
        // init

        robot.init(hardwareMap,telemetry);

        // reserved for initialization instructions

        waitForStart();

        // loop

        while(opModeIsActive()){

            // process inputs

            df = .5;

            if(gamepad1.left_bumper)
                df=1;

            double d = -df*gamepad1.left_stick_y;
            double t = df*gamepad1.left_stick_x;
            double l = d + t;
            double r = d - t;

            // set motors

            robot.setMotors(l,r);

            if(gamepad1.left_trigger > 0){
                robot.intake0.setPosition(.83);
            }else if(gamepad1.right_trigger > 0){
                robot.intake0.setPosition(.4);
            }

            robot.arm1.setPower(-gamepad1.right_stick_y);
            robot.arm2.setPower(-gamepad1.right_stick_y);
            robot.intake.setPower(gamepad1.right_stick_x);


            telemetry.update();

        }
    }

}
