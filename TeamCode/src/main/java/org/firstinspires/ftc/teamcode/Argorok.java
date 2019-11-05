package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Argorok {

    DcMotor frontLeft = null;
    DcMotor backLeft = null;
    DcMotor frontRight = null;
    DcMotor backRight = null;

    DcMotor lift = null;

    Servo leftClaw = null;
    Servo rightClaw = null;

    HardwareMap hwmap = null;

    public void init(HardwareMap hwm){

        hwmap = hwm;

        frontRight = hwmap.get(DcMotor.class, "frontRight");
        frontLeft = hwmap.get(DcMotor.class, "frontLeft");
        backRight = hwmap.get(DcMotor.class, "backRight");
        backLeft = hwmap.get(DcMotor.class, "backLeft");

        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        lift.setDirection(DcMotor.Direction.FORWARD);

        // leftClaw.setDirection();
        // rightClaw.setDirection();
    }

}
