package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class AutoOpMode extends CommandOpMode {
    StormFly stormFly;

    @Override
    public void initialize() {
        stormFly = StormFly.getInstance(this);



    }
    private void driveForward(double v) {

        Servo extakeServo;
        @Override
        public void init()
            extakeServo = hardwareMap.servo.get("extakeServo");

            public void loop()

            extakeServo.setPosition(0.5);

        }


    }



}
