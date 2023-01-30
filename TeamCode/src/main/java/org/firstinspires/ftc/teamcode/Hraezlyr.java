package org.firstinspires.ftc.teamcode;





import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

public class Hraezlyr {
    public DcMotor topLeft;
    public DcMotor topRight;
    public DcMotor bottomLeft;
    public DcMotor bottomRight;
    public DcMotor cascadeMotor1;
    public DcMotor cascadeMotor2;
    public DcMotor horizontalMotor;
    public OpenCvCamera camera;
    public Servo servoClaw;
    public BNO055IMU IMU;


    public Hraezlyr(HardwareMap hwmap) {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled = false;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        topLeft = hwmap.get(DcMotor.class, "topLeft");
        topRight = hwmap.get(DcMotor.class, "topRight");
        bottomLeft = hwmap.get(DcMotor.class, "bottomLeft");
        bottomRight = hwmap.get(DcMotor.class, "bottomRight");

        cascadeMotor1 = hwmap.get(DcMotor.class, "cascadeMotor1");
        cascadeMotor2 = hwmap.get(DcMotor.class, "cascadeMotor2");
        servoClaw = hwmap.get(Servo.class, "servoClaw");
        horizontalMotor = hwmap.get(DcMotor.class, "horizontalMotor");


        topRight.setDirection(DcMotorSimple.Direction.REVERSE);
        bottomRight.setDirection(DcMotorSimple.Direction.REVERSE);


        IMU = hwmap.get(BNO055IMU.class, "imu");

        WebcamName webcamName = hwmap.get(WebcamName.class,"Webcam 1");
        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName);

        topLeft.setZeroPowerBehavior(BRAKE);
        topRight.setZeroPowerBehavior(BRAKE);
        bottomLeft.setZeroPowerBehavior(BRAKE);
        bottomRight.setZeroPowerBehavior(BRAKE);

        cascadeMotor1.setZeroPowerBehavior(BRAKE);
        cascadeMotor2.setZeroPowerBehavior(BRAKE);




        cascadeMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        cascadeMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        IMU.initialize(parameters);
    }

    public double getHeading() {
        Orientation angles = IMU.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
        return (angles.secondAngle);
    }
    public Orientation getAllAngles(){
        Orientation angles = IMU.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
        return (angles);
    }

    public double resetIMU() {
        Orientation reset = IMU.getAngularOrientation();

        return (reset.secondAngle);
    }

    public void setMotorsMode(DcMotor.RunMode runMode) {
        topLeft.setMode(runMode);
        topRight.setMode(runMode);
        bottomLeft.setMode(runMode);
        bottomRight.setMode(runMode);
    }

    public void setCascadeMotorsMode(DcMotor.RunMode runMode) {
        cascadeMotor1.setMode(runMode);
        cascadeMotor2.setMode(runMode);


    }
}