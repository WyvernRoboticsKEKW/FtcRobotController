package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;


public class Azure {
    DcMotor leftA;
    DcMotor leftB;
    DcMotor rightA;
    DcMotor rightB;
    Servo liftyBoi;
     // CRServo carousel;
    DcMotor arm;
    DcMotor intake;
    // Drivetrain

    BNO055IMU imu;

    OpenCvCamera camera;
    //camera

    public Azure(HardwareMap hwmap){
        leftA = hwmap.get(DcMotor.class, "leftA");
        leftB = hwmap.get(DcMotor.class, "leftB");
        rightA = hwmap.get(DcMotor.class, "rightA");
        rightB = hwmap.get(DcMotor.class, "rightB");
        arm = hwmap.get(DcMotor.class, "arm");
        intake = hwmap.get(DcMotor.class,"intake");

        liftyBoi = hwmap.get(Servo.class, "lift1");
       // carousel = hwmap.get(CRServo.class, "carousel");
        // defining devices from the config

        rightA.setDirection(DcMotorSimple.Direction.REVERSE);
        rightB.setDirection(DcMotorSimple.Direction.REVERSE);
        // Making the motors on the left move forward

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setTargetPosition(0);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setDirection(DcMotorSimple.Direction.REVERSE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hwmap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = "AdafruitIMUCalibration.json";
        imu.initialize(parameters);

        WebcamName webcamName = hwmap.get(WebcamName.class, "Webcam 1");

        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName);
    }

}
