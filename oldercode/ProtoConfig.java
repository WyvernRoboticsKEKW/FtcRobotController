package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Robotics on 9/14/2017.
 */

public class ProtoConfig {
    public DcMotor topRight = null;
    public DcMotor topLeft = null;
    public DcMotor bottomRight = null;
    public DcMotor bottomLeft = null;
    //public DcMotor glyphArm = null;
    public Servo hand1 = null;
    public Servo hand2 = null;

    HardwareMap hwmap = null;

    public ProtoConfig(){

    }

    public void init(HardwareMap ahwmap){
        hwmap = ahwmap;

        topRight = hwmap.get(DcMotor.class, "m1");
        topLeft = hwmap.get(DcMotor.class, "m2");
        bottomRight = hwmap.get(DcMotor.class, "m3");
        bottomLeft = hwmap.get(DcMotor.class, "m4");
        //glyphArm = hwmap.get(DcMotor.class, "glyphArm");
        hand1 = hwmap.get(Servo.class, "hand1");
        hand2 = hwmap.get(Servo.class, "hand2");

        topLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        bottomLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        topRight.setPower(0);
        topLeft.setPower(0);
        bottomRight.setPower(0);
        bottomLeft.setPower(0);

        topRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        topLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bottomRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bottomLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);



    }
}
