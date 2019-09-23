package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Robotics on 9/28/2017.
 */

public class RobotConfigW {

    public DcMotor Right = null;
    public DcMotor Left = null;
    public DcMotor Elevator = null;
    public Servo colorArm = null;
    public Servo leftArm = null;
    public Servo rightArm = null;
    public ColorSensor sensor = null;
    //public CRServo uRightArm = null;
    //public Servo uLeftArm = null;

    RobotConfigW (){

    }

    public void init (HardwareMap hwmap){
        Right = hwmap.get(DcMotor.class,"right");
        Left = hwmap.get(DcMotor.class,"left");

        colorArm = hwmap.get(Servo.class,"colorArm");
        leftArm = hwmap.get(Servo.class,"leftArm");
        rightArm = hwmap.get(Servo.class,"rightArm");
        //uLeftArm = hwmap.get(Servo.class, "uLeftArm");
        //uRightArm = hwmap.get(CRServo.class, "uRightArm");
        sensor = hwmap.get(ColorSensor.class,"sensor");


        Left.setDirection(DcMotorSimple.Direction.REVERSE);
        Right.setDirection(DcMotorSimple.Direction.FORWARD);

        leftArm.setDirection(Servo.Direction.FORWARD);
        rightArm.setDirection(Servo.Direction.FORWARD);
        //uLeftArm.setDirection(Servo.Direction.FORWARD);
        //uRightArm.setDirection(CRServo.Direction.FORWARD);

        leftArm.setPosition(0);
        rightArm.setPosition(.9);
        //uLeftArm.setPosition(1);
        //uRightArm.setPower(0);

        Right.setPower(0);
        Left.setPower(0);

        colorArm.setDirection(Servo.Direction.FORWARD);
        colorArm.setPosition(1);

        Elevator = hwmap.get(DcMotor.class, "elevator");
        Elevator.setDirection(DcMotorSimple.Direction.FORWARD);
        Elevator.setPower(0);


    }

}
