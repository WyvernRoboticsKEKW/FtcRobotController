package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Robotics on 2/12/2018.
 */

@TeleOp(name="Tank_Movement_1c", group="jv")

public class TMOC extends OpMode {
    static final double COUNTS_PER_MOTOR_REV = 1120;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 1.496;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    double arm_position = 0;
    double armp;
    RobotConfigW robot = new RobotConfigW();

    @Override
    public void init() {
        robot.init(hardwareMap);
        armp = robot.Elevator.getCurrentPosition()/COUNTS_PER_INCH;
    }

    @Override
    public void loop() {
        //Right Drive is faster
        checkArm();
        robot.Right.setPower(gamepad1.left_stick_y);
        robot.Left.setPower(gamepad1.right_stick_y);
        colora(robot.colorArm);
        arm(robot.leftArm, robot.rightArm);
        eArm(robot.Elevator);
        if(gamepad1.y){

            robot.Right.setPower(gamepad1.left_stick_y / 2);
            robot.Left.setPower(gamepad1.right_stick_y / 2);

        }

    }



    public void colora(Servo c) {

        if (gamepad1.b) {
            c.setPosition(c.getPosition() + 0.01);
        } else if (gamepad1.a) {
            c.setPosition(c.getPosition() - 0.01);

        }
    }

    public void arm(Servo ls, Servo rs) {

        if (gamepad1.right_bumper) {

            rs.setPosition(rs.getPosition() + 0.02);
            ls.setPosition(ls.getPosition() - 0.02);

        } else if (gamepad1.left_bumper) {
            ls.setPosition(ls.getPosition() + 0.02);
            rs.setPosition(rs.getPosition() - 0.02);

        }


    }

    public void eArm(DcMotor e) {
        double dir;
        float rt = gamepad1.left_trigger;
        float lt = gamepad1.right_trigger;
        if ((rt > 0)&&(arm_position < 13)) {
            e.setPower(0.65);
        } else if ((lt > 0)&&(arm_position > 0)){
            e.setPower(-0.65);
        }
        else
            e.setPower(0);

    }

    public void checkArm(){


        arm_position = (robot.Elevator.getCurrentPosition()/COUNTS_PER_INCH) - armp;
        telemetry.addData("Arm Position: ",arm_position);
        telemetry.update();
        arm_position = 3;

    }

}
