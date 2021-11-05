package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.*;

public class Azure {
    DcMotor leftA;
    DcMotor leftB;
    DcMotor rightA;
    DcMotor rightB;
    Servo liftyBoi;
    // Drivetrain

    public Azure(HardwareMap hwmap){
        leftA = hwmap.get(DcMotor.class, "leftA");
        leftB = hwmap.get(DcMotor.class, "leftB");
        rightA = hwmap.get(DcMotor.class, "rightA");
        rightB = hwmap.get(DcMotor.class, "rightB");

        liftyBoi = hwmap.get(Servo.class, "lift1");
        // defining devices from the config

        leftA.setDirection(DcMotorSimple.Direction.REVERSE);
        leftB.setDirection(DcMotorSimple.Direction.REVERSE);
        // Making the motors on the left move forward

    }
}
