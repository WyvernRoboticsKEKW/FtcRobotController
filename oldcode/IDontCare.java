package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IDontCare {

    DcMotor topLeft = null;
    DcMotor bottomLeft = null;
    DcMotor topRight = null;
    DcMotor bottomRight = null;
    HardwareMap ahwm = null;

    public void init (HardwareMap hwm){
        ahwm = hwm;
        topLeft = ahwm.dcMotor.get("TopLeft");
        bottomLeft = ahwm.dcMotor.get("BottomLeft");
        topRight = ahwm.dcMotor.get("TopRight");
        bottomRight = ahwm.dcMotor.get("BottomRight");

        topLeft.setDirection(DcMotor.Direction.REVERSE);
        bottomLeft.setDirection(DcMotor.Direction.REVERSE);
        topRight.setDirection(DcMotor.Direction.FORWARD);
        bottomRight.setDirection(DcMotor.Direction.FORWARD);

        topLeft.setPower(0);
        bottomLeft.setPower(0);
        topRight.setPower(0);
        bottomRight.setPower(0);


    }

}
