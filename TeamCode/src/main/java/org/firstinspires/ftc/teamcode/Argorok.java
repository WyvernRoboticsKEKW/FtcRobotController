package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Argorok {

    DcMotor frontLeft = null;
    DcMotor backLeft = null;
    DcMotor frontRight = null;
    DcMotor backRight = null;

    //DcMotor lift = null;

    //Servo leftClaw = null;
    //Servo rightClaw = null;

    HardwareMap hwmap = null;

    BNO055IMU imu = null;

    public void init(HardwareMap hwm){

        hwmap = hwm;

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hwmap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        frontRight = hwmap.get(DcMotor.class, "frontRight");
        frontLeft = hwmap.get(DcMotor.class, "frontLeft");
        backRight = hwmap.get(DcMotor.class, "backRight");
        backLeft = hwmap.get(DcMotor.class, "backLeft");

        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        //lift.setDirection(DcMotor.Direction.FORWARD);

        //leftClaw.setDirection();
        //rightClaw.setDirection();
    }

}
