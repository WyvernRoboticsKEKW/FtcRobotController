package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Robotics on 1/29/2018.
 */

@TeleOp(name = "Movement Reader", group = "jv")
public class MovementReader extends OpMode {
    RobotConfigW robot = new RobotConfigW();
    boolean toggle = false;

    static final double COUNTS_PER_MOTOR_REV = 1120;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);


    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            double oRightPosition = robot.Right.getCurrentPosition() / COUNTS_PER_INCH;
            double oLeftPosition = robot.Left.getCurrentPosition() / COUNTS_PER_INCH;
            while(gamepad1.a){

                if(gamepad1.dpad_up){

                    robot.Right.setPower(-0.4);
                    robot.Left.setPower(-0.4);

                }else if(gamepad1.dpad_left){

                    robot.Right.setPower(0.4);
                    robot.Left.setPower(-0.4);

                }else if(gamepad1.dpad_down){

                    robot.Left.setPower(0.4);
                    robot.Right.setPower(0.4);

                }else if(gamepad1.dpad_right){

                    robot.Left.setPower(0.4);
                    robot.Right.setPower(-0.4);

                }else{

                    robot.Left.setPower(0);
                    robot.Right.setPower(0);

                }


            }
            telemetry.addData("Left", (robot.Left.getCurrentPosition()/COUNTS_PER_INCH)-oLeftPosition);
            telemetry.addData("Right", (robot.Right.getCurrentPosition()/COUNTS_PER_INCH)-oRightPosition);
            telemetry.update();
        }
    }


    public void readMovement(){


    }

    public void toggle(){

        if(gamepad1.b && !toggle){

            robot.leftArm.setPosition(1);
            robot.rightArm.setPosition(0);
            toggle = true;

        } else if(gamepad1.b && toggle){

            robot.leftArm.setPosition(0);
            robot.rightArm.setPosition(1);
            toggle = false;

        }

    }


}
