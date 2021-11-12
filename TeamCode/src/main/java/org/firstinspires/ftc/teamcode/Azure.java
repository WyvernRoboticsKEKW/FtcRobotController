package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvPipeline;

public class Azure {
    DcMotor leftA;
    DcMotor leftB;
    DcMotor rightA;
    DcMotor rightB;
    Servo liftyBoi;
    // Drivetrain

    BNO055IMU imu;

    OpenCvCamera camera;
    Pipeline pipeline;
    //camera

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

        imu = hwmap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = "AdafruitIMUCalibration.json";
        imu.initialize(parameters);

        WebcamName webcamName = hwmap.get(WebcamName.class, "Webcam 1");

        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName);
    }
}
