package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Robotics on 9/21/2017.
 */

public class robotConfig {

    public DcMotor topRight = null;
    public DcMotor topLeft = null;
    public DcMotor bottomRight = null;
    public DcMotor bottomLeft = null;

    robotConfig(){

    }

    public void init(HardwareMap hwmap){
        topRight = hwmap.get(DcMotor.class,"topr");
        topLeft = hwmap.get(DcMotor.class,"topl");
        bottomLeft = hwmap.get(DcMotor.class, "bottoml");
        bottomRight = hwmap.get (DcMotor.class, "bottomr");

        topLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        bottomLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        bottomRight.setDirection (DcMotorSimple.Direction.FORWARD);
        topRight.setDirection (DcMotorSimple.Direction.FORWARD);

        topRight.setPower(0);
        topLeft.setPower(0);
        bottomRight.setPower(0);
        bottomLeft.setPower(0);
    }

}
