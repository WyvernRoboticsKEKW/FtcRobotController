package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {

    DcMotor frontLeft = null;
    DcMotor backLeft = null;
    DcMotor frontRight = null;
    DcMotor backRight = null;

    HardwareMap hwmap = null;

    public void init(HardwareMap hwm){

        hwmap = hwm;

        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

    }

}
